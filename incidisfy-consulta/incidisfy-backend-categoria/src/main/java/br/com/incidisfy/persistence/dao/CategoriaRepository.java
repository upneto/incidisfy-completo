package br.com.incidisfy.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.incidisfy.persistence.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
