package br.usp.mobile.bandejao;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;


public class BandejaoControllerTest {
	private Result result;
	private ComentariosBandejao comentarios;
	private BandejaoController controller;

	@Before
	public void setUp() {
		result = new MockResult();
		comentarios = mock(ComentariosBandejao.class);
		controller = new BandejaoController(comentarios, result);
	}
	
	@Test
	public void adicionaComentarioNoBandejaoCorreto() throws Exception {
		Comentario comentario = new Comentario();
		String nome = Bandejoes.FISICA.getNome();
		controller.adiciona(comentario, nome);
		verify(comentarios).adiciona(comentario, nome);
	}
}
