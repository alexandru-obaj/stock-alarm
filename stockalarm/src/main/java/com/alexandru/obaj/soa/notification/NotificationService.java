package com.alexandru.obaj.soa.notification;

import com.alexandru.obaj.soa.config.SoaEmailConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service
@Slf4j
public class NotificationService implements INotificationService {

    private static final String MAIL_USERNAME_KEY = "mail.user";

    private static final String MAIL_PASSWORD_KEY = "mail.password";

    private static final String MAIL_SENDER_KEY = "mail.from";

    private static final String STOCK_KEY = "{STOCK}";

    private static final String VALUE_KEY = "{VALUE}";

    private static final String SENDER_KEY = "{SENDER}";

    private static final String MAIL_SUBJECT = "Stock price variance exceeded threshold";

    private static final String MAIL_CONTENT = "Price for stock {STOCK} reached value {VALUE} which exceeded the configured threshold." +
            "\nYours sincerely,\n{SENDER}";

    private SoaEmailConfig emailConfig;

    public NotificationService(SoaEmailConfig emailConfig) {
        this.emailConfig = emailConfig;
    }

    @Override
    public void sendMail(String emailAddress, String stockSymbol, int value) {
        try {
            Message emailMessage = createMimeMessage(emailAddress, composeMessageContent(stockSymbol, value));
            Transport.send(emailMessage);
        } catch (AddressException ae) {
            log.error("Invalid email address {}", emailAddress);
        } catch (MessagingException me) {
            log.error("Exception occurred during message sending: {}", me.getMessage());
        }
    }


    private String composeMessageContent(String stockSymbol, int value) {
        return new StringBuffer(MAIL_CONTENT).toString().replace(STOCK_KEY, stockSymbol)
                .replace(VALUE_KEY, String.valueOf(value))
                .replace(SENDER_KEY, emailConfig.getEmailProperties().getProperty(MAIL_SENDER_KEY));
    }


    private Message createMimeMessage(String targetEmailAddress, String messageContent) throws MessagingException {
        Properties emailProperties = emailConfig.getEmailProperties();
        Session emailSession = Session.getInstance(emailProperties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailProperties.getProperty(MAIL_USERNAME_KEY), MAIL_PASSWORD_KEY);
            }
        });

        Message message = new MimeMessage(emailSession);
        message.setHeader("Content-Type", "text/plain; charset=UTF-8");
        message.setFrom(new InternetAddress(emailProperties.getProperty(MAIL_SENDER_KEY)));
        message.setSentDate(new Date());
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(targetEmailAddress));
        message.setSubject(MAIL_SUBJECT);
        message.setContent(messageContent, "text/plain; charset=UTF-8");
        return message;
    }
}
