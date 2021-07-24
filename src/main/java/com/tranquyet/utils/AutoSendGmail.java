package com.tranquyet.utils;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class AutoSendGmail {

	@Autowired
	JavaMailSender javaSendMail;

	public void sendGmail() {

		SimpleMailMessage msg = new SimpleMailMessage();

		msg.setTo("quyettran057@gmail.com");

		msg.setSubject("Testing for send email");

		msg.setText("Hello World");

		javaSendMail.send(msg);
	}

	void sendEmailWithAttachment() throws MessagingException, IOException {

		MimeMessage msg = javaSendMail.createMimeMessage();

		// true = multipart message
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		helper.setTo("1@gmail.com");

		helper.setSubject("Testing from Spring Boot");

		// default = text/plain
		// helper.setText("Check attachment for image!");

		// true = text/html
		helper.setText("<h1>Check attachment for image!</h1>", true);

		helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

		javaSendMail.send(msg);

	}
}
