package br.com.incidisfy.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.incidisfy.persistence.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
