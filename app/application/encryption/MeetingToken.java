package application.encryption;

import application.enums.USER_TYPE;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by pkonwar on 1/10/2017.
 */
public class MeetingToken implements Serializable {

    private Long meetingId;     //the meeting id
    private Long teacherId;     //the teachers id
    private Date startDateTime;   //the start date time
    private USER_TYPE userType;   //the user type

    public MeetingToken(Long meetingId, Long teacherId, Date startDateTime, USER_TYPE userType) {
        this.meetingId = meetingId;
        this.teacherId = teacherId;
        this.startDateTime = startDateTime;
        this.userType = userType;
    }

    public MeetingToken() {
    }

    /**
     * Encode the meeting id
     *
     * @return
     */
    public String encode() {
        return "ENCODED_STRING";
    }

    /**
     * decode the meeting id
     *
     * @param encodedToken
     * @return
     */
    public MeetingToken decode(String encodedToken) {
        return null;
    }


    public Long getMeetingId() {
        return meetingId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public USER_TYPE getUserType() {
        return userType;
    }
}
