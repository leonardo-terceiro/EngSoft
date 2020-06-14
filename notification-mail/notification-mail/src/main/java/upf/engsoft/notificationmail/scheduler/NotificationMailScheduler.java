package upf.engsoft.notificationmail.scheduler;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import upf.engsoft.notificationmail.exception.NoDataFoundException;
import upf.engsoft.notificationmail.service.NotificationMailService;

@Component
public class NotificationMailScheduler {

	@Autowired
	private NotificationMailService service;
	
	@Scheduled(cron = "${data.cron}", zone = "${data.timezone}")
	public void scheduler() throws NoDataFoundException, IOException, MessagingException {
		service.doWork();
	}
	
}
