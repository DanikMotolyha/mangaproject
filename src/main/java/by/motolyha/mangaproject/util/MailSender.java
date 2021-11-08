package by.motolyha.mangaproject.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {

    private static final Logger logger = LogManager.getLogger(MailSender.class);
    private static final Properties props = new Properties();
    private static final String MAIL_PROPERTIES = "/email.properties";
    private static final String MAIL_LOGIN = "mail_login";
    private static final String MAIL_PASSWORD = "mail_password";
    private static final String SUBJECT_NEW_PASSWORD = "NEW PASSWORD";
    private static final String NEW_PASSWORD_MESSAGE = "%s, Hi! This is your new password: %s";

    static {
        try {
            props.load(MailSender.class.getResourceAsStream(MAIL_PROPERTIES));
        } catch (IOException e) {
            logger.log(Level.ERROR, "Properties exception: " + e.getMessage());
        }

    }

    public void sendNewPasswordToMail(String userLogin, String password, String email){
        String message = String.format(NEW_PASSWORD_MESSAGE, userLogin, password);
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        props.getProperty(MAIL_LOGIN),
                        props.getProperty(MAIL_PASSWORD));
            }
        });
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(props.getProperty(MAIL_LOGIN)));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            msg.setSubject(MailSender.SUBJECT_NEW_PASSWORD);
            msg.setText(message);
            Transport.send(msg);
        } catch (MessagingException e) {
            logger.log(Level.ERROR, "SendMailException: " + e.getMessage());
        }
    }
}
