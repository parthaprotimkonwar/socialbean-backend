package rest.bean.response;

import application.utilities.ImageUtils;
import models.Presenter;

/**
 * Created by pkonwar on 2/5/2017.
 */
public class PresenterResponse {

    private Long id;
    private String presenterName;
    private String emailId;
    private String designation;
    private String department;
    private String imageBlob;

    public PresenterResponse(Presenter presenter) {
        this.id = presenter.getId();
        this.presenterName = presenter.getPresenterName();
        this.emailId = presenter.getEmailId();
        this.designation = presenter.getDesignation();
        this.department = presenter.getDepartment();
        this.imageBlob = ImageUtils.fromByteToBase64encodedImage(presenter.getImageBlob());
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

    public String getImageBlob() {
        return imageBlob;
    }

    public void setImageBlob(String imageBlob) {
        this.imageBlob = imageBlob;
    }
}
