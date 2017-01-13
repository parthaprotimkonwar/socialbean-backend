package application.utilities;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by pkonwar on 1/13/2017.
 */
public class Util {

    /**
     * Convert DateTime in string to Date object
     *
     * @param dateTimeString
     * @return
     */
    public static Date convertStringDateTimeToDate(String dateTimeString) {
        if (dateTimeString == null || dateTimeString.trim().length() == 0)
            return null;

        DateTimeFormatter formatter = DateTimeFormat.forPattern(Constants.DATE_TIME_PATTERN);
        DateTime dateTime = formatter.parseDateTime(dateTimeString);
        Date date = dateTime.toDate();
        return date;
    }


    /*public static void main(String[] args) {
        System.out.println(convertStringDateTimeToDate("05/30/2017 12:34:23"));
    }*/
}
