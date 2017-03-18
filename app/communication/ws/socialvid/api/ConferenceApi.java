package communication.ws.socialvid.api;

import application.exceptions.BaseException;
import communication.ws.socialvid.bean.request.CreateConferenceRequest;
import communication.ws.socialvid.bean.request.GetConferenceRequest;
import communication.ws.socialvid.bean.request.GetRecordingUrlRequest;
import communication.ws.socialvid.bean.request.UserLoginRequest;
import communication.ws.socialvid.bean.response.CreateConferenceResponse;
import communication.ws.socialvid.bean.response.GetConferenceResponse;
import communication.ws.socialvid.bean.response.GetRecordingUrlResponse;
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


    /**
     * Get the conference details based on the conference id
     * @param getConferenceRequest
     * @return
     * @throws BaseException
     */
    GetConferenceResponse getConference(GetConferenceRequest getConferenceRequest) throws BaseException;

    /**
     * Get Recording URL's for the conference
     * @param getRecordingUrlRequest
     * @return
     * @throws BaseException
     */
    GetRecordingUrlResponse getRecordingUrlResponse(GetRecordingUrlRequest getRecordingUrlRequest) throws BaseException;

}
