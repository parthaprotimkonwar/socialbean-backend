package communication.ws.socialvid.bean.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * Created by pkonwar on 1/29/2017.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserLoginRequest implements Serializable {

    private String email;
    private String password;

    public UserLoginRequest() {
    }

    public UserLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
