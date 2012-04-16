package br.usp.mobile.bandejao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class PeriodoDeRefeicaoTest {
	@Before
	public void setUp() {
	}
	
	@Test
	public void calculaPeriodoDoCafeDaManha() throws Exception {
		for(int hora = 0; hora < 11; hora++) {
			assertEquals(PeriodoDeRefeicao.MANHA, PeriodoDeRefeicao.calculaPeriodo(hora));
		}
	}
	
	@Test
	public void calculaPeriodoDoAlmoco() throws Exception {
		for(int hora = 11; hora < 16; hora++) {
			assertEquals(PeriodoDeRefeicao.TARDE, PeriodoDeRefeicao.calculaPeriodo(hora));
		}
	}
	
	@Test
	public void calculaPeriodoDaJanta() throws Exception {
		for(int hora = 16; hora < 24; hora++) {
			assertEquals(PeriodoDeRefeicao.NOITE, PeriodoDeRefeicao.calculaPeriodo(hora));
		}
	}
	
	@Test
	public void anosDiferentes() throws Exception {
		assertFalse(PeriodoDeRefeicao.mesmoPeriodo(new GregorianCalendar(2012, 3, 12, 15, 0), 
												   new GregorianCalendar(2013, 3, 12, 15, 0)));
	}
	
	@Test
	public void mesesDiferentes() throws Exception {
		assertFalse(PeriodoDeRefeicao.mesmoPeriodo(new GregorianCalendar(2012, 3, 12, 15, 0), 
												   new GregorianCalendar(2012, 4, 12, 15, 0)));
	}
	
	@Test
	public void diasDiferentes() throws Exception {
		assertFalse(PeriodoDeRefeicao.mesmoPeriodo(new GregorianCalendar(2012, 3, 12, 15, 0), 
												   new GregorianCalendar(2012, 3, 13, 15, 0)));
	}
	
	@Test
	public void periodosDiferentes() throws Exception {
		assertFalse(PeriodoDeRefeicao.mesmoPeriodo(new GregorianCalendar(2012, 3, 12, 12, 0), 
												   new GregorianCalendar(2012, 3, 12, 24, 0)));
	}
	
	@Test
	public void mesmoPeriodo() throws Exception {
		assertTrue(PeriodoDeRefeicao.mesmoPeriodo(new GregorianCalendar(2012, 3, 12, 0, 0), 
												  new GregorianCalendar(2012, 3, 12, 9, 0)));
	}
}
