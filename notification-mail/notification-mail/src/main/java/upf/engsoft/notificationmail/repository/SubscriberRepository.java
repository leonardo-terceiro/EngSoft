package upf.engsoft.notificationmail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import upf.engsoft.notificationmail.entity.SubscriberEntity;

public interface SubscriberRepository extends JpaRepository<SubscriberEntity, Long>{

	List<SubscriberEntity> findByStatus(String status);
	
}
