package upf.engsoft.notificationmail.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import upf.engsoft.notificationmail.entity.SubscriberEntity;
import upf.engsoft.notificationmail.exception.NoDataFoundException;

@Slf4j
@Service
public class NotificationMailService {

	@Autowired
	private SubscriptionService subscriptionService;
	
	@Autowired
	private WriterService writerService;
	
	@Autowired
    private MailSender mailSender;
	
	/**
	 * 
	 * Work is the method responsable to execute all steps to report the new subscriptions
	 * First step: load new subscriptions
	 * Second step: generate the CSV file for the report
	 * Third step: send the email with the CSV file as attchament 
	 * fourth step: update status of the informed subscriptions
	 * 
	 * @throws NoDataFoundException
	 * @throws IOException
	 * @throws MessagingException
	 */
	public void doWork() throws NoDataFoundException, IOException, MessagingException {
		log.info("dowork() - START - working on report");
		
		List<SubscriberEntity> loadSubscriptions = loadSubscriptions();
		
		File file = generateFile(loadSubscriptions);
		
		sendEmail(file);
		
		updateStatus(loadSubscriptions);
		
		log.info("doWork() - END");
	}
	/**
	 * 
	 * Start the reseach for new subscriptions, and validate if has new subscriptions, if not shut down the application
	 * 
	 * @return
	 * @throws NoDataFoundException
	 */
	private List<SubscriberEntity> loadSubscriptions() throws NoDataFoundException {
		log.info("loadSubscriptions() - START - loading subscriptions with INITIAL status");
		
		List<SubscriberEntity> loadSubscriptions = subscriptionService.loadSubscriptions();
		
		if(loadSubscriptions.isEmpty()) {
			log.info("loadSubscriptions() - END - no Data found, finishing the process");
			throw new NoDataFoundException("Nenhuma nova inscrição realizada!");
		}
		
		log.info("loadSubscriptions() - END - loaded [{}] subscriptions", loadSubscriptions.size());
		return loadSubscriptions;
	}

	/**
	 * 
	 * Create the file that will be filled with the subscriptions.
	 * Start the writting file step
	 * 
	 * @param loadSubscriptions
	 * @return
	 * @throws IOException
	 */
	private File generateFile(List<SubscriberEntity> loadSubscriptions) throws IOException {
		log.info("generateFile() - START - generating report file");
		
		File file = File.createTempFile("subscription", ".csv");
		
		writerService.csvWriter(file, loadSubscriptions);
		
		log.info("generateFile() - END - file [{}] generated", file.getName());
		return file;
		
	}

	/**
	 * 
	 * Calls the service to send the email
	 * 
	 * @param file
	 * @throws MessagingException
	 */
	private void sendEmail(File file) throws MessagingException {
		log.info("sendEmail() - START - sending e-mail with file [{}]", file.getName());
		
		mailSender.sendEmailWithAttachment(file);
		
		log.info("sendEmail() - END file [{}] sended", file.getName());
		
	}
	
	/**
	 * 
	 * Set the subscriptions status as finished, meaning they have already been reported
	 * 
	 * @param loadSubscriptions
	 */
	private void updateStatus(List<SubscriberEntity> loadSubscriptions) {
		log.info("updateStatus() - START - changing status to FINISHED for [{}] subscriptions", loadSubscriptions.size());
		
		subscriptionService.updateStatus(loadSubscriptions, "FINISHED");
		
		log.info("updateStatus() - END - sucessuful changed status for [{}] subscriptions", loadSubscriptions.size());
	}

}
