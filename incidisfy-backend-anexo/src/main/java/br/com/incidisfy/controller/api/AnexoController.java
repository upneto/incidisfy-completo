package br.com.incidisfy.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.incidisfy.controller.payload.AnexoPayload;
import br.com.incidisfy.resources.exception.DaoException;
import br.com.incidisfy.service.AnexoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/anexo")
public class AnexoController {

	@Autowired
	private AnexoService service;
	
	@ApiOperation(value = "Pesquisa todos os Veiculos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna lista de Veiculos"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
	@GetMapping(produces="application/json")
	public ResponseEntity<List<AnexoPayload>> findAll() throws DaoException {
		return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Pesquisa Veiculo por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna Veiculo"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
	@GetMapping(value = "/{id}", produces="application/json", consumes="application/json")
	public ResponseEntity<AnexoPayload> findBy(@PathVariable long id) throws DaoException {
		return new ResponseEntity<>(this.service.find(id), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Grava novo Veiculo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Gravou Veiculo com sucesso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
	@PostMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<?> insert(@RequestBody AnexoPayload pessoa) throws DaoException {
		this.service.insert(pessoa);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
