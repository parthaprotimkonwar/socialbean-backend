package communication.ws.socialvid.bean.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * Created by parthaprotimkonwar on 18/03/17.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetRecordingUrlResponse implements Serializable{

    public GetRecordingUrlResponse(){}

    private List<Recording> recordings;

    public List<Recording> getRecordings() {
        return recordings;
    }

    public void setRecordings(List<Recording> recordings) {
        this.recordings = recordings;
    }

}
