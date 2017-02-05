package models;

import application.enums.STATUS;
import application.utilities.Constants;
import models.beans.PresenterBean;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by pkonwar on 1/9/2017.
 */
@Entity
@Table(name = "PRESENTER", schema = Constants.USER_MANAGEMENT)
public class Presenter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "PRESENTER_ID")
    private Long id;

    @Column(name = "PRESENTER_NAME", nullable = false, length = 30)
    private String presenterName;

    @Column(name = "EMAIL_ID", nullable = false, length = 40, unique = true)
    private String emailId;

    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;

    @Column(name = "DESIGNATION", length = 40)
    private String designation;

    @Column(name = "DEPARTMENT", length = 40)
    private String department;

    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "IMAGE_BLOB")
    private byte[] imageBlob;

    @Column(name = "STATUS")
    @Enumerated(value = EnumType.ORDINAL)
    private STATUS status;

    @OneToMany(mappedBy = "presenter")
    private List<Meeting> meetingList;

    public Presenter(Long id, String presenterName, String emailId, String password, String designation, String department, byte[] imageBlob, STATUS status) {
        this.id = id;
        this.presenterName = presenterName;
        this.emailId = emailId;
        this.password = password;
        this.designation = designation;
        this.department = department;
        this.imageBlob = imageBlob;
        this.status = status;
    }

    public Presenter(String presenterName, String emailId, String password, String designation, String department, byte[] imageBlob, STATUS status) {
        this.presenterName = presenterName;
        this.emailId = emailId;
        this.password = password;
        this.designation = designation;
        this.department = department;
        this.imageBlob = imageBlob;
        this.status = status;
    }

    public Presenter() {
    }

    /**
     * Converts to presenter bean
     *
     * @return
     */
    public PresenterBean toPresenterBean() {
        return new PresenterBean(this.getId(), this.getPresenterName(), this.getEmailId(), this.getPassword(), designation, department, this.getImageBlob(), this.getStatus());
    }


    public Long getId() {
        return id;
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

    public List<Meeting> getMeetingList() {
        return meetingList;
    }

    public void setMeetingList(List<Meeting> meetingList) {
        this.meetingList = meetingList;
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
}
