package models.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by pkonwar on 1/9/2017.
 */
public class MeetingBean implements Serializable {

    private Long id;
    private String title;
    private Date startDateTime;
    private Integer duration;
    private String presenterToken;
    private String attendeesUrl;
    private String recordedUrl;
    private PresenterBean presenterBean;

    public MeetingBean() {}

    public MeetingBean(Long id, String title, Date startDateTime, Integer duration, String presenterToken, String attendeesUrl, String recordedUrl, PresenterBean presenterBean) {
        this.id = id;
        this.title = title;
        this.startDateTime = startDateTime;
        this.duration = duration;
        this.presenterToken = presenterToken;
        this.attendeesUrl = attendeesUrl;
        this.recordedUrl = recordedUrl;
        this.presenterBean = presenterBean;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getPresenterToken() {
        return presenterToken;
    }

    public void setPresenterToken(String presenterToken) {
        this.presenterToken = presenterToken;
    }

    public String getAttendeesUrl() {
        return attendeesUrl;
    }

    public void setAttendeesUrl(String attendeesUrl) {
        this.attendeesUrl = attendeesUrl;
    }

    public String getRecordedUrl() {
        return recordedUrl;
    }

    public void setRecordedUrl(String recordedUrl) {
        this.recordedUrl = recordedUrl;
    }

    public PresenterBean getPresenterBean() {
        return presenterBean;
    }

    public void setPresenterBean(PresenterBean presenterBean) {
        this.presenterBean = presenterBean;
    }
}
