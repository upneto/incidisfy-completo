package br.com.incidisfy.persistence.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.incidisfy.persistence.model.Anexo;


public interface AnexosRepository extends MongoRepository<Anexo, String> {

}
