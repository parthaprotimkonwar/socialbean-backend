package rest.controllers;

import application.enums.MEETING_TYPE;
import application.enums.STATUS;
import application.enums.USER_TYPE;
import application.exceptions.BaseException;
import application.exceptions.ErrorConstants;
import com.google.common.io.LittleEndianDataOutputStream;
import models.Meeting;
import models.Presenter;
import models.beans.MeetingBean;
import models.beans.PresenterBean;
import play.mvc.Result;
import rest.base.BaseController;
import rest.bean.request.MeetingRequest;
import rest.bean.request.RequestBean;
import rest.bean.response.ResponseBean;
import rest.responsedto.ErrorResponse;
import services.ServicesFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
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
            Presenter presenter = servicesFactory.presenterService.findPresenter(meetingBean.getPresenterBean().getId());
            Meeting meeting = servicesFactory.meetingService.createMeeting(meetingBean, presenter);
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
