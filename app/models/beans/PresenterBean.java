package models.beans;

import application.enums.STATUS;
import application.utilities.ImageUtils;
import models.Presenter;

import java.io.Serializable;

/**
 * Created by pkonwar on 1/9/2017.
 */
public class PresenterBean implements Serializable {

    private Long id;
    private String presenterName;
    private String emailId;
    private String password;
    private String designation;
    private String department;
    private String imageString;
    private byte[] imageBlob;
    private STATUS status;

    public PresenterBean() {
    }

    public PresenterBean(Long id) {
        this.id = id;
    }

    public PresenterBean(Long id, String presenterName, String emailId, String password, String designation, String department, byte[] imageBlob, STATUS status) {
        this.id = id;
        this.presenterName = presenterName;
        this.emailId = emailId;
        this.password = password;
        this.designation = designation;
        this.department = department;
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;

        setImageBlob(ImageUtils.fromBase64decodedImageToByte(imageString));
    }
}
