package com.fvss.vendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
		title = "Api de Vendas",
		version = "3.0.1",
		description = "Rest Api de um sistema de venda"
	)
)
public class VendasApplication {
		
	
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
