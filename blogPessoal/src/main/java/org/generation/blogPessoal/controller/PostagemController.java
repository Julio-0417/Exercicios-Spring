package org.generation.blogPessoal.controller;

/*O back-end deverá possuir 3 camadas:
1. Camada de model com o nome de Postagem com os seus respectivos
atributos descritos nos video.
2. Uma camada de repository com o nome PostagemRepository (com a
capacidade de se comunicar com o banco de dados mysql).
3. Uma camada de Controller com o nome de PostagemController Com 6
endpoints:
● findAllPostagem = um endPoint com a capacidade de trazer todas as
Postagem.
● findByIDPostagem = um endPoint com a função de trazer uma única
Postagem por id.
● postPostagem = um endPoint com a função de gravar uma nova Postagem
no banco de dados.
● putPostagem = um endPoint com a função de atualizar dados de uma
Postagem.
● deletePostagem = um endPoint com a função de apagar uma Postagem do
banco de dados.
● Crie o método getByTitulo no repository.
● Crie um método getByTitulo no controller = um endPoint com a função de
trazer uma única Postagem por Título.*/


import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
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

@RestController  //informa que se trata de um controlador
@RequestMapping("/postagens") //informa por onde será acessado e a classe
@CrossOrigin("*")  //esta classe aceita requisição de qualquer origem
public class PostagemController {
	
	@Autowired  //injeção de dependências
	private PostagemRepository repository;
	
	@GetMapping  //requisição tipo get e método para busca de todas postagens
	public ResponseEntity<List<Postagem>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")  //método para busca por Id
	public ResponseEntity<Postagem> GetById(@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo/{titulo}")  //subrota para não confundir e método para busca por título
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping  //método para inserção da postagem
	public ResponseEntity<Postagem> post (@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	
	@PutMapping  //método para atualização de dado da postagem
	public ResponseEntity<Postagem> put (@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}
	
	@DeleteMapping("/{id}")  //método para deletar postagem
	public void delete(@PathVariable long id) {  //método void para não retornar nada
		repository.deleteById(id);
	}

}
