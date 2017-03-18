package rest.controllers;

import application.clientspec.ClientAssets;
import application.enums.MEETING_TYPE;
import application.enums.STATUS;
import application.enums.USER_TYPE;
import application.exceptions.BaseException;
import application.exceptions.ErrorConstants;
import application.utilities.Constants;
import application.utilities.Util;
import communication.email.client.EmailExecutor;
import communication.email.client.SendEmail;
import communication.email.processor.Fields;
import communication.ws.socialvid.bean.request.GetConferenceRequest;
import communication.ws.socialvid.bean.response.GetConferenceResponse;
import models.Meeting;
import models.Presenter;
import models.beans.MeetingBean;
import play.mvc.Result;
import rest.base.BaseController;
import rest.bean.request.MeetingRequest;
import rest.bean.response.ResponseBean;
import rest.responsedto.ErrorResponse;
import services.ServicesFactory;
import communication.ws.socialvid.bean.request.CreateConferenceRequest;
import communication.ws.socialvid.bean.request.UserLoginRequest;
import communication.ws.socialvid.bean.response.CreateConferenceResponse;
import communication.ws.socialvid.bean.response.UserLoginResponse;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Date;
import java.util.List;

/**
 * Created by pkonwar on 1/11/2017.
 */
@Named
@Singleton
public class MeetingController extends BaseController {

    @Inject
    ServicesFactory servicesFactory;

