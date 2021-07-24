package com.tranquyet.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CheckScheduling {

	private static final Logger log = LoggerFactory.getLogger(CheckScheduling.class);
	@Autowired
	private JavaMailSender javaSenderMail;
	/*
	 * @Scheduled(cron = "1 * * * * ?") public void sendEmail() {
	 * log.info("Hello Kai Welcome to chanel!"); }
	 */

	@Scheduled(cron = "1 50 17 * * ?")
	public void checkEmail() {
		SimpleMailMessage msg = new SimpleMailMessage();

		msg.setTo("quyettran057@gmail.com");

		msg.setSubject("Testing for send email");

		msg.setText("Hello World");

		javaSenderMail.send(msg);
		log.warn("Check email Scheduling!");
	}


}
