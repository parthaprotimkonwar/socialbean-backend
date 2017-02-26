package application.utilities;

/**
 * Created by pkonwar on 1/9/2017.
 */
public interface Constants {

    //CONSTANTS
    String USER_MANAGEMENT = "AUSER_MANAGEMENT";
    String MEETING_SCHEDULES = "MEETING_SCHEDULES";
    String CONFERENCE_UI_HOST = "conference.ui.host";
    String CONFERENCE_UI_PORT = "conference.ui.port";
    String CONFERENCE_UI_PROTOCOL = "conference.ui.protocol";
    String BACKEND_APP_HOST = "backend.app.host";
    String BACKEND_APP_PORT = "backend.app.port";
    String BACKEND_APP_PROTOCOL = "backend.app.protocol";

    String DATE_TIME_PATTERN = "MM/dd/yyyy HH:mm:ss";
    Integer RANDOM_MEETING_TOKEN_LENGTH = 6;
    Integer REST_TIMEOUT = 10000;


    //logo small
    String BACKEND_ASSETS_LOGO_PATH = ApplicationConf.readProperty(BACKEND_APP_PROTOCOL) + "://" + ApplicationConf.readProperty(BACKEND_APP_HOST) + ":" + ApplicationConf.readProperty(BACKEND_APP_PORT) + "/assets";

    //Third Party API
    String SOCIAL_VID_ENDPOINT = "https://ha.socialvid.in/adminapi/v1";
    String CONFERENCE_UI_ENDPOINT_INSTANT_MEETING = "/conferencing.html#/conference/join/guest";
    String CONFERENCE_UI_ENDPOINT_PRESENTER = "/conferencing.html#/conference/join/p";
    String CONFERENCE_UI_ENDPOINT_ATTENDEE = "/conferencing.html#/conference/join/a";


}
