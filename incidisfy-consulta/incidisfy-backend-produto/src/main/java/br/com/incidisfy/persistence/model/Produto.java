package br.com.incidisfy.persistence.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name="dt_produto")
public class Produto implements Serializable {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 961439410950712375L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long codigo;
	private String descricao;
	private String marca;
	private String modelo;
}