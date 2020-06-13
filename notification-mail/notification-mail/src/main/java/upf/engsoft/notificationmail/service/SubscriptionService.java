package upf.engsoft.notificationmail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upf.engsoft.notificationmail.entity.SubscriberEntity;
import upf.engsoft.notificationmail.repository.SubscriberRepository;

@Service
public class SubscriptionService {

	@Autowired
	private SubscriberRepository subscriberRepository;
	
	public List<SubscriberEntity> loadSubscriptions() {

		return subscriberRepository.findByStatus("INITIAL");
		
	}

	public void updateStatus(List<SubscriberEntity> loadSubscriptions, String status) {

		for (SubscriberEntity subEnt : loadSubscriptions) {
			subEnt.setStatus(status);
		}
		
		subscriberRepository.saveAll(loadSubscriptions);
		
	}

}
