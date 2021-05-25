package com.helloworld.app.Turma22.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*Atividade 1 Spring-boot (minha primeira aplicação):
Crie uma aplicação spring de hello world.

A aplicação deverá conter um end-point que retorna
uma string com as habilidades e mentalidades que você
utilizou para realizar essa atividade.

Atividade 2 Spring-boot (minha segunda aplicação):
Crie uma aplicação spring de hello world.

A aplicação deverá conter um end-point que retorna
uma string com os seus objetivos de aprendizagem para
essa semana.*/

@RestController
@RequestMapping("/hello")
public class HelloController {
	
	@GetMapping
	public String habilidadesementalidades() {
		return "Atenção aos detalhes\nPersistência";
	}
	
	@GetMapping("/exercicio2")
	public String objetivosdeaprendizagem() {
		return "Aprender sobre API rest utilizando Spring boot";
	}

}
