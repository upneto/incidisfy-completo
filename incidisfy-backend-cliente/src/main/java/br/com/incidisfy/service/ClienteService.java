package br.com.incidisfy.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.incidisfy.controller.payload.ClientePayload;
import br.com.incidisfy.persistence.dao.ClienteRepository;
import br.com.incidisfy.persistence.model.Cliente;
import br.com.incidisfy.persistence.model.TipoPessoa;
import br.com.incidisfy.resources.exception.DaoException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public ClientePayload find(long id) throws DaoException {
		try {
			Cliente cliente = this.repository.findById(id).get();
			ClientePayload clientePayload = ClientePayload.builder()
					.documento(cliente.getDocumento())
					.dataCriacao(cliente.getDataCriacao())					
					.informacao(cliente.getInformacao())
					.tipoPessoa(cliente.getTipoPessoa().getId())
					.nome(cliente.getNome())
					.nomeRazaoSocial(cliente.getNomeRazaoSocial())
					.build();
			
			clientePayload.buildContatosPayload(cliente.getContatos());
			clientePayload.buildEnderecosPayload(cliente.getEnderecos());
			
			return clientePayload;			
			
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	public List<ClientePayload> findAll() throws DaoException {
		try {
			List<ClientePayload> payloads = new ArrayList<ClientePayload>();			
			List<Cliente> clientes = this.repository.findAll();
			for(Cliente cliente : clientes) {
				ClientePayload clientePayload = ClientePayload.builder()
						.documento(cliente.getDocumento())
						.dataCriacao(cliente.getDataCriacao())						
						.informacao(cliente.getInformacao())
						.nome(cliente.getNome())
						.nomeRazaoSocial(cliente.getNomeRazaoSocial())
						.tipoPessoa(cliente.getTipoPessoa().getId())
						.build();
				
				clientePayload.buildContatosPayload(cliente.getContatos());
				clientePayload.buildEnderecosPayload(cliente.getEnderecos());
				
				payloads.add(clientePayload);
			}
			
			return payloads;
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	public void insert(ClientePayload cliente) throws DaoException {
		try {
			Cliente clienteToSave = Cliente.builder()
					.informacao(cliente.getInformacao())
					.dataCriacao(Calendar.getInstance().getTime())
					.nome(cliente.getNome())
					.nomeRazaoSocial(cliente.getNomeRazaoSocial())
					.tipoPessoa(TipoPessoa.builder().id(cliente.getTipoPessoa()).build())
					.dataCriacao(Calendar.getInstance().getTime())					
					.build();
			
			clienteToSave.buildContatos(cliente.getContatos());
			clienteToSave.buildEnderecos(cliente.getEnderecos());
			
			this.repository.save(clienteToSave);
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	public void update(ClientePayload cliente) throws DaoException {
		try {
			
			Cliente clienteToSave = Cliente.builder()
					.documento(cliente.getDocumento())
					.informacao(cliente.getInformacao())
					.nome(cliente.getNome())
					.nomeRazaoSocial(cliente.getNomeRazaoSocial())
					.tipoPessoa(TipoPessoa.builder().id(cliente.getTipoPessoa()).build())
					.dataCriacao(Calendar.getInstance().getTime())
					.build();
					
			clienteToSave.buildContatos(cliente.getContatos());
			clienteToSave.buildEnderecos(cliente.getEnderecos());
			
			this.repository.save(clienteToSave);
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
