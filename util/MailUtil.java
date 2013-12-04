package util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class MailUtil
{  
	public static void sendMail(String to, String from, String subject, String body, boolean bodyIsHTML)
		throws MessagingException
	{
		// 1 - get a mail session
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.EnableSSL.enable", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.put("mail.smtp.socketFactory.port", "465");
		Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("jhu.fabfitfun@gmail.com", "fffshare");
                }
            });
		session.setDebug(true);
		
		// 2 - create a message
		Message message = new MimeMessage(session);
		message.setSubject(subject);
		if (bodyIsHTML)
			message.setContent(body, "text/html");
		else
			message.setText(body);
		
		// 3 - address the message
		Address fromAddress = new InternetAddress(from);
		Address toAddress = new InternetAddress(to);
		message.setFrom(fromAddress);
		message.setRecipient(Message.RecipientType.TO, toAddress);
		
		// 4 - send the message
		Transport.send(message);
	}
}