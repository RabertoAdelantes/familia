package com.ra.familia.services;

import java.util.Base64;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.dao.PropertiesManager;
import com.ra.familia.entities.PersonBean;

public class MailService
{
	private static final Logger LOG = LoggerFactory
			.getLogger(MailService.class);
	private static final Properties mailServerProperties = PropertiesManager.getMailProperties();

	private static final String HOST = mailServerProperties.getProperty("mail.smtp.host");
	private static final String ACC = mailServerProperties.getProperty("mail.account");
	private static final String KEY = mailServerProperties.getProperty("mail.key");
	
	private static final String REG_SUBJECT = mailServerProperties.getProperty("mail.register.subject");
	private static final String REG_BODY = mailServerProperties.getProperty("mail.register.body");

 
	public void sendRegistrationMail(final PersonBean person)
	{
		send(String.format(REG_BODY,person.getFirstName(), person.getEmail(),person.getPassword()), REG_SUBJECT, person.getEmail());
	}
	
	private void send(final String emailBody, String subject, final String receipient)   {
		try
		{
		Session mailSession = Session.getDefaultInstance(mailServerProperties, null);
		MimeMessage generateMailMessage = new MimeMessage(mailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(receipient)); 
		generateMailMessage.setSubject(subject);
		generateMailMessage.setContent(emailBody, "text/html");
 
		Transport transport = mailSession.getTransport("smtp"); 
		transport.connect(HOST, decode(ACC), decode(KEY));
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
		}
		catch (MessagingException  msex)
		{
			LOG.error(">>> MailService :" + msex.getLocalizedMessage());
		}
	}
	
	private String decode(String inputValue)
	{
		return new String(Base64.getDecoder().decode(inputValue.getBytes()));
	}
	

}