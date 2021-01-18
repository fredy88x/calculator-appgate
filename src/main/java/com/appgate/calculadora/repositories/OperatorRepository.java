package com.appgate.calculadora.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.appgate.calculadora.entitys.Operator;

import reactor.core.publisher.Flux;

@Repository
public interface OperatorRepository extends ReactiveMongoRepository<Operator, String>{

	Flux<Operator> findByIdSession(String idSession);
}
