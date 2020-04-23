package br.upf.engSoft2.subscriptioncore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.upf.engSoft2.subscriptioncore.entity.SubscriberEntity;
import br.upf.engSoft2.subscriptioncore.exception.SubscriberNotFoundException;
import br.upf.engSoft2.subscriptioncore.repository.SubscriberRepository;

@Service
public class SubscriberCoreService {

	@Autowired
	private SubscriberRepository repository;
	
	public List<SubscriberEntity> getSubscribers() {
		
		return repository.findAll();
		
	}
	
	public SubscriberEntity getSubscriber(Long id) throws SubscriberNotFoundException {
		
		Optional<SubscriberEntity> subscriber = repository.findById(id);
		
		if(subscriber.isPresent()) {
			return subscriber.get();
		}else {
			throw new SubscriberNotFoundException();
		}
		
	}
	
	public void saveSubscriber(SubscriberEntity subscriber) {
		
		repository.save(subscriber);
		
	}
	
	public void updateSubscriber(Long subscriberId, SubscriberEntity subscriber) throws SubscriberNotFoundException {
		
		Optional<SubscriberEntity> optSubscriber = repository.findById(subscriberId);
		
		if(!optSubscriber.isPresent()) {
			throw new SubscriberNotFoundException();
		}
		
		SubscriberEntity oldSubscriber = optSubscriber.get();
		
		BeanUtils.copyProperties(subscriber, oldSubscriber, "id");
		
		repository.save(oldSubscriber);
		
	}

	public void deleteSubscriber(Long subscriberId) {
		
		repository.deleteById(subscriberId);
		
	}
	
}
