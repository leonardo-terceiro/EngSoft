package upf.engsoft.subscriptioncore.controller;

import java.util.List;

import javax.validation.Valid;

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

import upf.engsoft.subscriptioncore.dto.SubscriberDTO;
import upf.engsoft.subscriptioncore.dto.SucessResponseDTO;
import upf.engsoft.subscriptioncore.entity.SubscriberEntity;
import upf.engsoft.subscriptioncore.exception.SubscriberNotFoundException;
import upf.engsoft.subscriptioncore.service.SubscriberCoreService;

@RestController
public class SubscriberCoreController {

	@Autowired
	private SubscriberCoreService service;
	
	@GetMapping("/find/all")
	public ResponseEntity<List<SubscriberEntity>> getSubscribers() {
		
		List<SubscriberEntity> subscribers = service.getSubscribers();
		
		return ResponseEntity.status(HttpStatus.OK).body(subscribers);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<SubscriberEntity> getSubscriberById(@PathVariable("id") Long id) throws SubscriberNotFoundException {
		
		SubscriberEntity subscriber = service.getSubscriber(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(subscriber);
	}
	
	@PostMapping("/create")
	public ResponseEntity<SucessResponseDTO> createSubscribers(@RequestBody @Valid SubscriberDTO subscriber) {
		
		SucessResponseDTO response = service.saveSubscriber(subscriber);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<SucessResponseDTO> updateSubscribers(@PathVariable("id") Long id, @RequestBody @Valid SubscriberDTO subscriber) throws SubscriberNotFoundException {
		
		SucessResponseDTO response = service.updateSubscriber(id, subscriber);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<SucessResponseDTO> deleteSubscribers(@PathVariable("id") Long id) throws SubscriberNotFoundException {
		
		SucessResponseDTO deleteSubscriber = service.deleteSubscriber(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(deleteSubscriber);
	}
}
