package br.upf.engSoft2.subscriptioncore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.upf.engSoft2.subscriptioncore.entity.Subscriber;
import br.upf.engSoft2.subscriptioncore.exception.SubscriberNotFoundException;
import br.upf.engSoft2.subscriptioncore.repository.SubscriberRepository;

@Service
public class SubscriberCoreService {

	@Autowired
	private SubscriberRepository subscriberRepository;
	
	public List<Subscriber> getSubscribers() {
		
		return subscriberRepository.findAll();
		
	}
	
	public Subscriber getSubscriber(Long id) throws SubscriberNotFoundException {
		
		Optional<Subscriber> subscriber = subscriberRepository.findById(id);
		
		if(subscriber.isPresent()) {
			return subscriber.get();
		}else {
			throw new SubscriberNotFoundException();
		}
		
	}
	
}
