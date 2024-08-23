package br.com.samiac.emailservice.sender;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import br.com.samiac.emailservice.env.EnvLoader;
import br.com.samiac.emailservice.model.entity.Attachment;
import br.com.samiac.emailservice.model.entity.Email;
import br.com.samiac.emailservice.model.entity.EmailBody;

public class Sender {
	private static final String SMTP_AUTH = "mail.smtp.auth";
    private static final String SMTP_STARTTLS = "mail.smtp.starttls.enable";
    private static final String SMTP_HOST = "mail.smtp.host";
    private static final String SMTP_PORT = "mail.smtp.port";
    private static final String EMAIL_HOST = "smtp-mail.outlook.com";
    private static final String EMAIL_PORT = "587";

    public static void sendEmail(Email email) {
    	try {
            Session session = createEmailSession();
            Message message = createMessage(session, email);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static Session createEmailSession() {
        Properties properties = new Properties();
        properties.put(SMTP_AUTH, "true");
        properties.put(SMTP_STARTTLS, "true");
        properties.put(SMTP_HOST, EMAIL_HOST);
        properties.put(SMTP_PORT, EMAIL_PORT);

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EnvLoader.botEmail, EnvLoader.botPass);
            }
        };

        return Session.getInstance(properties, auth);
    }
    
    private static Message createMessage(Session session, Email email)
            throws MessagingException {
    	String recipient = email.getRecipient();
    	String subject = email.getSubject();
    	EmailBody body = email.getBody();
    	
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(EnvLoader.botEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject(subject);
        message.setSentDate(new java.util.Date());
        
        Multipart multipart = createMultipart(body);
        message.setContent(multipart);

        return message;
    }

    private static Multipart createMultipart(EmailBody body) throws MessagingException {
        String message = body.getMessage();
        Attachment attachment = body.getAttachment();
    	
    	MimeBodyPart textPart = createTextPart(message);
        MimeBodyPart attachmentPart = createAttachmentPart(attachment);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(textPart);
        multipart.addBodyPart(attachmentPart);

        return multipart;
    }

    private static MimeBodyPart createTextPart(String message) throws MessagingException {
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setText(message);
        return textPart;
    }

    private static MimeBodyPart createAttachmentPart(Attachment attachment) throws MessagingException {
    	String contentType = attachment.getContentType();
    	byte[] attachmentBytes = attachment.parse();
    	
        DataSource dataSource = new ByteArrayDataSource(attachmentBytes, contentType);

        MimeBodyPart attachmentPart = new MimeBodyPart();
        attachmentPart.setDataHandler(new DataHandler(dataSource));
        attachmentPart.setFileName(generateAttachmentFileName());

        return attachmentPart;
    }

    private static String generateAttachmentFileName() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss_dd-MM-yyyy");
        return "relatorio_integrador_certificacoes_" + LocalDateTime.now().format(formatter) + ".pdf";
    }
}
