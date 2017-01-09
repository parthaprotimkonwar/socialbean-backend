package application.encryption;

import application.enums.USER_TYPE;

import java.io.*;
import java.util.Base64;
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
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new ByteArrayOutputStream());
            objectOutputStream.writeObject(this);
            objectOutputStream.flush();
            return new String(Base64.getEncoder().encode(byteArrayOutputStream.toByteArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * decode the meeting id
     *
     * @param encodedToken
     * @return
     */
    public MeetingToken decode(String encodedToken) {
        try {
            byte encodedByteArray[] = Base64.getDecoder().decode(encodedToken.getBytes());
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(encodedByteArray);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

            return (MeetingToken) (objectInputStream.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
