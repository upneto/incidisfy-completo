package br.com.incidisfy.persistence.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The persistent class for the dt_veiculos database table.
 * 
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Anexo implements Serializable {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 961439410950712375L;

	@Id
	private String codigo;	
	private String codigoReclamacao;
	private String arquivo;
	private Integer tamanho;
	private String extensao;	
	private Date dataCriacao;
}