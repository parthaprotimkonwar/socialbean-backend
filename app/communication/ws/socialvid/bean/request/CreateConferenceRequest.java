package communication.ws.socialvid.bean.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * Created by pkonwar on 1/29/2017.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CreateConferenceRequest implements Serializable {

    //the conference mode
    public enum MODE{

        GROUP("group"),
        PRESENTER("presenter");

        String mode;
        MODE(String mode) {
            this.mode = mode;
        }
    }
    private String email;
    private String session;
    private String name;
    private String mode;
    private Boolean autoRecord = true;
    private String maxBitrateKbps = "512";
    private String maxParticipants;

    public CreateConferenceRequest(String email, String session, String name, String maxParticipants, MODE mode) {
        this.email = email;
        this.session = session;
        this.name = name;
        this.maxParticipants = maxParticipants;
        //default mode is group
        this.mode = mode == null ? MODE.GROUP.mode : mode.mode;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(String maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Boolean getAutoRecord() {
        return autoRecord;
    }

    public void setAutoRecord(Boolean autoRecord) {
        this.autoRecord = autoRecord;
    }

    public String getMaxBitrateKbps() {
        return maxBitrateKbps;
    }

    public void setMaxBitrateKbps(String maxBitrateKbps) {
        this.maxBitrateKbps = maxBitrateKbps;
    }
}
