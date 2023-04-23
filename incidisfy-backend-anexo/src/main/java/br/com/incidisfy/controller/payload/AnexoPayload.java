package br.com.incidisfy.controller.payload;

import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class AnexoPayload {

	private BigInteger codigo;
	private String codigoReclamacao;
	private ArquivoPayload arquivo;
		
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dataCriacao;
}
