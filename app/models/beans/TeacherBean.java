package models.beans;

import application.enums.STATUS;

import java.io.Serializable;

/**
 * Created by pkonwar on 1/9/2017.
 */
public class TeacherBean implements Serializable {

    private Long id;
    private String teacherName;
    private String emailId;
    private String password;
    private byte[] imageBlob;
    private STATUS status;


    public TeacherBean(Long id, String teacherName, String emailId, String password, byte[] imageBlob, STATUS status) {
        this.id = id;
        this.teacherName = teacherName;
        this.emailId = emailId;
        this.password = password;
        this.imageBlob = imageBlob;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
