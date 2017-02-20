package communication.ws.socialvid.bean.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * Created by parthaprotimkonwar on 20/02/17.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetConferenceRequest implements Serializable{

    private String email;   //the email
    private String session; //the session
    private String id;      //the conference id


    public GetConferenceRequest() {}

    public GetConferenceRequest(String email, String session, String id) {
        this.email = email;
        this.session = session;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
