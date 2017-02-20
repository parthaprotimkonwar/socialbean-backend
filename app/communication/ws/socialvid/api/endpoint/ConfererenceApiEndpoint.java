package communication.ws.socialvid.api.endpoint;

import application.utilities.Constants;
import communication.ws.helper.HttpRequestType;
import communication.ws.helper.RestClientEndpointInterface;

/**
 * Created by pkonwar on 1/29/2017.
 */
public enum ConfererenceApiEndpoint implements RestClientEndpointInterface {

    //ENDPOINTS
    USER_LOGIN(HttpRequestType.POST, Constants.SOCIAL_VID_ENDPOINT + "/userlogin"),
    ADD_CONFERENCE(HttpRequestType.POST, Constants.SOCIAL_VID_ENDPOINT + "/user/conference/add"),
    GET_CONFERENCE(HttpRequestType.POST, Constants.SOCIAL_VID_ENDPOINT + "/user/conference/get"),
    SAMPLE_HTTP(HttpRequestType.POST, "http://localhost:3000/listening"),
    SAMPLE_HTTPS(HttpRequestType.POST, "https://localhost:8443/listening");

    //DEFINATION
    private final String url;

    private HttpRequestType reqType;

    private String[] params;

    private ConfererenceApiEndpoint(HttpRequestType reqType, String url, String... params) {
        this.reqType = reqType;
        this.url = url;
        this.params = params;
    }

    public String getUrl() {
        return url;
    }

    public HttpRequestType getReqType() {
        return reqType;
    }

    public String[] getParams() {
        return params;
    }

    @Override
    public String getName() {
        return this.name();
    }

}
