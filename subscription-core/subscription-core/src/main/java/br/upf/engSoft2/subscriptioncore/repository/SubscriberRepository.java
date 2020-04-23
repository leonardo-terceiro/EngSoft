package br.upf.engSoft2.subscriptioncore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.upf.engSoft2.subscriptioncore.entity.SubscriberEntity;

public interface SubscriberRepository extends JpaRepository<SubscriberEntity, Long>{

}
