package models;

import application.utilities.Constants;
import models.beans.MeetingBean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by pkonwar on 1/9/2017.
 */
@Entity
@Table(name = "MEETING", schema = Constants.MEETING_SCHEDULES)
public class Meeting implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE", nullable = false, length = 60)
    private String title;

    @Column(name = "START_DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateTime;

    @Column(name = "DURATION", nullable = false)
    private Integer duration;

    @Column(name = "PRESENTER_TOKEN", length = 60)
    private String presenterToken;

    @Column(name = "ATTENDEES_TOKEN", length = 60)
    private String attendeesToken;

    @Column(name = "RECORDED_URL", length = 60)
    private String recordedUrl;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ID")
    private Teacher teacher;


    public Meeting(String title, Date startDateTime, Integer duration, String presenterToken, String attendeesToken, String recordedUrl, Teacher teacher) {
        this.title = title;
        this.startDateTime = startDateTime;
        this.duration = duration;
        this.presenterToken = presenterToken;
        this.attendeesToken = attendeesToken;
        this.recordedUrl = recordedUrl;
        this.teacher = teacher;
    }

    public Meeting() {
    }

    /**
     * Convert to meeting bean
     *
     * @return
     */
    public MeetingBean toMeetingBean() {
        return new MeetingBean(this.getId(), this.getTitle(), this.getStartDateTime(), this.getDuration(), this.getPresenterToken(), this.getAttendeesToken(), this.getRecordedUrl(), this.getTeacher().toTeacherBean());
    }

    public Long getId() {
        return id;
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

    public void setPresenterToken(String presenterUrl) {
        this.presenterToken = presenterUrl;
    }

    public String getAttendeesToken() {
        return attendeesToken;
    }

    public void setAttendeesToken(String attendeesUrl) {
        this.attendeesToken = attendeesUrl;
    }

    public String getRecordedUrl() {
        return recordedUrl;
    }

    public void setRecordedUrl(String recordedUrl) {
        this.recordedUrl = recordedUrl;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
