package application.encryption;

import application.enums.USER_TYPE;
import application.utilities.Constants;

import java.io.*;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

/**
 * Created by pkonwar on 1/10/2017.
 */
public class MeetingToken implements Serializable {

    private Long meetingId;     //the meeting id
    private Long presenterId;     //the presenter id
    private Date startDateTime;   //the start date time
    private USER_TYPE userType;   //the user type

    public MeetingToken(Long meetingId, Long presenterId, Date startDateTime, USER_TYPE userType) {
        this.meetingId = meetingId;
        this.presenterId = presenterId;
        this.startDateTime = startDateTime;
        this.userType = userType;
    }

    public MeetingToken() {
    }

    /**
     * Generate a random meeting token
     *
     * @return
     */
    public String createRandomMeetingToken() {
        final String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final int N = alphabet.length();
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Constants.RANDOM_MEETING_TOKEN_LENGTH; i++) {
            sb.append(alphabet.charAt(r.nextInt(N)));
        }
        return sb.toString();
    }

    /**
     * Encode the meeting id
     *
     * @return
     */
    public String encode() {
        /*try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new ByteArrayOutputStream());
            objectOutputStream.writeObject(this);
            objectOutputStream.flush();
            return new String(Base64.getEncoder().encode(byteArrayOutputStream.toByteArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return createRandomMeetingToken();
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

    public static void main(String[] args) {
        MeetingToken mt = new MeetingToken();
        System.out.println(mt.createRandomMeetingToken());
    }

    public Long getMeetingId() {
        return meetingId;
    }

    public Long getPresenterId() {
        return presenterId;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public USER_TYPE getUserType() {
        return userType;
    }
}
