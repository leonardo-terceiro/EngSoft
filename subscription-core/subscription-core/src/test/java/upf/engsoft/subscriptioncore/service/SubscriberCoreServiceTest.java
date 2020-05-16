package upf.engsoft.subscriptioncore.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import upf.engsoft.subscriptioncore.dto.SubscriberDTO;
import upf.engsoft.subscriptioncore.dto.SucessResponseDTO;
import upf.engsoft.subscriptioncore.entity.SubscriberEntity;
import upf.engsoft.subscriptioncore.exception.SubscriberNotFoundException;
import upf.engsoft.subscriptioncore.exception.UnexpectedErrorException;
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
		
		when(repository.findById(1L)).thenReturn(Optional.empty());
		
		service.getSubscriber(1L);
		
	}
	
	@Test
	public void saveSubscriber() {
		SubscriberDTO subscriber = buildSubscription();
		SubscriberEntity subEnt = buildSubEntity();
		SubscriberEntity flushedSubEnt = buildFlushedSubEntity();
		
		when(repository.saveAndFlush(subEnt)).thenReturn(flushedSubEnt);
		
		SucessResponseDTO saveSubscriber = service.saveSubscriber(subscriber);
		
		assertNotNull(saveSubscriber);
		assertEquals("200", saveSubscriber.getCode());
		assertEquals("OK", saveSubscriber.getStatus());
		verify(repository, times(1)).saveAndFlush(subEnt);
		
	}

	@Test(expected = UnexpectedErrorException.class)
	public void saveWithUnexpectError() {
		SubscriberDTO subscriber = buildSubscription();
		SubscriberEntity subEnt = buildSubEntity();
		
		when(repository.saveAndFlush(subEnt)).thenThrow(new RuntimeException());
		
		service.saveSubscriber(subscriber);
		
	}
	
	@Test
	public void updateSubscriber() throws SubscriberNotFoundException {
		
		SubscriberDTO subscriber = buildSubscription();
		
		Optional<SubscriberEntity> optSubscription = buildOptSubscription();
		when(repository.findById(1L)).thenReturn(optSubscription );
		
		service.updateSubscriber(1L, subscriber);
		
		
	}
	
	private Optional<SubscriberEntity> buildOptSubscription() {
		SubscriberEntity buildFlushedSubEntity = buildFlushedSubEntity();
		
		return Optional.of(buildFlushedSubEntity);
	}

	private SubscriberEntity buildFlushedSubEntity() {
		SubscriberEntity buildSubEntity = buildSubEntity();
		buildSubEntity.setId(1L);
		return buildSubEntity;
	}

	private SubscriberEntity buildSubEntity() {
		SubscriberEntity subscription = new SubscriberEntity();
		subscription.setName("Leonardo");
		subscription.setLastName("Terceiro");
		subscription.setEmail("163510@upf.br");
		subscription.setCpf("01234567890");
		subscription.setCellphone("5554999999999");
		
		return subscription;
	}

	private SubscriberDTO buildSubscription() {
		SubscriberDTO subscription = new SubscriberDTO();
		subscription.setName("Leonardo");
		subscription.setLastName("Terceiro");
		subscription.setEmail("163510@upf.br");
		subscription.setCpf("01234567890");
		subscription.setCellphone("5554999999999");
		
		return subscription;
	}
}
