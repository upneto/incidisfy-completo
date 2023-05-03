package br.com.incidisfy.persistence.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Entity
@Table(name="dt_anexo")
public class Anexo implements Serializable {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 961439410950712375L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private BigInteger codigo;	
	private String codigoReclamacao;
	private String arquivo;
	private int tamanho;
	private String extensao;	

	@Column(name="data_criacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;
}