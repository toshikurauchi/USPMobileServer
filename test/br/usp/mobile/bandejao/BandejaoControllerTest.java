package br.usp.mobile.bandejao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.usp.mobile.mock.MockJSONSerialization;


public class BandejaoControllerTest {
	private Result result;
	private BandejaoController controller;
	private ComentarioDAO dao;

	@Before
	public void setUp() {
		result = mock(Result.class);
		dao = mock(ComentarioDAO.class);
		controller = new BandejaoController(result, dao);
	}
	
	@Test
	public void listaTodosOsBandejoes() throws Exception {
		MockJSONSerialization jsonSerialization = preparaResultJSON();
		controller.listaBandejoes();
		Collection<String> bandejoesListados = (Collection<String>) jsonSerialization.getToSerialize();
		Bandejoes[] todosBandejoes = Bandejoes.values();
		assertEquals(todosBandejoes.length, bandejoesListados.size());
		for(Bandejoes bandejao : todosBandejoes) {
			assertTrue(bandejoesListados.contains(bandejao.getNome()));
		}
	}
	
	@Test
	public void quandoAdicionaMockNaoInsereNadaNoDAO() throws Exception {
		Comentario comentario = new Comentario();
		controller.adiciona(comentario, "mock");
		verify(dao, never()).insere(comentario);
	}
	
	@Test
	public void adicionaComentarioComNomeDoBandejao() throws Exception {
		Comentario comentario = new Comentario("Comida muito boa!", TamanhoDaFila.MUITO_GRANDE, Calendar.getInstance());
		String central = Bandejoes.CENTRAL.getNome();
		controller.adiciona(comentario, central);
		verify(dao).insere(comentario);
		assertEquals(central, comentario.getBandejao());
	}
	
	@Test
	public void naoAdicionaComentarioEmBandejaoInvalidoEDaNotFound() throws Exception {
		Comentario comentario = new Comentario("Comida muito boa!", TamanhoDaFila.MUITO_GRANDE, Calendar.getInstance());
		controller.adiciona(comentario, "bandejao invalido");
		verify(dao, never()).insere(comentario);
		verify(result).notFound();
	}
	
	@Test
	public void listaComentariosDeBandejaoMock() throws Exception {
		MockJSONSerialization jsonSerialization = preparaResultJSON();
		controller.lista("mock");
		Collection<Comentario> toSerialize = (Collection<Comentario>) jsonSerialization.getToSerialize();
		assertNotNull(toSerialize);
	}
	
	@Test
	public void listaComentariosDeBandejaoValido() throws Exception {
		MockJSONSerialization jsonSerialization = preparaResultJSON();
		controller.lista(Bandejoes.COCESP.getNome());
		Collection<Comentario> toSerialize = (Collection<Comentario>) jsonSerialization.getToSerialize();
		assertNotNull(toSerialize);
	}
	
	@Test
	public void naoListaComentariosDeBandejaoInvalidoEDaNotFound() throws Exception {
		controller.lista("bandejao invalido");
		verify(result).notFound();
	}
	
	private MockJSONSerialization preparaResultJSON() {
		MockJSONSerialization jsonSerialization = new MockJSONSerialization();
		when(result.use(Results.json())).thenReturn(jsonSerialization);
		return jsonSerialization;
	}
}
