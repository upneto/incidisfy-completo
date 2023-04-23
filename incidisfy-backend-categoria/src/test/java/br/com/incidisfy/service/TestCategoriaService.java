/**
 * 
 */
package br.com.incidisfy.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

import br.com.incidisfy.controller.payload.CategoriaRequest;
import br.com.incidisfy.controller.payload.CategoriaResponse;
import br.com.incidisfy.persistence.dao.CategoriaRepository;
import br.com.incidisfy.persistence.model.Categoria;
import br.com.incidisfy.resources.exception.DaoException;

/**
 * @author Ulisses Neto
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TestCategoriaService {
	
	@Mock
	private CategoriaRepository mockRepository;

	@InjectMocks
	private CategoriaService service;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}
	
	public Categoria getMock() {
		return Categoria.builder()
				.codigo(123)
				.descricao("TESTE MOCK")
				.build();
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
	 * Test method for {@link br.com.incidisfy.service.CategoriaService#findAll()}.
	 * @throws DaoException 
	 */
	@Test
	public void testFindAll() throws DaoException {
		List<Categoria> lista = new ArrayList<Categoria>();
		lista.add(getMock());
		
		Mockito.when(mockRepository.findAll()).thenReturn(lista);
		
		List<CategoriaResponse> findAll = service.findAll();

		Assert.assertNotNull(findAll);
		Assert.assertEquals(lista.size(), findAll.size());		
		Assert.assertEquals(lista.get(0), findAll.get(0));
	}
	
	/**
	 * Test method for {@link br.com.incidisfy.service.ClienteService#insert(br.com.incidisfy.persistence.model.Cliente)}.
	 * @throws DaoException 
	 */
	@Test
	public void testInsert() throws DaoException {		
		
		service.insert(getMockRequestPayload());
		
		verify(mockRepository, times(1)).save(Mockito.any(Categoria.class));
	}

	/**
	 * Test method for {@link br.com.incidisfy.service.ClienteService#delete(long)}.
	 * @throws DaoException 
	 */
	@Test
	public void testDelete() throws DaoException {
		
		service.delete(1);
		
		verify(mockRepository, times(1)).deleteById(Mockito.anyInt());
	}
}