    public Result meetings(Long presenterId, String meetingType) {
        ResponseBean responseBean = null;
        try {
            MEETING_TYPE type = MEETING_TYPE.findMeetingType(meetingType.toUpperCase());
            Presenter presenter = servicesFactory.presenterService.findPresenter(presenterId);

            List<Meeting> meetingList = null;
            switch (type) {
                case ALL_MEETING:
                    meetingList = servicesFactory.meetingService.allMeeting(presenter);
                    break;
                case PAST_MEETING:
                    meetingList = servicesFactory.meetingService.pastMeetings(presenter);
                    break;
                case UPCOMMING_MEETING:
                    meetingList = servicesFactory.meetingService.upcommingMeetings(presenter);
                    break;
            }
            List<MeetingBean> meetingBeanList = servicesFactory.meetingService.convertIntoMeetingBean(meetingList);
            responseBean = new ResponseBean(STATUS.SUCCESS, null, meetingBeanList, null);
        } catch (BaseException ex) {
            System.out.println(ex.getCause());
            ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), ex.getErrorMessage());
            return errorObjectToJsonResponse(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = unknownErrorResponse();
            return errorObjectToJsonResponse(errorResponse);
        }
        return convertObjectToJsonResponse(responseBean);
    }

    //create a meeting
    public Result createNewMeeting() {
        ResponseBean responseBean = null;
        try {
            MeetingRequest meetingRequest = convertRequestBodyToObject(request().body(), MeetingRequest.class);
            MeetingBean meetingBean = meetingRequest.toMeetingBean();

            //Login to the socialvid server
            //@TODO Remove the hard coding of email id and password
            UserLoginRequest userLoginRequest = new UserLoginRequest("arunsimon@gmail.com", "Arun123");
            UserLoginResponse loginResponse = servicesFactory.conferenceApi.userLogin(userLoginRequest);

            //Create conference
            CreateConferenceRequest createConferenceRequest = new CreateConferenceRequest("arunsimon@gmail.com", loginResponse.getSession(), meetingBean.getTitle(), "10", CreateConferenceRequest.MODE.PRESENTER);
            CreateConferenceResponse createConferenceResponse = servicesFactory.conferenceApi.createConference(createConferenceRequest);

            //the presenter
            Presenter presenter = servicesFactory.presenterService.findPresenter(meetingBean.getPresenterBean().getId());

            //add the conference as the token
            meetingBean.setPresenterToken(createConferenceResponse.getId());
            meetingBean.setAttendeesToken(createConferenceResponse.getId());

            //set the mode
            meetingBean.setMode(CreateConferenceRequest.MODE.PRESENTER.mode);
            Meeting meeting = servicesFactory.meetingService.createMeeting(meetingBean, presenter);
            responseBean = new ResponseBean(STATUS.SUCCESS, null, meeting.toMeetingBean(), null);

            ClientAssets clientAssets = new ClientAssets(meetingRequest.getClientName());

            String meetingUrl = clientAssets.getConferenceUrl() + Constants.CONFERENCE_UI_ENDPOINT_ATTENDEE + "/" + meeting.getAttendeesToken();
            //EmailClient.sendEmail(meeting.getPresenter().getEmailId(), "Meeting Scheduled" , "The Url is : " + meetingUrl);


            Fields bodyFields = new Fields();
            bodyFields.addField("professor_name", presenter.getPresenterName());
            bodyFields.addField("professor_designation", presenter.getDesignation());
            bodyFields.addField("department_name", presenter.getDepartment());
            bodyFields.addField("topic", meetingBean.getTitle());
            bodyFields.addField("url", meetingUrl);
            bodyFields.addField("scheduled_datetime", meetingBean.getStartDateTime().toString());
            bodyFields.addField("client_image_name", clientAssets.getImageNameForEmailHeader());

            Fields calenderInviteFields = new Fields();
            calenderInviteFields.addField("to_email", presenter.getEmailId());
            calenderInviteFields.addField("start_time", Util.convertDateToString(meetingBean.getStartDateTime()));
            calenderInviteFields.addField("end_time", Util.convertDateToString(meetingBean.getStartDateTime(), meetingBean.getDuration()));
            calenderInviteFields.addField("conference_location", meetingUrl);
            calenderInviteFields.addField("current_time", Util.convertDateToString(new Date()));
            calenderInviteFields.addField("meeting_description", meetingBean.getDescription());
            calenderInviteFields.addField("meeting_topic",meetingBean.getTitle());
            calenderInviteFields.addField("unique_id", String.valueOf(System.currentTimeMillis()));

            String[] invitees = meetingBean.getInvitees().split(",");

            for(String invitee : invitees) {
                System.out.println("Sending Email to : " + invitee);
                EmailExecutor.sendEmailInAsyncMode(new SendEmail("sdaya2012@gmail.com", invitee, "Meeting Scheduled by " + presenter.getPresenterName(), bodyFields, calenderInviteFields));
            }

        } catch (BaseException ex) {
            System.out.println(ex.getCause());
            ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), ex.getErrorMessage());
            return errorObjectToJsonResponse(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = unknownErrorResponse();
            return errorObjectToJsonResponse(errorResponse);
        }
        return convertObjectToJsonResponse(responseBean);
    }

    public Result createQuickMeeting() {

        ResponseBean responseBean = null;
        try {
            MeetingRequest meetingRequest = convertRequestBodyToObject(request().body(), MeetingRequest.class);
            MeetingBean meetingBean = meetingRequest.toMeetingBean();

            //default for quick meeting
            meetingBean.setDuration(1);
            meetingBean.setStartDateTime(new Date());

            //Login to the socialvid server
            //@TODO Remove the hard coding of email id and password
            UserLoginRequest userLoginRequest = new UserLoginRequest("arunsimon@gmail.com", "Arun123");
            UserLoginResponse loginResponse = servicesFactory.conferenceApi.userLogin(userLoginRequest);

            //Create conference
            CreateConferenceRequest createConferenceRequest = new CreateConferenceRequest("arunsimon@gmail.com", loginResponse.getSession(), meetingBean.getTitle(), "5", CreateConferenceRequest.MODE.GROUP);
            CreateConferenceResponse createConferenceResponse = servicesFactory.conferenceApi.createConference(createConferenceRequest);

            //the presenter
            Presenter presenter = servicesFactory.presenterService.findPresenter(meetingBean.getPresenterBean().getId());

            //add the conference as the token
            meetingBean.setPresenterToken(createConferenceResponse.getId());
            meetingBean.setAttendeesToken(createConferenceResponse.getId());

            //set mode
            meetingBean.setMode(CreateConferenceRequest.MODE.GROUP.mode);

            Meeting meeting = servicesFactory.meetingService.createMeeting(meetingBean, presenter);
            responseBean = new ResponseBean(STATUS.SUCCESS, null, meeting.toMeetingBean(), null);

            //get client specific assets
            ClientAssets clientAssets = new ClientAssets(meetingRequest.getClientName());

            String meetingUrl = clientAssets.getConferenceUrl() + Constants.CONFERENCE_UI_ENDPOINT_INSTANT_MEETING + "/" + meeting.getAttendeesToken();



            //meeting invite
            Fields bodyFields = new Fields();
            bodyFields.addField("professor_name", presenter.getPresenterName());
            bodyFields.addField("professor_designation", presenter.getDesignation());
            bodyFields.addField("department_name", presenter.getDepartment());
            bodyFields.addField("topic", meetingBean.getTitle());
            bodyFields.addField("url", meetingUrl);
            bodyFields.addField("scheduled_datetime", meetingBean.getStartDateTime().toString());
            bodyFields.addField("client_image_name", clientAssets.getImageNameForEmailHeader());

            Fields calenderInviteFields = new Fields();
            calenderInviteFields.addField("to_email", presenter.getEmailId());
            calenderInviteFields.addField("start_time", Util.convertDateToString(meetingBean.getStartDateTime()));
            calenderInviteFields.addField("end_time", Util.convertDateToString(meetingBean.getStartDateTime(), meetingBean.getDuration()));
            calenderInviteFields.addField("conference_location", meetingUrl);
            calenderInviteFields.addField("current_time", Util.convertDateToString(new Date()));
            calenderInviteFields.addField("meeting_description", meetingBean.getDescription());
            calenderInviteFields.addField("meeting_topic",meetingBean.getTitle());
            calenderInviteFields.addField("unique_id", String.valueOf(System.currentTimeMillis()));

            String[] invitees = meetingBean.getInvitees().split(",");

            for(String invitee : invitees) {
                System.out.println("Sending Email to : " + invitee);
                EmailExecutor.sendEmailInAsyncMode(new SendEmail("sdaya2012@gmail.com", invitee, "Meeting Scheduled by " + presenter.getPresenterName(), bodyFields, calenderInviteFields));
            }

        } catch (BaseException ex) {
            System.out.println(ex.getCause());
            ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), ex.getErrorMessage());
            return errorObjectToJsonResponse(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = unknownErrorResponse();
            return errorObjectToJsonResponse(errorResponse);
        }
        return convertObjectToJsonResponse(responseBean);

    }

    //find meeting based on conference id
    public Result findMeeting(String conferenceId) {

        ResponseBean responseBean = null;
        try {
            UserLoginRequest userLoginRequest = new UserLoginRequest("arunsimon@gmail.com", "Arun123");
            UserLoginResponse loginResponse = servicesFactory.conferenceApi.userLogin(userLoginRequest);

            GetConferenceRequest conferenceRequest = new GetConferenceRequest("arunsimon@gmail.com", loginResponse.getSession(),conferenceId);
            //GetConferenceRequest conferenceRequest = new GetConferenceRequest("arunsimon@gmail.com", "9f869c97c2833382",conferenceId);
            GetConferenceResponse conferenceResponse = servicesFactory.conferenceApi.getConference(conferenceRequest);

            responseBean = new ResponseBean(STATUS.SUCCESS, null, conferenceResponse, null);
        } catch (BaseException ex) {
            System.out.println(ex.getCause());
            ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), ex.getErrorMessage());
            return errorObjectToJsonResponse(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = unknownErrorResponse();
            return errorObjectToJsonResponse(errorResponse);
        }
        return convertObjectToJsonResponse(responseBean);

    }

    //store the recorded url
    public Result storeRecordedUrl() {
        ResponseBean responseBean = null;
        try {
            MeetingRequest meetingRequest = convertRequestBodyToObject(request().body(), MeetingRequest.class);
            MeetingBean meetingBean = meetingRequest.toMeetingBean();
            Meeting meeting = servicesFactory.meetingService.saveRecordedUrl(meetingBean.getId(), meetingBean.getRecordedUrl());
            responseBean = new ResponseBean(STATUS.SUCCESS, null, meeting.toMeetingBean(), null);
        } catch (BaseException ex) {
            System.out.println(ex.getCause());
            ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), ex.getErrorMessage());
            return errorObjectToJsonResponse(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = unknownErrorResponse();
            return errorObjectToJsonResponse(errorResponse);
        }
        return convertObjectToJsonResponse(responseBean);
    }
}
