package communication.ws.socialvid.bean.response;

import java.io.Serializable;

/**
 * Created by pkonwar on 1/29/2017.
 */
public class CreateConferenceResponse implements Serializable {

    private String id;
    private String name;
    private Boolean autoRecord;
    private String maxParticipants;
    private String maxBitrateKbps;
    private String mode;

    public CreateConferenceResponse() {
    }

    public CreateConferenceResponse(String id, String name, Boolean autoRecord, String maxParticipants, String maxBitrateKbps, String mode) {
        this.id = id;
        this.name = name;
        this.autoRecord = autoRecord;
        this.maxParticipants = maxParticipants;
        this.maxBitrateKbps = maxBitrateKbps;
        this.mode = mode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAutoRecord() {
        return autoRecord;
    }

    public void setAutoRecord(Boolean autoRecord) {
        this.autoRecord = autoRecord;
    }

    public String getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(String maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public String getMaxBitrateKbps() {
        return maxBitrateKbps;
    }

    public void setMaxBitrateKbps(String maxBitrateKbps) {
        this.maxBitrateKbps = maxBitrateKbps;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
