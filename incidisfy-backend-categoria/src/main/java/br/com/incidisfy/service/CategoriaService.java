package br.com.incidisfy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.incidisfy.controller.payload.CategoriaRequest;
import br.com.incidisfy.controller.payload.CategoriaResponse;
import br.com.incidisfy.persistence.dao.CategoriaRepository;
import br.com.incidisfy.persistence.model.Categoria;
import br.com.incidisfy.resources.exception.DaoException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public List<CategoriaResponse> findAll() throws DaoException {
		List<CategoriaResponse> categorias = new ArrayList<CategoriaResponse>();
		try {
			List<Categoria> result = this.repository.findAll();
			result.stream().forEach(categoria -> {
				categorias.add(CategoriaResponse.builder()
						.codigo(categoria.getCodigo())
						.descricao(categoria.getDescricao())
						.build());
			});
			return categorias;
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}
	
	public void insert(CategoriaRequest request) throws DaoException {
		try {
			Categoria objToSave = Categoria.builder()
					.descricao(request.getDescricao())					
					.build();
			
			this.repository.save(objToSave);
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	public void delete(Integer id) throws DaoException {
		try {
			this.repository.deleteById(id);
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}
}
