package com.example.calculter;


import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.appgate.calculadora.CalculterApplication;
import com.appgate.calculadora.controller.CalculatorController;
import com.appgate.calculadora.entitys.Operator;
import com.appgate.calculadora.repositories.OperatorRepository;
import com.appgate.calculadora.services.CalculatorService;

import reactor.core.publisher.Mono;

@ContextConfiguration(classes = CalculterApplication.class)
@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = CalculatorController.class)
@Import(CalculatorService.class)
class CalculterApplicationTests {
	
	@MockBean
	private OperatorRepository operatorRepository;
	
	@Autowired
    private WebTestClient webClient;
	
	private Mono<Operator> operatorSave;
	
	@BeforeEach
	void setup() {
		Operator operator = new Operator();
		operator.setId("1");
		operator.setIdSession("10");
		operator.setOperator(9);
		operatorSave = Mono.just(operator);
	}
	
	@Test
	void getIdSession() {
		webClient.get().uri("/calculadora").exchange().expectStatus().isOk();
	}
	
	@Test
	void saveOperator() {
		when(operatorRepository.save(Mockito.any())).thenReturn(operatorSave);
		webClient.post().uri(uriBuilder -> uriBuilder.path("/calculadora/{idSession}").queryParam("operator", 5).build("10")).exchange().expectStatus().isOk();
	}
}
