package models;

import application.enums.STATUS;
import application.utilities.Constants;
import models.beans.TeacherBean;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by pkonwar on 1/9/2017.
 */
@Entity
@Table(name = "PRESENTER", schema = Constants.USER_MANAGEMENT)
public class Teacher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TEACHER_NAME", nullable = false, length = 30)
    private String teacherName;

    @Column(name = "EMAIL_ID", nullable = false, length = 40, unique = true)
    private String emailId;

    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;

    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "IMAGE_BLOB")
    private byte[] imageBlob;

    @Column(name = "STATUS")
    @Enumerated(value = EnumType.ORDINAL)
    private STATUS status;

    @OneToMany(mappedBy = "teacher")
    private List<Meeting> meetingList;

    public Teacher(String teacherName, String emailId, String password, byte[] imageBlob, STATUS status) {
        this.teacherName = teacherName;
        this.emailId = emailId;
        this.password = password;
        this.imageBlob = imageBlob;
        this.status = status;
    }

    public Teacher() {
    }

    /**
     * Converts to teacher bean
     *
     * @return
     */
    public TeacherBean toTeacherBean() {
        return new TeacherBean(this.getId(), this.getTeacherName(), this.getEmailId(), this.getPassword(), this.getImageBlob(), this.getStatus());
    }

    public Long getId() {
        return id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getImageBlob() {
        return imageBlob;
    }

    public void setImageBlob(byte[] imageBlob) {
        this.imageBlob = imageBlob;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public List<Meeting> getMeetingList() {
        return meetingList;
    }

    public void setMeetingList(List<Meeting> meetingList) {
        this.meetingList = meetingList;
    }
}
