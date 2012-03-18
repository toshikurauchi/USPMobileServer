package br.usp.mobile.bandejao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class ComentariosBandejaoTest {
	private ComentariosBandejao comentarios;
	private PeriodoDeRefeicao periodo;

	@Before
	public void setUp() {
		periodo = mock(PeriodoDeRefeicao.class);
		comentarios = new ComentariosBandejao(periodo);
	}
	
	@Test
	public void adicionaComentarioNoBandejao() throws Exception {
		Comentario comentario = new Comentario("Comida boa!", TamanhoDaFila.MEDIA);
		String cocesp = Bandejoes.COCESP.getNome();
		
		when(periodo.mesmoPeriodo(any(Calendar.class), any(Calendar.class))).thenReturn(true);
		comentarios.adiciona(comentario, cocesp);
		
		assertEquals(comentario, comentarios.getComentarios(cocesp).get(0));
	}
	
	@Test
	public void limpaComentariosQuandoMudouDePeriodo() throws Exception {
		String cocesp = Bandejoes.COCESP.getNome();
		
		when(periodo.mesmoPeriodo(any(Calendar.class), any(Calendar.class))).thenReturn(true);
		comentarios.adiciona(new Comentario("Peixe :(", TamanhoDaFila.SEM_FILA), cocesp);
		
		Comentario comentario = new Comentario("Comida boa!", TamanhoDaFila.MEDIA);
		
		when(periodo.mesmoPeriodo(any(Calendar.class), any(Calendar.class))).thenReturn(false);
		comentarios.adiciona(comentario, cocesp);
		
		assertEquals(1, comentarios.getComentarios(cocesp).size());
		assertEquals(comentario, comentarios.getComentarios(cocesp).get(0));
	}
	
	@Test
	public void temListaDeComentariosParaTodosOsBandejoes() throws Exception {
		for(Bandejoes bandejao : Bandejoes.values()) {
			assertNotNull(comentarios.getComentarios(bandejao.getNome()));
		}
	}
}
