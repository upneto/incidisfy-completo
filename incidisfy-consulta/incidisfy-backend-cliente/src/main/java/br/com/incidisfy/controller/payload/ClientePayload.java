package br.com.incidisfy.controller.payload;

import java.util.ArrayList;
import java.util.List;

import br.com.incidisfy.persistence.model.Contato;
import br.com.incidisfy.persistence.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientePayload {

	private Long documento;
	private String informacao;
	private String nome;
	private String nomeRazaoSocial;
	private List<ContatoPayload> contatos;
	private List<EnderecoPayload> enderecos;
	
	public void buildContatosPayload(List<Contato> contatos) {
		this.contatos = new ArrayList<ContatoPayload>();
		if(contatos != null) {
			for(Contato contato : contatos) {
				this.contatos.add(ContatoPayload.builder()
						.id(contato.getId())
						.cliente(contato.getCliente().getDocumento())
						.descricao(contato.getDescricao())
						.build());
			}
		}
	}
	
	public void buildEnderecosPayload(List<Endereco> enderecos) {
		this.enderecos = new ArrayList<EnderecoPayload>();
		if(enderecos != null) {
			for(Endereco endereco : enderecos) {
				this.enderecos.add(EnderecoPayload.builder()
						.id(endereco.getId())
						.rua(endereco.getRua())
						.numero(endereco.getNumero())
						.complemento(endereco.getComplemento())
						.bairro(endereco.getBairro())
						.cidade(endereco.getCidade())
						.estado(endereco.getEstado())
						.pais(endereco.getPais())
						.cep(endereco.getCep())
						.cliente(endereco.getCliente().getDocumento())
						.build());
			}
		}
	}
}
