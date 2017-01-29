package communication.ws.socialvid.api.impl;

import application.exceptions.BaseException;
import application.exceptions.ErrorConstants;
import application.utilities.Constants;
import com.fasterxml.jackson.databind.JsonNode;
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
}
