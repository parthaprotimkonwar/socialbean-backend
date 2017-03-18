package communication.ws.socialvid.bean.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by parthaprotimkonwar on 20/02/17.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetConferenceResponse implements Serializable{

    private String id;
    private String name;
    private Boolean autoRecord;
    private String maxParticipants;
    private String maxBitrateKbps;
    private String mode;

    private Map<String, Object> urls = new HashMap<>();

    public GetConferenceResponse() {}

    public GetConferenceResponse(String id, String name, Boolean autoRecord, String maxParticipants, String maxBitrateKbps, String mode, Map<String, Object> urls) {
        this.id = id;
        this.name = name;
        this.autoRecord = autoRecord;
        this.maxParticipants = maxParticipants;
        this.maxBitrateKbps = maxBitrateKbps;
        this.mode = mode;
        this.urls = urls;
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

    public Map<String, Object> getUrls() {
        return urls;
    }

    public void setUrls(Map<String, Object> urls) {
        this.urls = urls;
    }
    //urls":{"audioOnly":{"moderator":"https://ha.socialvid.in/guest.html?conferenceId=ad29213769d8df02&audio=1&video=0&dialout=0&moderator=1&c=dae4a2b7491386ff","participant":"https://ha.socialvid.in/guest.html?conferenceId=ad29213769d8df02&audio=1&video=0&dialout=0&moderator=0&c=0d3ceaf34bc7fe33"},"audioVideo":{"moderator":"https://ha.socialvid.in/guest.html?conferenceId=ad29213769d8df02&audio=1&video=1&dialout=0&moderator=1&c=b1aab78f62585ddd","participant":"https://ha.socialvid.in/guest.html?conferenceId=ad29213769d8df02&audio=1&video=1&dialout=0&moderator=0&c=5cf79cacfcf612eb"},"shareOnly":{"moderator":"https://ha.socialvid.in/guest.html?conferenceId=ad29213769d8df02&audio=0&video=0&dialout=1&moderator=1&c=6633db07d1fedce1","participant":"https://ha.socialvid.in/guest.html?conferenceId=ad29213769d8df02&audio=0&video=0&dialout=1&moderator=0&c=b593a6c3059ab6bc"}}}
}
