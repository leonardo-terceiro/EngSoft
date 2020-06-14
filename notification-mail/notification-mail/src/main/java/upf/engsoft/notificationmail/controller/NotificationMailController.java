package upf.engsoft.notificationmail.controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import upf.engsoft.notificationmail.exception.NoDataFoundException;
import upf.engsoft.notificationmail.service.NotificationMailService;

@RestController
public class NotificationMailController {

	@Autowired
	private NotificationMailService service;
	
	@PostMapping("/sendMail")
	public ResponseEntity<String> sendEmail() throws NoDataFoundException, IOException, MessagingException{
		
		service.doWork();
		
		return ResponseEntity.status(HttpStatus.OK).body("Processo finalizado!");
	}
	
	
}
