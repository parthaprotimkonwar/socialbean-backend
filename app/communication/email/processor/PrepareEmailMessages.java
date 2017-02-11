package communication.email.processor;

import communication.email.processor.Fields;
import communication.email.processor.TemplateFactory;
import communication.email.processor.TemplateFieldsFacade;
import communication.email.templates.TEMPLATES;
import communication.email.templates.Template;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;

/**
 * Created by pkonwar on 2/9/2017.
 */
public class PrepareEmailMessages {


    public BodyPart prepareBodyContent(TEMPLATES templateType, Fields fields) throws MessagingException, IOException {

        Template template = new TemplateFactory().getTemplate(templateType);
        //template content replaced
        String bodyContent  = new TemplateFieldsFacade(template, fields).merge();

        BodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(bodyContent, "text/html; charset=utf-8");
        return bodyPart;

    }


    public BodyPart prepareCalenderInvite(TEMPLATES templateType, Fields fields) throws MessagingException, IOException {

        Template calenderInviteTemplate = new TemplateFactory().getTemplate(templateType);
        //template content replaced
        String calenderInviteContent  = new TemplateFieldsFacade(calenderInviteTemplate, fields).merge();
        BodyPart calenderInvitePart = new MimeBodyPart();

        // Fill the message
        calenderInvitePart.setHeader("Content-Class", "urn:content-  classes:calendarmessage");
        calenderInvitePart.setHeader("Content-ID", "calendar_message");
        calenderInvitePart.setDataHandler(new DataHandler(
                new ByteArrayDataSource(calenderInviteContent, "text/calendar")));// very important

        return calenderInvitePart;

    }
}
