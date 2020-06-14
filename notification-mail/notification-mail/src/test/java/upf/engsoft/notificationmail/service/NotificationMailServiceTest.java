package upf.engsoft.notificationmail.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import upf.engsoft.notificationmail.entity.SubscriberEntity;
import upf.engsoft.notificationmail.exception.NoDataFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { NotificationMailService.class, SubscriptionService.class, WriterService.class, MailSender.class })
public class NotificationMailServiceTest {

	@Autowired
	private NotificationMailService service;

	@MockBean
	private SubscriptionService subscriptionService;
	
	@MockBean
	private WriterService writerService;
	
	@MockBean
    private MailSender mailSender;
	
	@Test
	public void doWork() throws NoDataFoundException, IOException, MessagingException {
		
		List<SubscriberEntity> subscriptions = buildSubscriptions();
		
		when(subscriptionService.loadSubscriptions()).thenReturn(subscriptions);
		
		service.doWork();
		
		verify(subscriptionService, times(1)).loadSubscriptions();
		verify(subscriptionService, times(1)).updateStatus(any(), any());
		verify(mailSender, times(1)).sendEmailWithAttachment(any());
		verify(writerService, times(1)).csvWriter(any(), any());
	}

	@Test(expected = NoDataFoundException.class)
	public void doWorkWithNoLoad() throws NoDataFoundException, IOException, MessagingException {
		
		List<SubscriberEntity> subscriptions = new ArrayList<>();
		
		when(subscriptionService.loadSubscriptions()).thenReturn(subscriptions);
		
		service.doWork();
	}
	
	private List<SubscriberEntity> buildSubscriptions() {
		List<SubscriberEntity> subscriptions = new ArrayList<>();
		
		SubscriberEntity subscriber = new SubscriberEntity();
		subscriber.setId(1L);
		subscriber.setCellphone("5554999999999");
		subscriber.setEmail("teste@teste.com");
		subscriber.setCpf("01234567890");
		subscriber.setLastName("teste");
		subscriber.setName("name");
		subscriber.setStatus("INITIAL");
		subscriptions.add(subscriber);
		return subscriptions;
	}
	
}
