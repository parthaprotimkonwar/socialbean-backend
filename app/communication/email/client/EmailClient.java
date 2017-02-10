package communication.email.client;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * Created by pkonwar on 2/9/2017.
 */
public class EmailClient {

    // Create a Multipart
    Multipart multipart = new MimeMultipart();

    public Session setup() {

        final String username = "myn61820@gmail.com";
        final String password = "myn61820!";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        return session;
    }

    public void addMesageBody(BodyPart bodyPart) throws MessagingException {
        multipart.addBodyPart(bodyPart);
    }


    public void sendMessage(Session session, String from, String to, String subject) throws MessagingException {
        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));
        message.setSubject(subject);

        // Put parts in message
        message.setContent(multipart);

        Transport.send(message);
    }


}
