package com.apt.msa.mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationSender {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private  JavaMailSender emailSender;

	public void sendSimpleMessage( final Mail mail)  {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        message.setTo(mail.getTo());
        message.setFrom(mail.getFrom());

        emailSender.send(message);
		logger.info("Sending...");
		logger.info("Done!");	
  }
}