package upf.engsoft.notificationmail.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MailSender {

	@Autowired
    private JavaMailSender javaMailSender;
	
	@Value("${data.mailto}")
	private String mailTo;
	
	/**
	 * 
	 * With the given file send the report with the file as attachment
	 * 
	 * @param file
	 * @throws MessagingException
	 */
	public void sendEmailWithAttachment(File file) throws MessagingException {
		
		log.info("sendEmailWithAttachment() - START - sending e-mail with file [{}]", file.getName());
		
		MimeMessage msg = javaMailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		
		helper.setTo(mailTo);
		
		helper.setText("Relatorio de inscrições");
		
		helper.setSubject("Bom dia, Segue o relatorio com as novas incrições realizadas.");
		
		helper.addAttachment("NewSubscriptions.csv", file);
		
		javaMailSender.send(msg);
		
		log.info("sendEmailWithAttachment() - END file [{}] sended", file.getName());
		
	}
	
}
