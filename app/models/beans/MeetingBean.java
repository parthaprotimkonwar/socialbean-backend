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
    private String presenterUrl;
    private String attendeesUrl;
    private String recordedUrl;
    private TeacherBean teacherBean;

    public MeetingBean(Long id, String title, Date startDateTime, Integer duration, String presenterUrl, String attendeesUrl, String recordedUrl, TeacherBean teacherBean) {
        this.id = id;
        this.title = title;
        this.startDateTime = startDateTime;
        this.duration = duration;
        this.presenterUrl = presenterUrl;
        this.attendeesUrl = attendeesUrl;
        this.recordedUrl = recordedUrl;
        this.teacherBean = teacherBean;
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

    public String getPresenterUrl() {
        return presenterUrl;
    }

    public void setPresenterUrl(String presenterUrl) {
        this.presenterUrl = presenterUrl;
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

    public TeacherBean getTeacherBean() {
        return teacherBean;
    }

    public void setTeacherBean(TeacherBean teacherBean) {
        this.teacherBean = teacherBean;
    }
}
