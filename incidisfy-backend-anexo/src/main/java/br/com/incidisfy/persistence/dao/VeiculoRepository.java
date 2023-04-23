package br.com.incidisfy.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.incidisfy.persistence.model.Anexo;

public interface VeiculoRepository extends JpaRepository<Anexo, Long> {

}
