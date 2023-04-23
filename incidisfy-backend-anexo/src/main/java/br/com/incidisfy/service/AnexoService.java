package br.com.incidisfy.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.incidisfy.controller.payload.AnexoPayload;
import br.com.incidisfy.controller.payload.ArquivoPayload;
import br.com.incidisfy.persistence.dao.VeiculoRepository;
import br.com.incidisfy.persistence.model.Anexo;
import br.com.incidisfy.resources.exception.DaoException;

@Service
public class AnexoService {

	@Autowired
	private VeiculoRepository repository;

	public AnexoPayload find(long codigo) throws DaoException {
		try {
			Anexo obj = this.repository.findById(codigo).get();			
			return AnexoPayload.builder()
					.codigo(obj.getCodigo())
					.codigoReclamacao(obj.getCodigoReclamacao())
					.dataCriacao(obj.getDataCriacao())
					.arquivo(ArquivoPayload.builder()
							.arquivo(obj.getArquivo())
							.tamanho(obj.getTamanho())
							.extensao(obj.getExtensao())
							.build())					
					.build();						
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	public List<AnexoPayload> findAll() throws DaoException {
		List<AnexoPayload> veiculos = new ArrayList<AnexoPayload>();
		try {
			List<Anexo> findAll = this.repository.findAll();
			findAll.stream().forEach(obj -> {
				veiculos.add(AnexoPayload.builder()
						.codigo(obj.getCodigo())
						.codigoReclamacao(obj.getCodigoReclamacao())
						.dataCriacao(obj.getDataCriacao())
						.arquivo(ArquivoPayload.builder()
								.arquivo(obj.getArquivo())
								.tamanho(obj.getTamanho())
								.extensao(obj.getExtensao())
								.build())
						.build());
			});
			return veiculos;
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	public void insert(AnexoPayload anexo) throws DaoException {
		try {
			this.repository.save(Anexo.builder()
					.codigoReclamacao(anexo.getCodigoReclamacao())
					.dataCriacao(new Date())
					.arquivo(anexo.getArquivo().getArquivo())
					.tamanho(anexo.getArquivo().getTamanho())
					.extensao(anexo.getArquivo().getExtensao())
					.build());
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}
}
