package rest.responsedto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO for sending ErrorMessages
 *
 * @author pkonwar
 */
public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    public ErrorResponse() {
    }

    /**
     * Generates ErrorResponse accepting errorCode and List of errorMessages
     *
     * @param errorCode
     * @param errorMessage
     */
    public ErrorResponse(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String errorCode;

    public String errorMessage;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
