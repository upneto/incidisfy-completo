/**
 * 
 */
package br.com.fiap.fmba.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import br.com.incidisfy.controller.api.ProdutoController;
import br.com.incidisfy.controller.payload.ProdutoPayload;
import br.com.incidisfy.persistence.model.Produto;
import br.com.incidisfy.resources.exception.DaoException;
import br.com.incidisfy.service.ProdutoService;

/**
 * @author Ulisses Neto
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TestProdutoController {

	@Mock
	private ProdutoService mockService;

	@InjectMocks
	private ProdutoController controller;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUpBefore() throws Exception {
		MockitoAnnotations.openMocks(this);
	}
	
	public Produto getMock() {
		return Produto.builder()
				.codigo(123)				
				.build();
	}
	
	public ProdutoPayload getMockPayload() {
		return ProdutoPayload.builder()
				.codigo(123)				
				.build();
	}

	/**
	 * Test method for
	 * {@link br.com.incidisfy.controller.api.ProdutoController#findAll()}.
	 * @throws DaoException 
	 */
	@Test
	public void testFindAll() throws DaoException {
		
		List<ProdutoPayload> lista = new ArrayList<ProdutoPayload>();
		lista.add(getMockPayload());
		
		Mockito.when(mockService.findAll()).thenReturn(lista);
		
		ResponseEntity<List<ProdutoPayload>> findAll = controller.findAll();

		Assert.assertNotNull(findAll);
        Assert.assertEquals(200, findAll.getStatusCode().value());
        Assert.assertEquals(lista.get(0).getCodigo(), findAll.getBody().get(0).getCodigo());
	}

	/**
	 * Test method for
	 * {@link br.com.incidisfy.controller.api.ProdutoController#findBy(long)}.
	 * @throws DaoException 
	 */
	@Test
	public void testFindBy() throws DaoException {
		
		Mockito.when(mockService.find(Mockito.anyLong())).thenReturn(getMockPayload());
		
		ResponseEntity<ProdutoPayload> findBy = controller.findBy(123);

		Assert.assertNotNull(findBy);
        Assert.assertEquals(200, findBy.getStatusCode().value());
        Assert.assertEquals(getMock().getCodigo(), findBy.getBody().getCodigo());
	}

	/**
	 * Test method for
	 * {@link br.com.incidisfy.controller.api.ProdutoController#insert(br.com.incidisfy.persistence.model.Produto)}.
	 * @throws DaoException 
	 */
	@Test
	public void testInsert() throws DaoException {
				
		ResponseEntity<?> insert = controller.insert(getMockPayload());

		Assert.assertNotNull(insert);
        Assert.assertEquals(200, insert.getStatusCode().value());
	}

	/**
	 * Test method for
	 * {@link br.com.incidisfy.controller.api.ProdutoController#update(br.com.incidisfy.persistence.model.Produto)}.
	 * @throws DaoException 
	 */
	@Test
	public void testUpdate() throws DaoException {
		ResponseEntity<?> update = controller.update(getMockPayload());

		Assert.assertNotNull(update);
        Assert.assertEquals(200, update.getStatusCode().value());
	}

	/**
	 * Test method for
	 * {@link br.com.incidisfy.controller.api.ProdutoController#delete(long)}.
	 * @throws DaoException 
	 */
	@Test
	public void testDelete() throws DaoException {
		ResponseEntity<?> delete = controller.delete(123);

		Assert.assertNotNull(delete);
        Assert.assertEquals(200, delete.getStatusCode().value());
	}

}
