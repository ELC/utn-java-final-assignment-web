package util;

import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import data.ProjectConfiguration;

public class Emailer {
	public static Emailer instance;
	private Logger logger = LogManager.getLogger(getClass());
	private Properties props;
	
	public static Emailer getInstance() throws Exception{
		if (instance == null){
			instance = new Emailer();
		}
		return instance;
	}
	
	private Emailer() throws Exception {
		ProjectConfiguration pConf = new ProjectConfiguration();
		try {
			props = pConf.getProperties();
		} catch (IOException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw e;
		}
	}
	
	public void send(String to, String subject, String body){
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(props.getProperty("mail.username"), props.getProperty("mail.password"));
			}
		  });

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(props.getProperty("mail.username")));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(body);

			Transport.send(message);

		} catch (MessagingException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw new RuntimeException(e);
		}
	}

}