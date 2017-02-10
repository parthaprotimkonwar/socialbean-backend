package communication.email.client;

import communication.email.processor.Fields;
import communication.email.templates.TEMPLATES;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Session;
import java.io.IOException;

/**
 * Created by pkonwar on 2/9/2017.
 */
public class SendEmail {


    public void sendMeetingInvite(String from, String to, String subject, Fields meetingInviteFields, Fields calenderInviteFields) throws IOException, MessagingException {

        EmailClient emailClient = new EmailClient();
        Session session = emailClient.setup();    //setup

        PrepareEmailMessages emailMessages = new PrepareEmailMessages();
        //One part of the body
        BodyPart htmlPart = emailMessages.prepareBodyContent(TEMPLATES.MEETING_INVITE_EMAIL_TEMPLATE, meetingInviteFields);

        //The calender invite part
        BodyPart calenderInvite = emailMessages.prepareCalenderInvite(TEMPLATES.CALENDER_INVITE, calenderInviteFields);

        emailClient.addMesageBody(calenderInvite);
        emailClient.addMesageBody(htmlPart);
        //send the message
        emailClient.sendMessage(session, from, to, subject);
    }
}
