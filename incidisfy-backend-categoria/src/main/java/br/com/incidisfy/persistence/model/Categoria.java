package br.com.incidisfy.persistence.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode
@Builder
@Entity
@Table(name="dt_veiculos")
public class Categoria implements Serializable {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 961439410950712375L;

	@Id	
	private Integer codigo;
	private String descricao;
}