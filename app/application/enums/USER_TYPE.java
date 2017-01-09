package application.enums;

import java.io.Serializable;

/**
 * Created by pkonwar on 5/11/2016.
 */
public enum USER_TYPE implements Serializable {
    PRESENTER("PRESENTER"),
    ATTENDEE("ATTENDEE");


    String userType;

    private USER_TYPE(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return userType;
    }
}
