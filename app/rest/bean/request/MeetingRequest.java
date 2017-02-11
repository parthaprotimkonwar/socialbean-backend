package rest.bean.request;

import application.utilities.Util;
import models.beans.MeetingBean;
import models.beans.PresenterBean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by pkonwar on 1/13/2017.
 */
public class MeetingRequest implements Serializable {

    private Long presenterId;
    private Long meetingId;
    private String title;
    private String description;
    private String startDateTime;
    private Integer duration;
    private String presenterToken;
    private String attendeesToken;
    private String recordedUrl;
    private String invitees;


    public MeetingRequest() {
    }

    public MeetingRequest(Long presenterId, Long meetingId, String title, String description, String startDateTime, Integer duration, String presenterToken, String attendeesToken, String recordedUrl, String invitees) {
        this.presenterId = presenterId;
        this.meetingId = meetingId;
        this.title = title;
        this.description = description;
        this.startDateTime = startDateTime;
        this.duration = duration;
        this.presenterToken = presenterToken;
        this.attendeesToken = attendeesToken;
        this.recordedUrl = recordedUrl;
        this.invitees = invitees;
    }

    public MeetingBean toMeetingBean() {
        PresenterBean presenterBean = new PresenterBean(this.presenterId);

        Date timeOfMeetingStart = Util.convertStringDateTimeToDate(startDateTime);
        MeetingBean meetingBean = new MeetingBean(meetingId, title, description, timeOfMeetingStart, duration, presenterToken, attendeesToken, recordedUrl, presenterBean);
        meetingBean.setInvitees(this.invitees);
        return meetingBean;
    }

    public Long getPresenterId() {
        return presenterId;
    }

    public void setPresenterId(Long presenterId) {
        this.presenterId = presenterId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
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

    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
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
