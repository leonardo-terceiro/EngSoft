package upf.engsoft.subscriptioncore.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import upf.engsoft.subscriptioncore.constants.ResponseCode;
import upf.engsoft.subscriptioncore.dto.SubscriberDTO;
import upf.engsoft.subscriptioncore.dto.SucessResponseDTO;
import upf.engsoft.subscriptioncore.entity.SubscriberEntity;
import upf.engsoft.subscriptioncore.exception.SubscriberNotFoundException;
import upf.engsoft.subscriptioncore.exception.UnexpectedErrorException;
import upf.engsoft.subscriptioncore.repository.SubscriberRepository;

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
			throw new SubscriberNotFoundException("Inscrição com id: [" + id + "] não encontrada") ;
		}
		
	}
	
	public SucessResponseDTO saveSubscriber(SubscriberDTO subscriber) {
		log.info("saveSubscriber() - START - creationg subscription for document: [{}]", subscriber.getCpf());
		
		SubscriberEntity subEntity = buildSubscriberEntity(subscriber);
		
		try {
			SubscriberEntity savedSubscription = repository.saveAndFlush(subEntity);
			
			SucessResponseDTO response = new SucessResponseDTO();
			response.setCode(ResponseCode.OK.getCode());
			response.setStatus(ResponseCode.OK.getMessage());
			response.setDate(LocalDateTime.now());
			response.setMessage("Insrição realizada com sucesso! id de inscrição: [" + savedSubscription.getId() + "]");
			
			log.info("saveSubscriber() - END - subscription id: [{}]", savedSubscription.getId());
			return response;
		} catch (Exception e) {
			
			log.error("saveSubscriber() - ERROR ", e);
			throw new UnexpectedErrorException("Erro ao tentar realizar inscrição para usuario com CPF: [" + subscriber.getCpf() + "]");
		}
		
	}
	

	public SucessResponseDTO updateSubscriber(Long subscriberId, SubscriberDTO subscriber) throws SubscriberNotFoundException {
		log.info("updateSubscriber() - START - updating subscription with id: [{}]", subscriberId);
		
		Optional<SubscriberEntity> optSubscriber = repository.findById(subscriberId);
		
		if(!optSubscriber.isPresent()) {
			log.info("updateSubscriber() - END - id: [{}] not found", subscriberId);
			throw new SubscriberNotFoundException("Nenhuma inscrição com id: [" + subscriberId + "] foi encontrada!");
		}
		
		try {
			SubscriberEntity subEnt = buildSubscriberEntity(subscriber);
			SubscriberEntity oldSubscriber = optSubscriber.get();
			
			BeanUtils.copyProperties(subEnt, oldSubscriber, "id");
			
			repository.save(oldSubscriber);
			SucessResponseDTO response = new SucessResponseDTO();
			response.setCode(ResponseCode.OK.getCode());
			response.setStatus(ResponseCode.OK.getMessage());
			response.setMessage("Inscição com id: [" + subscriberId + "] atualizada com sucesso!");
			response.setDate(LocalDateTime.now());
			
			log.info("updateSubscriber() - END - update finished for subscription id: [{}]", subscriberId);
			return response;
		}catch (Exception e) {
			log.info("updateSubscriber() - END - Unexpected error ocurring while updating subscription with id: [{}] - Error: ", subscriberId, e);
			throw new UnexpectedErrorException("Erro ao tentar realizar atualiazação da inscrição id: [" + subscriberId + "]");
		}
	}

	public SucessResponseDTO deleteSubscriber(Long subscriberId) throws SubscriberNotFoundException {
		log.info("deleteSubscriber() - START - deliting subscription with id: [{}]", subscriberId);
		
		boolean existsById = repository.existsById(subscriberId);

		if (existsById) {
			try {
				repository.deleteById(subscriberId);
			} catch (Exception e) {
				log.info("deleteSubscriber() - ERROR - error while trying to delete subscription with id: [{}] - Error: ",subscriberId, e);
				throw new UnexpectedErrorException("Erro ao tentar realizar exclusão da inscrição com id: [" + subscriberId + "]");
			}
			SucessResponseDTO response = new SucessResponseDTO();
			response.setCode(ResponseCode.OK.getCode());
			response.setStatus(ResponseCode.OK.getMessage());
			response.setMessage("Inscrição excluida com sucesso!");
			response.setDate(LocalDateTime.now());

			log.info("deleteSubscriber() - END - sucessuful deleting subscription with id: [{}]", subscriberId);
			return response;
		} else {
			log.info("deleteSubscriber() - END - subscription with id: [{}] not founded our already deleted",subscriberId);
			throw new SubscriberNotFoundException("Inscrição com id: [" + subscriberId + "] não encontrada!");
		}
		
	}
	
	private SubscriberEntity buildSubscriberEntity(SubscriberDTO subscriber) {
		SubscriberEntity subEntity = new SubscriberEntity();
		subEntity.setName(subscriber.getName());
		subEntity.setLastName(subscriber.getLastName());
		subEntity.setEmail(subscriber.getEmail());
		subEntity.setCpf(subscriber.getCpf());
		subEntity.setCellphone(subscriber.getCellphone());
		subEntity.setStatus("INITIAL");
		return subEntity; 
	}
}
