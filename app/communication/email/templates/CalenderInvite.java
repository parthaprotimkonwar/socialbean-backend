package communication.email.templates;

/**
 * Created by pkonwar on 2/9/2017.
 */
public class CalenderInvite implements Template {

    @Override
    public String getTemplateString() {
        String calenderInvite = "BEGIN:VCALENDAR\n" +
                "PRODID:-//Microsoft Corporation//Outlook 9.0 MIMEDIR//EN\n" +
                "VERSION:2.0\n" +
                "METHOD:REQUEST\n" +
                "BEGIN:VEVENT\n" +
                "ATTENDEE;ROLE=REQ-PARTICIPANT;RSVP=TRUE:MAILTO:`to_email`\n" +
                "ORGANIZER:MAILTO:`to_email`\n" +
                "DTSTART:`start_time`\n" +
                "DTEND:`end_time`\n" +
                "LOCATION:`conference_location`\n" +
                "TRANSP:OPAQUE\n" +
                "SEQUENCE:0\n" +
                "UID:`unique_id`\n" +
                "DTSTAMP:`current_time`\n" +
                "CATEGORIES:Meeting\n" +
                "DESCRIPTION:`meeting_description`\n\n" +
                "SUMMARY:`meeting_topic`\n" +
                "PRIORITY:5\n" +
                "CLASS:PUBLIC\n" +
                "BEGIN:VALARM\n" +
                "TRIGGER:PT1440M\n" +
                "ACTION:DISPLAY\n" +
                "DESCRIPTION:Reminder\n" +
                "END:VALARM\n" +
                "END:VEVENT\n" +
                "END:VCALENDAR";
        return calenderInvite;
    }
}
