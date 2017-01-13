package application.enums;

import java.io.Serializable;

/**
 * Created by pkonwar on 5/11/2016.
 */
public enum USER_TYPE implements Serializable {
    PRESENTER("P"),
    ATTENDEE("A");


    String userType;

    USER_TYPE(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return userType;
    }


    public static USER_TYPE findUserType(String type) {

        for(USER_TYPE userType : USER_TYPE.values()) {
            if(userType.userType.equals(type)) {
                return userType;
            }
        }
        return USER_TYPE.ATTENDEE;
    }
}
