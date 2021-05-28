package org.generation.blogPessoal.controller;

/*Parte 1 criação da tabela Tema.

1. Camada de model com o nome de Tema com os atributos apresentados na
video-aula:
2. Uma camada de repository com o nome TemaRepository (com a
capacidade de se comunicar com o banco de dados mysql).
3. Uma camada de Controller com o nome de TemaController Com 5
endpoints:
● findAllTema = um endPoint com a capacidade de trazer todas os temas.
● findByIDTema = um endPoint com a função de trazer uma unico tema por id.
● findByDescricaoPostagem = um endPoint com a função de trazer um unico
tema por Descricao.
● postTema = um endPoint com a função de gravar um novo tema no banco de
dados.
● putTema = um endPoint com a função de atualizar dados de um tema.
● deleteTema = um endPoint com a função de apagar um tema do banco de
dados).*/

import java.util.List;

import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")		//habilita ver dados independente da origem e parametros
@RequestMapping("/tema")
public class TemaController {
	
	@Autowired
	private TemaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Tema>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tema> getById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Tema>> getByName(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<Tema> post (@RequestBody Tema tema){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(tema));
	}
	
	@PutMapping
	public ResponseEntity<Tema> put (@RequestBody Tema tema){
		return ResponseEntity.ok(repository.save(tema));
	}	
	
	@DeleteMapping
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}

}
