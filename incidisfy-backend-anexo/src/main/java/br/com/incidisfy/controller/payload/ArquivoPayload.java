package br.com.incidisfy.controller.payload;

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
public class ArquivoPayload {

	private String arquivo;
	private int tamanho;
	private String extensao;
}
