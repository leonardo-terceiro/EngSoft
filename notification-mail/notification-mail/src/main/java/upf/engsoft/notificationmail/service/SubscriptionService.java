package upf.engsoft.notificationmail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import upf.engsoft.notificationmail.entity.SubscriberEntity;
import upf.engsoft.notificationmail.repository.SubscriberRepository;

@Slf4j
@Service
public class SubscriptionService {

	@Autowired
	private SubscriberRepository subscriberRepository;
	
	/**
	 * 
	 * Find in dataBase the new subscriptions
	 * 
	 * @return a list of subscriptions
	 */
	public List<SubscriberEntity> loadSubscriptions() {
		log.info("loadSubscriptions() - START - finding subscriptions with INITIAL status");
		
		List<SubscriberEntity> initialSubscriptions = subscriberRepository.findByStatus("INITIAL");
		
		log.info("loadSubscriptions() - END - loaded [{}] subscriptions", initialSubscriptions.size());
		return initialSubscriptions;
		
	}

	/**
	 * 
	 * Change the status of the subscriptions
	 * 
	 * @param loadSubscriptions
	 * @param status
	 */
	public void updateStatus(List<SubscriberEntity> loadSubscriptions, String status) {
		log.info("updateStatus() - START - changing status to [{}] for [{}] subscriptons", status, loadSubscriptions);
		
		for (SubscriberEntity subEnt : loadSubscriptions) {
			subEnt.setStatus(status);
		}
		
		subscriberRepository.saveAll(loadSubscriptions);
		
		log.info("updateStatus() - END - sucessuful updated status for [{}] subscriptions", loadSubscriptions.size());
	}

}
