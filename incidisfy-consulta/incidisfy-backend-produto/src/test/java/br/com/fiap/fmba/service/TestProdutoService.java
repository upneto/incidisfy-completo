/**
 * 
 */
package br.com.fiap.fmba.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.incidisfy.controller.payload.ProdutoPayload;
import br.com.incidisfy.persistence.dao.ProdutoRepository;
import br.com.incidisfy.persistence.model.Produto;
import br.com.incidisfy.resources.exception.DaoException;
import br.com.incidisfy.service.ProdutoService;

/**
 * @author Ulisses Neto
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TestProdutoService {
	
	@Mock
	private ProdutoRepository mockRepository;

	@InjectMocks
	private ProdutoService service;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
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
	 * Test method for {@link br.com.incidisfy.service.ProdutoService#find(long)}.
	 * @throws DaoException 
	 */
	@Test
	public void testFind() throws DaoException {
		
		Mockito.when(mockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getMock()));
		
		ProdutoPayload find = service.find(123);

		Assert.assertNotNull(find);
		Assert.assertEquals(getMock().getCodigo(), find.getCodigo());
	}

	/**
	 * Test method for {@link br.com.incidisfy.service.ProdutoService#find(long)}.
	 * @throws DaoException 
	 */
	@Test
	public void testFindError() throws DaoException {
		
		Mockito.when(mockRepository.findById(Mockito.anyLong())).thenThrow(new RuntimeException());
		
		try {
			service.find(123);			
		} catch (Exception e) {
			Assert.assertTrue(e instanceof DaoException);
		}
	}

	
	/**
	 * Test method for {@link br.com.incidisfy.service.ProdutoService#findAll()}.
	 * @throws DaoException 
	 */
	@Test
	public void testFindAll() throws DaoException {
		List<Produto> lista = new ArrayList<Produto>();
		lista.add(getMock());
		
		Mockito.when(mockRepository.findAll()).thenReturn(lista);
		
		List<ProdutoPayload> findAll = service.findAll();

		Assert.assertNotNull(findAll);
		Assert.assertEquals(lista.size(), findAll.size());
		Assert.assertEquals(lista.get(0).getCodigo(), findAll.get(0).getCodigo());
	}
	
	/**
	 * Test method for {@link br.com.incidisfy.service.ProdutoService#findAll()}.
	 * @throws DaoException 
	 */
	@Test
	public void testFindAllError() {
		List<Produto> lista = new ArrayList<Produto>();
		lista.add(getMock());
		
		Mockito.when(mockRepository.findAll()).thenThrow(new RuntimeException());
		
		try {
			service.findAll();
		} catch (Exception e) {
			Assert.assertTrue(e instanceof DaoException);
		}		
	}

	/**
	 * Test method for {@link br.com.incidisfy.service.ProdutoService#insert(br.com.incidisfy.persistence.model.Produto)}.
	 * @throws DaoException 
	 */
	@Test
	public void testInsert() throws DaoException {		
		
		service.insert(getMockPayload());
		
		verify(mockRepository, times(1)).save(Mockito.any(Produto.class));
	}

	/**
	 * Test method for {@link br.com.incidisfy.service.ProdutoService#update(br.com.incidisfy.persistence.model.Produto)}.
	 * @throws DaoException 
	 */
	@Test
	public void testUpdate() throws DaoException {
		
		service.update(getMockPayload());
		
		verify(mockRepository, times(1)).save(Mockito.any(Produto.class));
	}

	/**
	 * Test method for {@link br.com.incidisfy.service.ProdutoService#delete(long)}.
	 * @throws DaoException 
	 */
	@Test
	public void testDelete() throws DaoException {
		
		service.delete(1);
		
		verify(mockRepository, times(1)).deleteById(Mockito.anyLong());
	}

}
