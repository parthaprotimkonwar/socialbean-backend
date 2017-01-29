package communication.ws.helper;

/**
 * Created by pkonwar on 1/29/2017.
 */
public enum HttpRequestType {

    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");

    HttpRequestType(String request) {
        this.request = request;
    }

    String request;

    public String getRequest() {
        return request;
    }
}
