package rest.controllers;

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
            CreateConferenceRequest createConferenceRequest = new CreateConferenceRequest("arunsimon@gmail.com", loginResponse.getSession(), meetingBean.getTitle(), "20");
            CreateConferenceResponse createConferenceResponse = servicesFactory.conferenceApi.createConference(createConferenceRequest);

            //the presenter
            Presenter presenter = servicesFactory.presenterService.findPresenter(meetingBean.getPresenterBean().getId());

            //add the conference as the token
            meetingBean.setPresenterToken(createConferenceResponse.getId());
            meetingBean.setAttendeesToken(createConferenceResponse.getId());

            Meeting meeting = servicesFactory.meetingService.createMeeting(meetingBean, presenter);
            responseBean = new ResponseBean(STATUS.SUCCESS, null, meeting.toMeetingBean(), null);

            String meetingUrl = Constants.CONFERENCE_UI_ENDPOINT + "/" + meeting.getAttendeesToken();
            //EmailClient.sendEmail(meeting.getPresenter().getEmailId(), "Meeting Scheduled" , "The Url is : " + meetingUrl);


            Fields bodyFields = new Fields();
            bodyFields.addField("professor_name", presenter.getPresenterName());
            bodyFields.addField("professor_designation", presenter.getDesignation());
            bodyFields.addField("department_name", presenter.getDepartment());
            bodyFields.addField("topic", meetingBean.getTitle());
            bodyFields.addField("url", meetingUrl);
            bodyFields.addField("scheduled_datetime", meetingBean.getStartDateTime().toString());

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

    //find a class
    public Result findMeeting(String userType, String tokenId) {
        ResponseBean responseBean = null;
        try {
            USER_TYPE type = USER_TYPE.findUserType(userType);
            Meeting meeting = null;

            switch (type) {
                case ATTENDEE:
                    meeting = servicesFactory.meetingService.findMeetingOfAttendee(tokenId);
                    break;
                case PRESENTER:
                    meeting = servicesFactory.meetingService.findMeetingOfPersentor(tokenId);
                    break;
            }
            if (meeting == null) {
                ErrorConstants error = ErrorConstants.NO_SUCH_RECORD;
                throw new BaseException(error.getErrorCode(), error.getErrorMessage());
            }
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
