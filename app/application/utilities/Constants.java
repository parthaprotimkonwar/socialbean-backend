package application.utilities;

/**
 * Created by pkonwar on 1/9/2017.
 */
public interface Constants {

    String USER_MANAGEMENT = "AUSER_MANAGEMENT";
    String MEETING_SCHEDULES = "MEETING_SCHEDULES";

    String DATE_TIME_PATTERN = "MM/dd/yyyy HH:mm:ss";
    Integer RANDOM_MEETING_TOKEN_LENGTH = 6;
    Integer REST_TIMEOUT = 10000;


    //Third Party API
    String SOCIAL_VID_ENDPOINT = "https://ha.socialvid.in/adminapi/v1";
    String CONFERENCE_UI_ENDPOINT = "http://localhost:3000/conferencing.html#/conference/join/guest";

}
