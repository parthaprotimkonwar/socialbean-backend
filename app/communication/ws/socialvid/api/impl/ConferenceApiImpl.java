package communication.ws.socialvid.api.impl;

import application.exceptions.BaseException;
import application.exceptions.ErrorConstants;
import application.utilities.Constants;
import com.fasterxml.jackson.databind.JsonNode;
import communication.ws.socialvid.bean.request.GetConferenceRequest;
import communication.ws.socialvid.bean.request.GetRecordingUrlRequest;
import communication.ws.socialvid.bean.response.GetConferenceResponse;
import communication.ws.socialvid.bean.response.GetRecordingUrlResponse;
import play.libs.F;
import play.libs.Json;
import play.libs.WS;
import rest.base.BaseController;
import communication.ws.helper.RestClient;
import communication.ws.socialvid.api.ConferenceApi;
import communication.ws.socialvid.api.endpoint.ConfererenceApiEndpoint;
import communication.ws.socialvid.bean.request.CreateConferenceRequest;
import communication.ws.socialvid.bean.request.UserLoginRequest;
import communication.ws.socialvid.bean.response.CreateConferenceResponse;
import communication.ws.socialvid.bean.response.UserLoginResponse;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Created by pkonwar on 1/29/2017.
 */

@Named
@Singleton
public class ConferenceApiImpl extends BaseController implements ConferenceApi {

    @Override
    public UserLoginResponse userLogin(UserLoginRequest userLoginRequest) throws BaseException {

        UserLoginResponse response = null;
        try {
            JsonNode userRequest = Json.toJson(userLoginRequest);
            F.Promise<WS.Response> res = RestClient.sendRequest(ConfererenceApiEndpoint.USER_LOGIN, userRequest, null);
            JsonNode jsonResponse = res.get(Constants.REST_TIMEOUT).asJson();
            response = convertJsonNodeToObject(jsonResponse, UserLoginResponse.class);

        } catch (Exception ex) {
            ex.printStackTrace();
            ErrorConstants error = ErrorConstants.INTEGRATION_FETCH_RESPONSE_EXCEPTION;
            throw new BaseException(error.getErrorCode(), error.getErrorMessage(), ex.getCause());
        }
        return response;
    }

    @Override
    public CreateConferenceResponse createConference(CreateConferenceRequest createConferenceRequest) throws BaseException {
        CreateConferenceResponse response;
        try {
            JsonNode userRequest = Json.toJson(createConferenceRequest);
            F.Promise<WS.Response> res = RestClient.sendRequest(ConfererenceApiEndpoint.ADD_CONFERENCE, userRequest, null);
            JsonNode jsonResponse = res.get(Constants.REST_TIMEOUT).asJson();
            response = convertJsonNodeToObject(jsonResponse, CreateConferenceResponse.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            ErrorConstants error = ErrorConstants.INTEGRATION_FETCH_RESPONSE_EXCEPTION;
            throw new BaseException(error.getErrorCode(), error.getErrorMessage(), ex.getCause());
        }
        return response;
    }

    @Override
    public GetConferenceResponse getConference(GetConferenceRequest getConferenceRequest) throws BaseException {
        GetConferenceResponse response = null;
        try {
            JsonNode userRequest = Json.toJson(getConferenceRequest);
            F.Promise<WS.Response> res = RestClient.sendRequest(ConfererenceApiEndpoint.GET_CONFERENCE, userRequest, null);
            JsonNode jsonResponse = res.get(Constants.REST_TIMEOUT).asJson();
            response = convertJsonNodeToObject(jsonResponse, GetConferenceResponse.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            ErrorConstants error = ErrorConstants.INTEGRATION_FETCH_RESPONSE_EXCEPTION;
            throw new BaseException(error.getErrorCode(), error.getErrorMessage(), ex.getCause());
        }
        return response;
    }

    @Override
    public GetRecordingUrlResponse getRecordingUrlResponse(GetRecordingUrlRequest getRecordingUrlRequest) throws BaseException {
        GetRecordingUrlResponse response = null;
        try {
            JsonNode userRequest = Json.toJson(getRecordingUrlRequest);
            F.Promise<WS.Response> res = RestClient.sendRequest(ConfererenceApiEndpoint.GET_RECORDED_URLS, userRequest, null);
            JsonNode jsonResponse = res.get(Constants.REST_TIMEOUT).asJson();
            response = convertJsonNodeToObject(jsonResponse, GetRecordingUrlResponse.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            ErrorConstants error = ErrorConstants.INTEGRATION_FETCH_RESPONSE_EXCEPTION;
            throw new BaseException(error.getErrorCode(), error.getErrorMessage(), ex.getCause());
        }
        return response;
    }
}
