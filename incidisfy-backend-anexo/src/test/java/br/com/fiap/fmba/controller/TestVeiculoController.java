/**
 * 
 */
package br.com.fiap.fmba.controller;

import java.math.BigInteger;
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

import br.com.incidisfy.controller.api.AnexoController;
import br.com.incidisfy.controller.payload.AnexoPayload;
import br.com.incidisfy.persistence.model.Anexo;
import br.com.incidisfy.resources.exception.DaoException;
import br.com.incidisfy.service.AnexoService;

/**
 * @author Ulisses Neto
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TestVeiculoController {

	@Mock
	private AnexoService mockService;

	@InjectMocks
	private AnexoController controller;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUpBefore() throws Exception {
		MockitoAnnotations.openMocks(this);
	}
	
	public Anexo getMock() {
		return Anexo.builder()
				.codigo(BigInteger.TEN)				
				.build();
	}
	
	public AnexoPayload getMockPayload() {
		return AnexoPayload.builder()
				.codigo(BigInteger.TEN)
				.build();
	}

	/**
	 * Test method for
	 * {@link br.com.incidisfy.controller.api.AnexoController#findAll()}.
	 * @throws DaoException 
	 */
	@Test
	public void testFindAll() throws DaoException {
		
		List<AnexoPayload> lista = new ArrayList<AnexoPayload>();
		lista.add(getMockPayload());
		
		Mockito.when(mockService.findAll()).thenReturn(lista);
		
		ResponseEntity<List<AnexoPayload>> findAll = controller.findAll();

		Assert.assertNotNull(findAll);
        Assert.assertEquals(200, findAll.getStatusCode().value());
        Assert.assertEquals(lista.get(0).getCodigo(), findAll.getBody().get(0).getCodigo());
	}

	/**
	 * Test method for
	 * {@link br.com.incidisfy.controller.api.AnexoController#findBy(long)}.
	 * @throws DaoException 
	 */
	@Test
	public void testFindBy() throws DaoException {
		
		Mockito.when(mockService.find(Mockito.anyLong())).thenReturn(getMockPayload());
		
		ResponseEntity<AnexoPayload> findBy = controller.findBy(123);

		Assert.assertNotNull(findBy);
        Assert.assertEquals(200, findBy.getStatusCode().value());
        Assert.assertEquals(getMock().getCodigo(), findBy.getBody().getCodigo());
	}

	/**
	 * Test method for
	 * {@link br.com.incidisfy.controller.api.AnexoController#insert(br.com.incidisfy.persistence.model.Anexo)}.
	 * @throws DaoException 
	 */
	@Test
	public void testInsert() throws DaoException {
				
		ResponseEntity<?> insert = controller.insert(getMockPayload());

		Assert.assertNotNull(insert);
        Assert.assertEquals(200, insert.getStatusCode().value());
	}

}
