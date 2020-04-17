package br.upf.engSoft2.subscriptioncore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.upf.engSoft2.subscriptioncore.entity.Subscriber;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long>{

}
