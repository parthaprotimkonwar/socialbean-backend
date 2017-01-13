package application.enums;

/**
 * Created by pkonwar on 1/11/2017.
 */
public enum MEETING_TYPE {

    ALL_MEETING("ALL"),
    PAST_MEETING("PAST"),
    UPCOMMING_MEETING("NEW");

    String type;
    MEETING_TYPE(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

    public static MEETING_TYPE findMeetingType(String type) {

        for(MEETING_TYPE meetingType : MEETING_TYPE.values()) {
            if(meetingType.type.equals(type)) {
                return meetingType;
            }
        }
        return MEETING_TYPE.UPCOMMING_MEETING;
    }
}
