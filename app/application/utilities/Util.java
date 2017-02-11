package application.utilities;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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


    //Convert date into YEAR-MONTH-DAY-T-HOURS-MINUTES-SEC-Z
    //Adds hour to the date object
    public static String convertDateToString(Date date, int addHours) {

        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(date); // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, addHours); // adds one hour
        return convertDateToString(cal.getTime()); // returns new date object
    }

    //Convert date into YEAR-MONTH-DAY-T-HOURS-MINUTES-SEC-Z
    //This format is used for CalenderInvite
    public static String convertDateToString(Date date) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
        String reportDate = df.format(date);
        return reportDate;
    }

    /*public static void main(String[] args) {
        //System.out.println(convertStringDateTimeToDate("05/30/2017 12:34:23"));
        System.out.println(convertDateToString(new Date()));
        System.out.println(convertDateToString(new Date(), 1));
    }*/
}
