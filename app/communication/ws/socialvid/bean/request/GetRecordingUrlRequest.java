package communication.ws.socialvid.bean.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * Created by parthaprotimkonwar on 18/03/17.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetRecordingUrlRequest implements Serializable {

    private String session; //the session
    private String email;   //the email
    private String confName;//the conference name
    private String confId;//the conference name

    public GetRecordingUrlRequest(){}

    public GetRecordingUrlRequest(String session, String email, String confId){
        this.session = session;
        this.email = email;
        this.confId = confId;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfName() {
        return confName;
    }

    public void setConfName(String confName) {
        this.confName = confName;
    }

    public String getConfId() {
        return confId;
    }

    public void setConfId(String confId) {
        this.confId = confId;
    }
}
