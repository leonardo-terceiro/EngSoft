package upf.engsoft.notificationmail.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import upf.engsoft.notificationmail.entity.SubscriberEntity;
import upf.engsoft.notificationmail.repository.SubscriberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SubscriptionService.class, SubscriberRepository.class })
public class SubscriptionServiceTest {

	@Autowired
	private SubscriptionService service;
	
	@MockBean
	private SubscriberRepository subscriberRepository;

	@Test
	public void loadSubscriptions() {
		
		service.loadSubscriptions();
		
		verify(subscriberRepository, times(1)).findByStatus("INITIAL");
	}
	
	@Test
	public void updateStatus() {
		
		List<SubscriberEntity> loadSubscriptions = new ArrayList<>();
		String status = "FINISHED";
		service.updateStatus(loadSubscriptions, status);
		
		verify(subscriberRepository, times(1)).saveAll(loadSubscriptions);
	}
	
}
