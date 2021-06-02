package com.lojagames.lojagames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lojagames.lojagames.model.Categoria;


@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	public List<Categoria> findAllByCategoriaContainingIgnoreCase (String Categoria); 
	
}