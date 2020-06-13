package upf.engsoft.notificationmail.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import upf.engsoft.notificationmail.entity.SubscriberEntity;
import upf.engsoft.notificationmail.exception.NoDataFoundException;

@Service
public class NotificationMailService {

	@Autowired
	private SubscriptionService subscriptionService;
	
	@Autowired
	private WriterService writerService;
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	public void dowork() throws NoDataFoundException, IOException, MessagingException {
		
		List<SubscriberEntity> loadSubscriptions = loadSubscriptions();
		
		File file = generateFile(loadSubscriptions);
		
		sendEmail(file);
		
		updateStatus(loadSubscriptions);
	}

	private List<SubscriberEntity> loadSubscriptions() throws NoDataFoundException {
		
		List<SubscriberEntity> loadSubscriptions = subscriptionService.loadSubscriptions();
		
		if(loadSubscriptions.isEmpty()) {
			throw new NoDataFoundException("Nenhuma nova inscrição realizada!");
		}
		
		return loadSubscriptions;
	}

	private File generateFile(List<SubscriberEntity> loadSubscriptions) throws IOException {
		
		File file = File.createTempFile("subscription", ".csv");
		
		writerService.csvWriter(file, loadSubscriptions);
		
		return file;
		
	}

	public void sendEmail(File file) throws MessagingException {
		
		MimeMessage msg = javaMailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		
		helper.setTo("163510@upf.br");
		
		helper.setSubject("Teste");
		
		helper.addAttachment("NewSubscriptions", file);
		
		javaMailSender.send(msg);
		
	}
	
	private void updateStatus(List<SubscriberEntity> loadSubscriptions) {
		
		subscriptionService.updateStatus(loadSubscriptions, "FINISHED");
		
	}

}
