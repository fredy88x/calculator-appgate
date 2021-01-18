package com.appgate.calculadora.entitys;



import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operator {

	 @Id
	 private String id;
	 private Integer operator;
	 private String idSession;
}
