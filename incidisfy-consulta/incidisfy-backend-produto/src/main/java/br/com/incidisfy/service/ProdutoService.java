package br.com.incidisfy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.incidisfy.controller.payload.ProdutoPayload;
import br.com.incidisfy.persistence.dao.ProdutoRepository;
import br.com.incidisfy.persistence.model.Produto;
import br.com.incidisfy.resources.exception.DaoException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	public ProdutoPayload find(long id) throws DaoException {
		try {
			Produto veiculo = this.repository.findById(id).get();			
			return ProdutoPayload.builder()
					.codigo(veiculo.getCodigo())
					.dataCriacao(veiculo.getDataCriacao())
					.descricao(veiculo.getDescricao())
					.marca(veiculo.getMarca())
					.modelo(veiculo.getModelo())
					.build();						
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	public List<ProdutoPayload> findAll() throws DaoException {
		List<ProdutoPayload> veiculos = new ArrayList<ProdutoPayload>();
		try {
			List<Produto> findAll = this.repository.findAll();
			findAll.stream().forEach(veiculo -> {
				veiculos.add(ProdutoPayload.builder()
						.codigo(veiculo.getCodigo())
						.dataCriacao(veiculo.getDataCriacao())
						.descricao(veiculo.getDescricao())
						.marca(veiculo.getMarca())
						.modelo(veiculo.getModelo())
						.build());
			});
			return veiculos;
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	public void insert(ProdutoPayload veiculo) throws DaoException {
		try {
			this.repository.save(Produto.builder()
					.dataCriacao(veiculo.getDataCriacao())
					.descricao(veiculo.getDescricao())
					.marca(veiculo.getMarca())
					.modelo(veiculo.getModelo())
					.build());
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	public void update(ProdutoPayload veiculo) throws DaoException {
		try {
			this.repository.save(Produto.builder()
					.codigo(veiculo.getCodigo())
					.dataCriacao(veiculo.getDataCriacao())
					.descricao(veiculo.getDescricao())
					.marca(veiculo.getMarca())
					.modelo(veiculo.getModelo())
					.build());
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	public void delete(long id) throws DaoException {
		try {
			this.repository.deleteById(id);
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}
}
