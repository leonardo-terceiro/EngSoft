package upf.engSoft2.subscriptioncore.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import upf.engSoft2.subscriptioncore.dto.SubscriberDTO;
import upf.engSoft2.subscriptioncore.dto.SucessResponseDTO;
import upf.engSoft2.subscriptioncore.entity.SubscriberEntity;
import upf.engSoft2.subscriptioncore.exception.SubscriberNotFoundException;
import upf.engSoft2.subscriptioncore.exception.UnexpectedErrorException;
import upf.engSoft2.subscriptioncore.repository.SubscriberRepository;

@Slf4j
@Service
public class SubscriberCoreService {

	@Autowired
	private SubscriberRepository repository;
	
	public List<SubscriberEntity> getSubscribers() {
		log.info("getSubscribers() - START - finding all subscripitions");
		
		List<SubscriberEntity> subscribers = repository.findAll();

		log.info("getSubscribers() - END - returning [{}] subscripitions", subscribers.size());
		return subscribers;
	}
	
	public SubscriberEntity getSubscriber(Long id) throws SubscriberNotFoundException {
		log.info("getSubscriber() - START - finding subscripition with id: [{}]", id);
		
		Optional<SubscriberEntity> subscriber = repository.findById(id);
		
		if(subscriber.isPresent()) {
			log.info("getSubscriber() - END - subscription founded");
			return subscriber.get();
		}else {
			log.warn("getSubscriber() - END - no subscripition founded with id: [{}]", id);
			throw new SubscriberNotFoundException("inscrição de id: [" + id + "] não encontrada") ;
		}
		
	}
	
	public SucessResponseDTO saveSubscriber(SubscriberDTO subscriber) {
		log.info("saveSubscriber() - START - creationg subscription for document: [{}]", subscriber.getCpf());
		
		SubscriberEntity subEntity = buildSubscriberEntity(subscriber);
		
		try {
			SubscriberEntity savedSubscription = repository.saveAndFlush(subEntity);
			
			SucessResponseDTO response = new SucessResponseDTO();
			response.setCode("203");
			response.setStatus("CREATED");
			response.setDate(LocalDateTime.now());
			response.setMessage("Insrição realizada com sucesso! id de inscrição: [" + savedSubscription.getId() + "]");
			log.info("\"saveSubscriber() - END - subscription id: [{}]", savedSubscription.getId());
			return response;
		} catch (Exception e) {
			log.error("saveSubscriber() - ERROR ", e);
			throw new UnexpectedErrorException("Erro ao tentar realizar inscrição para usuario com CPF: [" + subscriber.getCpf() + "]");
		}
		
	}
	

	public SucessResponseDTO updateSubscriber(Long subscriberId, SubscriberDTO subscriber) throws SubscriberNotFoundException {

		try {
			Optional<SubscriberEntity> optSubscriber = repository.findById(subscriberId);
			
			if(!optSubscriber.isPresent()) {
				throw new SubscriberNotFoundException("Nenhuma inscrição com id: [" + subscriberId + "] foi encontrada!");
			}
			
			SubscriberEntity subEnt = buildSubscriberEntity(subscriber);
			SubscriberEntity oldSubscriber = optSubscriber.get();
			
			BeanUtils.copyProperties(subEnt, oldSubscriber, "id");
			
			repository.save(oldSubscriber);
			SucessResponseDTO response = new SucessResponseDTO();
			response.setCode("200");
			response.setStatus("OK");
			response.setMessage("Inscição com id: [" + subscriberId + "] atualizada com sucesso!");
			response.setDate(LocalDateTime.now());
			return response;
		}catch (Exception e) {
			throw new UnexpectedErrorException("Erro ao tentar realizar atualiazação da inscrição id: [" + subscriberId + "]");
		}
	}

	public SucessResponseDTO deleteSubscriber(Long subscriberId) throws SubscriberNotFoundException {
		
		try {
			boolean existsById = repository.existsById(subscriberId);
			
			if(existsById) {
				repository.deleteById(subscriberId);
				SucessResponseDTO response = new SucessResponseDTO();
				response.setCode("200");
				response.setStatus("OK");
				response.setMessage("Inscrição excluida ocm sucesso!");
				response.setDate(LocalDateTime.now());
				return response;
			}else {
				throw new SubscriberNotFoundException("inscrição com id: [" + subscriberId + "] não encontrada!");
			}
		} catch (Exception e) {
			throw new UnexpectedErrorException("Erro ao tentar realizar exclusão da inscrição com id: [" + subscriberId + "]");
		}
		
		
		
	}
	
	private SubscriberEntity buildSubscriberEntity(SubscriberDTO subscriber) {
		SubscriberEntity subEntity = new SubscriberEntity();
		subEntity.setName(subscriber.getName());
		subEntity.setLastName(subscriber.getLastName());
		subEntity.setEmail(subscriber.getEmail());
		subEntity.setCpf(subscriber.getCpf());
		subEntity.setCellphone(subscriber.getCellphone());
		
		return subEntity;
	}
}
