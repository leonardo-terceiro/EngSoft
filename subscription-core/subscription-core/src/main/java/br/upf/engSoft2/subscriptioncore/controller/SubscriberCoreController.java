package br.upf.engSoft2.subscriptioncore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.upf.engSoft2.subscriptioncore.entity.SubscriberEntity;
import br.upf.engSoft2.subscriptioncore.exception.SubscriberNotFoundException;
import br.upf.engSoft2.subscriptioncore.service.SubscriberCoreService;

@RestController
public class SubscriberCoreController {

	@Autowired
	private SubscriberCoreService service;
	
	@GetMapping("/all")
	public ResponseEntity<List<SubscriberEntity>> getSubscribers() {
		
		List<SubscriberEntity> subscribers = service.getSubscribers();
		
		return ResponseEntity.status(HttpStatus.OK).body(subscribers);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SubscriberEntity> getSubscriberById(@PathVariable("id") Long id) throws SubscriberNotFoundException {
		
		
		SubscriberEntity subscriber = service.getSubscriber(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(subscriber);
	}
	
	@PostMapping
	public ResponseEntity<?> createSubscribers(@RequestBody SubscriberEntity subscriber) {
		
		service.saveSubscriber(subscriber);
		
		return ResponseEntity.status(HttpStatus.OK).body("");
		
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateSubscribers(@PathVariable("id") Long id, @RequestBody SubscriberEntity subscriber) throws SubscriberNotFoundException {
		
		service.updateSubscriber(id, subscriber);
		
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteSubscribers(@PathVariable("id") Long id) {
		
		service.deleteSubscriber(id);
		
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
}
