package rest.bean.request;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by pkonwar on 1/11/2017.
 */
public class RequestBean implements Serializable {

    private String token;                   // authentication token
    private Map<String, String> requestDataHeaders; // additional data to identify the request
    private Object data;                    // actual data

    public RequestBean() {
    }

    public RequestBean(String token, Map<String, String> requestDataHeaders, Object data) {
        this.token = token;
        this.requestDataHeaders = requestDataHeaders;
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Map<String, String> getRequestDataHeaders() {
        return requestDataHeaders;
    }

    public void setRequestDataHeaders(Map<String, String> requestDataHeaders) {
        this.requestDataHeaders = requestDataHeaders;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
