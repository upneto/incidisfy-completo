/**
 * 
 */
package br.com.incidisfy.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import br.com.incidisfy.controller.api.CategoriaController;
import br.com.incidisfy.controller.payload.CategoriaRequest;
import br.com.incidisfy.controller.payload.CategoriaResponse;
import br.com.incidisfy.resources.exception.DaoException;
import br.com.incidisfy.service.CategoriaService;

/**
 * @author Ulisses Neto
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TestCategoriaController {

	@Mock
	private CategoriaService mockService;

	@InjectMocks
	private CategoriaController controller;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUpBefore() throws Exception {
		MockitoAnnotations.openMocks(this);
	}
	
	public CategoriaRequest getMockRequestPayload() {
		return CategoriaRequest.builder()
				.descricao("TESTE MOCK")
				.build();
	}
	
	public CategoriaResponse getMockResponsePayload() {
		return CategoriaResponse.builder()
				.codigo(123)			
				.descricao("TESTE MOCK")
				.build();
	}

	/**
	 * Test method for
	 * {@link br.com.incidisfy.controller.api.CategoriaController#findAll()}.
	 * @throws DaoException 
	 */
	@Test
	public void testFindAll() throws DaoException {
		
		List<CategoriaResponse> lista = new ArrayList<CategoriaResponse>();
		lista.add(getMockResponsePayload());
		
		Mockito.when(mockService.findAll()).thenReturn(lista);
		
		ResponseEntity<List<CategoriaResponse>> findAll = controller.findAll();

		Assert.assertNotNull(findAll);
        Assert.assertEquals(200, findAll.getStatusCode().value());
        Assert.assertEquals(lista.get(0).getCodigo(), findAll.getBody().get(0).getCodigo());
        Assert.assertEquals(lista.get(0).getDescricao(), findAll.getBody().get(0).getDescricao());
	}

	/**
	 * Test method for
	 * {@link br.com.incidisfy.controller.api.VeiculoController#insert(br.com.incidisfy.persistence.model.Veiculo)}.
	 * @throws DaoException 
	 */
	@Test
	public void testInsert() throws DaoException {
				
		ResponseEntity<?> insert = controller.insert(getMockRequestPayload());

		Assert.assertNotNull(insert);
        Assert.assertEquals(200, insert.getStatusCode().value());
	}

	/**
	 * Test method for
	 * {@link br.com.incidisfy.controller.api.VeiculoController#delete(long)}.
	 * @throws DaoException 
	 */
	@Test
	public void testDelete() throws DaoException {
		ResponseEntity<?> delete = controller.delete(123);

		Assert.assertNotNull(delete);
        Assert.assertEquals(200, delete.getStatusCode().value());
	}
}
