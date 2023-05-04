package br.com.incidisfy.persistence.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.incidisfy.controller.payload.ContatoPayload;
import br.com.incidisfy.controller.payload.EnderecoPayload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The persistent class for the dt_clientes database table.
 * 
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="dt_clientes")
public class Cliente implements Serializable {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = -5925378078816888144L;

	@Id	
	private long documento;

	private String informacao;

	private String nome;

	@Column(name="nome_razao_social")
	private String nomeRazaoSocial;

	@OneToMany(mappedBy="cliente", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Contato> contatos;

	@OneToMany(mappedBy="cliente", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Endereco> enderecos;
	
	
	public void buildContatos(List<ContatoPayload> contatos) {
		this.contatos = new ArrayList<Contato>();
		if(contatos != null) {
			for(ContatoPayload contato : contatos) {
				this.contatos.add(Contato.builder()
						.cliente(this)
						.descricao(contato.getDescricao())
						.build());
			}
		}
	}
	
	public void buildEnderecos(List<EnderecoPayload> enderecos) {
		this.enderecos = new ArrayList<Endereco>();
		if(enderecos != null) {
			for(EnderecoPayload endereco : enderecos) {
				this.enderecos.add(Endereco.builder()
						.cliente(this)
						.rua(endereco.getRua())
						.numero(endereco.getNumero())
						.complemento(endereco.getComplemento())
						.bairro(endereco.getBairro())
						.cidade(endereco.getCidade())
						.estado(endereco.getEstado())
						.pais(endereco.getPais())
						.cep(endereco.getCep())						
						.build());
			}
		}
	}

}