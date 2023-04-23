/**
 * 
 */
package br.com.fiap.fmba.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigInteger;
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

import br.com.incidisfy.controller.payload.AnexoPayload;
import br.com.incidisfy.persistence.dao.VeiculoRepository;
import br.com.incidisfy.persistence.model.Anexo;
import br.com.incidisfy.resources.exception.DaoException;
import br.com.incidisfy.service.AnexoService;

/**
 * @author Ulisses Neto
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TestVeiculoService {
	
	@Mock
	private VeiculoRepository mockRepository;

	@InjectMocks
	private AnexoService service;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
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
	 * Test method for {@link br.com.incidisfy.service.AnexoService#find(long)}.
	 * @throws DaoException 
	 */
	@Test
	public void testFind() throws DaoException {
		
		Mockito.when(mockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getMock()));
		
		AnexoPayload find = service.find(123);

		Assert.assertNotNull(find);
		Assert.assertEquals(getMock().getCodigo(), find.getCodigo());
	}

	/**
	 * Test method for {@link br.com.incidisfy.service.AnexoService#find(long)}.
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
	 * Test method for {@link br.com.incidisfy.service.AnexoService#findAll()}.
	 * @throws DaoException 
	 */
	@Test
	public void testFindAll() throws DaoException {
		List<Anexo> lista = new ArrayList<Anexo>();
		lista.add(getMock());
		
		Mockito.when(mockRepository.findAll()).thenReturn(lista);
		
		List<AnexoPayload> findAll = service.findAll();

		Assert.assertNotNull(findAll);
		Assert.assertEquals(lista.size(), findAll.size());
		Assert.assertEquals(lista.get(0).getCodigo(), findAll.get(0).getCodigo());
	}
	
	/**
	 * Test method for {@link br.com.incidisfy.service.AnexoService#findAll()}.
	 * @throws DaoException 
	 */
	@Test
	public void testFindAllError() {
		List<Anexo> lista = new ArrayList<Anexo>();
		lista.add(getMock());
		
		Mockito.when(mockRepository.findAll()).thenThrow(new RuntimeException());
		
		try {
			service.findAll();
		} catch (Exception e) {
			Assert.assertTrue(e instanceof DaoException);
		}		
	}

	/**
	 * Test method for {@link br.com.incidisfy.service.AnexoService#insert(br.com.incidisfy.persistence.model.Anexo)}.
	 * @throws DaoException 
	 */
	@Test
	public void testInsert() throws DaoException {		
		
		service.insert(getMockPayload());
		
		verify(mockRepository, times(1)).save(Mockito.any(Anexo.class));
	}
}
