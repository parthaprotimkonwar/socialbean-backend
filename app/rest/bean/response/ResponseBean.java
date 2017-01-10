package rest.bean.response;

import application.enums.STATUS;
import rest.responsedto.ErrorResponse;

import java.io.Serializable;

/**
 * Created by pkonwar on 5/13/2016.
 */
public class ResponseBean implements Serializable {

    private STATUS status;                  // status
    private String token;                   // authentication token
    private Object data;                    // actual data
    private ErrorResponse errorResponse;    // error cause and messages

    public ResponseBean() {
    }

    public ResponseBean(STATUS status, String token, Object data, ErrorResponse errorResponse) {
        this.status = status;
        this.token = token;
        this.data = data;
        this.errorResponse = errorResponse;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }
}
