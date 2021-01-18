package com.appgate.calculadora.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appgate.calculadora.dto.DataIdSessionDTO;
import com.appgate.calculadora.dto.DataResultDTO;
import com.appgate.calculadora.entitys.Operator;
import com.appgate.calculadora.services.CalculatorService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/calculadora")
public class CalculatorController {

	@Autowired
	private CalculatorService calculatorService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseEntity<DataIdSessionDTO>> getSession(){
		return calculatorService.getId().map(id -> ResponseEntity.ok(id)).defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/{idSession}")
	public Mono<Operator> putOperator(@RequestParam Integer operator, @PathVariable String idSession){
		return calculatorService.createOperator(operator, idSession);
	}
	
	@GetMapping("/{idSession}")
	public Mono<ResponseEntity<Integer>> doOperation(@RequestParam String operation, @PathVariable String idSession){
		return calculatorService.doOperation(operation, idSession).map(result -> ResponseEntity.ok(result)).defaultIfEmpty(ResponseEntity.notFound().build());
	}
}
