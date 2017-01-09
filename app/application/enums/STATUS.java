package application.enums;

/**
 * Created by pkonwar on 5/11/2016.
 */
public enum STATUS {

    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    CONFIRMATION_PENDING("CONFIRMATION_PENDING"),
    SUCCESS("SUCCESS"),
    FAILURE("FAILURE");

    String status;

    private STATUS(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
