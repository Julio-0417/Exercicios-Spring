package com.farmacia.farmacia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmacia.farmacia.model.Categoria;
import com.farmacia.farmacia.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repositoryC;
	
	@GetMapping("/todas")
	public ResponseEntity<List<Categoria>> getAll(){
		return ResponseEntity.ok(repositoryC.findAll());
	}
	
	@GetMapping("/{id_categoria}")
	public ResponseEntity<Categoria> getById(@PathVariable long id_categoria){
		return repositoryC.findById(id_categoria)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
		
	@GetMapping("/cat/{categoria}")
	public ResponseEntity<List<Categoria>> getByName(@PathVariable String categoria){
		return ResponseEntity.ok(repositoryC.findAllByCategoriaContainingIgnoreCase(categoria));
		
	}
	
	@PostMapping("/adicionar")
	public ResponseEntity<Categoria> post (@RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.OK)
				.body(repositoryC.save(categoria));
	}
	
	@PutMapping("/atualizar/{id_categoria")
	public ResponseEntity<Categoria> put (@RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.OK)
				.body(repositoryC.save(categoria));
	}
	
	@DeleteMapping("/deletar/{id_categoria}")
	public void delete (@PathVariable long id_categoria) {
		repositoryC.deleteById(id_categoria);
	}
	

}
