package br.com.incidisfy.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.incidisfy.persistence.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
