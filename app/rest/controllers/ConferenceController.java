package rest.controllers;

import application.enums.STATUS;
import application.exceptions.BaseException;
import communication.ws.socialvid.bean.request.GetRecordingUrlRequest;
import communication.ws.socialvid.bean.request.UserLoginRequest;
import communication.ws.socialvid.bean.response.GetRecordingUrlResponse;
import communication.ws.socialvid.bean.response.UserLoginResponse;
import play.mvc.Result;
import rest.base.BaseController;
import rest.bean.response.ResponseBean;
import rest.responsedto.ErrorResponse;
import services.ServicesFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Created by parthaprotimkonwar on 18/03/17.
 */
@Named
@Singleton
public class ConferenceController extends BaseController{

    @Inject
    ServicesFactory servicesFactory;


    public Result getRecordedUrls(String conferenceId) {

        ResponseBean responseBean = null;
        try {
            //Login to the socialvid server

            //conferenceName = "create inst";
            //@TODO Remove the hard coding of email id and password
            UserLoginRequest userLoginRequest = new UserLoginRequest("arunsimon@gmail.com", "Arun123");
            UserLoginResponse loginResponse = servicesFactory.conferenceApi.userLogin(userLoginRequest);

            //Get the recording url
            GetRecordingUrlRequest recordingUrlRequest = new GetRecordingUrlRequest(loginResponse.getSession(), "arunsimon@gmail.com", conferenceId);
            GetRecordingUrlResponse recordingUrlResponse = servicesFactory.conferenceApi.getRecordingUrlResponse(recordingUrlRequest);

            responseBean = new ResponseBean(STATUS.SUCCESS, null, recordingUrlResponse, null);

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
