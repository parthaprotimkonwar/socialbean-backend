package communication.email.client;

import application.enums.STATUS;
import communication.email.processor.Fields;
import communication.email.processor.PrepareEmailMessages;
import communication.email.templates.TEMPLATES;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Session;
import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * Created by pkonwar on 2/9/2017.
 */
public class SendEmail implements Callable {

    private String from;
    private String to;
    private String subject;
    private Fields meetingInviteFields;
    private Fields calenderInviteFields;

    public SendEmail(String from, String to, String subject, Fields meetingInviteFields, Fields calenderInviteFields) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.meetingInviteFields = meetingInviteFields;
        this.calenderInviteFields = calenderInviteFields;
    }


    public String sendMeetingInvite() throws IOException, MessagingException {

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
        return STATUS.SUCCESS.name();
    }

    @Override
    public Object call() throws Exception {
        return sendMeetingInvite();
    }
}
