package com.appgate.calculadora.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appgate.calculadora.dto.DataIdSessionDTO;
import com.appgate.calculadora.entitys.Operator;
import com.appgate.calculadora.repositories.OperatorRepository;

import reactor.core.publisher.Mono;

@Service
@Transactional
public class CalculatorService {

	@Autowired
	private OperatorRepository operatorRepository;
	
	public Mono<DataIdSessionDTO> getId(){
		return Mono.just(DataIdSessionDTO.builder().idSession(UUID.randomUUID().toString()).build());
	}
	
	public Mono<Operator> createOperator(Integer operator, String idSession){
		Operator operatorEntity = new Operator();
		operatorEntity.setIdSession(idSession);
		operatorEntity.setOperator(operator);
		return operatorRepository.save(operatorEntity);
	}
	
	public Mono<Integer> doOperation(String operation, String idSession){
		Mono<Integer> result;
		switch(operation) {
		case "suma":
			 result = operatorRepository.findByIdSession(idSession).map(Operator::getOperator).reduce(0, (x1,x2)->x1+x2);
			 break;
		case "resta":
			 result = operatorRepository.findByIdSession(idSession).map(Operator::getOperator).reduce(0, (x1,x2)->x2-x1);
			 break;
		case "multiplicacion":
			 result = operatorRepository.findByIdSession(idSession).map(Operator::getOperator).reduce(1, (x1,x2)->x1*x2);
			 break;
		case "division":
			 result = operatorRepository.findByIdSession(idSession).map(Operator::getOperator).reduce(1, (x1,x2)->x1/x2);
			 break;
		case "potenciacion":
			 result = operatorRepository.findByIdSession(idSession).last().map(Operator::getOperator).map(operator -> operator * operator);
			 break;
		default:
			result = operatorRepository.findByIdSession(idSession).map(Operator::getOperator).reduce(0, (x1,x2)->x1+x2);
			break;
		}
		
		Operator resultOperation = new Operator();
		resultOperation.setIdSession(idSession);
		resultOperation.setOperator(result.block());
		operatorRepository.save(resultOperation);
		return result;
	}
	
}
