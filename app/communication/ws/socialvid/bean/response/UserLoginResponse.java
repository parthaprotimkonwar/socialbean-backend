package communication.ws.socialvid.bean.response;

import java.io.Serializable;

/**
 * Created by pkonwar on 1/29/2017.
 */
public class UserLoginResponse implements Serializable {

    private String session;

    public UserLoginResponse() {}

    public UserLoginResponse(String session) {
        this.session = session;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
