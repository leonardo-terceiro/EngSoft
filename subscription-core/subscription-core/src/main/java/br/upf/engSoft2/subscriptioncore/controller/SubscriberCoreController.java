package br.upf.engSoft2.subscriptioncore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import br.upf.engSoft2.subscriptioncore.entity.Subscriber;
import br.upf.engSoft2.subscriptioncore.service.SubscriberCoreService;

@RestController
public class SubscriberCoreController {

	@Autowired
	private SubscriberCoreService service;
	
	@GetMapping
	public ResponseEntity<List<Subscriber>> getSubscribers() {
		
		List<Subscriber> subscribers = service.getSubscribers();
		
		return ResponseEntity.status(HttpStatus.OK).body(subscribers);
	}

	@GetMapping
	public ResponseEntity<?> getSubscriber() {
		return null;
	}
	
	@PostMapping
	public ResponseEntity<?> createSubscribers() {
		return null;
	}
	
	@PutMapping
	public ResponseEntity<?> updateSubscribers() {
		return null;
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteSubscribers() {
		return null;
	}
}
