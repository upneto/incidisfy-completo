package br.com.incidisfy.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.incidisfy.controller.payload.CategoriaRequest;
import br.com.incidisfy.controller.payload.CategoriaResponse;
import br.com.incidisfy.resources.exception.DaoException;
import br.com.incidisfy.service.CategoriaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService service;
	
	/**
	 * 
	 * @return
	 * @throws DaoException
	 */
	@ApiOperation(value = "Pesquisa lista de Categorias")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso na pesquisa por lista de categorias"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
	@GetMapping(produces="application/json")
	public ResponseEntity<List<CategoriaResponse>> findAll() throws DaoException {
		return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
	}
	
	/**
	 * 
	 * @return
	 * @throws DaoException
	 */
	@ApiOperation(value = "Inclusão de Categoria")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso na inclusão de categorias"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
	@PostMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<?> insert(@RequestBody CategoriaRequest categoria) throws DaoException {
		this.service.insert(categoria);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	@ApiOperation(value = "Remoção de Categoria")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso na remoção da categorias"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
	@DeleteMapping(value = "/{codigo}", produces="application/json", consumes="application/json")
	public ResponseEntity<?> delete(@PathVariable int codigo) throws DaoException {
		this.service.delete(codigo);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
