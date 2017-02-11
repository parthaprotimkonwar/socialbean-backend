package models.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by pkonwar on 1/9/2017.
 */
public class MeetingBean implements Serializable {

    private Long id;
    private String title;
    private String description;
    private Date startDateTime;
    private String dateTimeInString;
    private Integer duration;
    private String presenterToken;
    private String attendeesToken;
    private String recordedUrl;
    private String invitees;
    private PresenterBean presenterBean;

    public MeetingBean() {
    }

    public MeetingBean(Long id, String title, String description, Date startDateTime, Integer duration, String presenterToken, String attendeesToken, String recordedUrl, PresenterBean presenterBean) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDateTime = startDateTime;
        this.dateTimeInString = startDateTime == null ? "" : startDateTime.toString();
        this.duration = duration;
        this.presenterToken = presenterToken;
        this.attendeesToken = attendeesToken;
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

    public String getDateTimeInString() {
        return dateTimeInString;
    }

    public void setDateTimeInString(String dateTimeInString) {
        this.dateTimeInString = dateTimeInString;
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

    public String getAttendeesToken() {
        return attendeesToken;
    }

    public void setAttendeesToken(String attendeesToken) {
        this.attendeesToken = attendeesToken;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInvitees() {
        return invitees;
    }

    public void setInvitees(String invitees) {
        this.invitees = invitees;
    }
}
