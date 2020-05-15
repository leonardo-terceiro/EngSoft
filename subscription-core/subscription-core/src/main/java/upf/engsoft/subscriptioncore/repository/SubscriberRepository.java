package upf.engsoft.subscriptioncore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import upf.engsoft.subscriptioncore.entity.SubscriberEntity;

public interface SubscriberRepository extends JpaRepository<SubscriberEntity, Long>{

}
