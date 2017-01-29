package communication.ws.helper;

/**
 * Created by pkonwar on 1/29/2017.
 */
public interface RestClientEndpointInterface {
    String getUrl();

    HttpRequestType getReqType();

    String[] getParams();

    String getName();
}
