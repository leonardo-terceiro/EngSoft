package upf.engsoft.subscriptioncore.service;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import upf.engsoft.subscriptioncore.exception.SubscriberNotFoundException;
import upf.engsoft.subscriptioncore.repository.SubscriberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SubscriberCoreService.class, SubscriberRepository.class })
public class SubscriberCoreServiceTest {

	@Autowired
	private SubscriberCoreService service;
	
	@MockBean
	private SubscriberRepository repository;
	
	@Test
	public void getSubscribers() {
		
		service.getSubscribers();
		
		verify(repository, times(1)).findAll();
	}
	
	@Test
	public void getSubscriber() throws SubscriberNotFoundException {
		
		service.getSubscriber(1L);
		
		verify(repository, times(1)).findById(1L);
		
	}
	
	@Test(expected = SubscriberNotFoundException.class)
	public void getSubscriberWithNotFoundException() throws SubscriberNotFoundException {
		
		doThrow(new SubscriberNotFoundException()).when(repository).findById(1L);

		service.getSubscriber(1L);
		
	}
}
