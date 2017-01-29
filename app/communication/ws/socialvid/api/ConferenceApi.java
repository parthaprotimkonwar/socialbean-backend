package communication.ws.socialvid.api;

import application.exceptions.BaseException;
import communication.ws.socialvid.bean.request.CreateConferenceRequest;
import communication.ws.socialvid.bean.request.UserLoginRequest;
import communication.ws.socialvid.bean.response.CreateConferenceResponse;
import communication.ws.socialvid.bean.response.UserLoginResponse;

/**
 * Created by pkonwar on 1/29/2017.
 */
public interface ConferenceApi {

    /**
     * Login to the System
     * returns a sessionId which is used for further communication
     * @param userLoginRequest
     * @return
     */
    UserLoginResponse userLogin(UserLoginRequest userLoginRequest) throws BaseException;


    /**
     * Create a conference
     * @requiredFieds emailId sessionId meetingName maxParticipants
     * @param createConferenceRequest
     * @return
     */
    CreateConferenceResponse createConference(CreateConferenceRequest createConferenceRequest) throws BaseException;
}
