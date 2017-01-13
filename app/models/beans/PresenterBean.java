package models.beans;

import application.enums.STATUS;

import java.io.Serializable;

/**
 * Created by pkonwar on 1/9/2017.
 */
public class PresenterBean implements Serializable {

    private Long id;
    private String presenterName;
    private String emailId;
    private String password;
    private byte[] imageBlob;
    private STATUS status;

    public PresenterBean() {
    }

    public PresenterBean(Long id) {
        this.id = id;
    }

    public PresenterBean(Long id, String presenterName, String emailId, String password, byte[] imageBlob, STATUS status) {
        this.id = id;
        this.presenterName = presenterName;
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

    public String getPresenterName() {
        return presenterName;
    }

    public void setPresenterName(String presenterName) {
        this.presenterName = presenterName;
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
