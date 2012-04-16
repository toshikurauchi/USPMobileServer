package br.usp.mobile.bandejao.calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;

public class CalendarUtilTest {
	@Test
	public void hojeAsZeroHoras() throws Exception {
		Calendar hojeAsZero = CalendarUtil.hojeAs(0);
		Calendar agora = Calendar.getInstance();
		assertTrue(mesmoDia(hojeAsZero, agora));
		assertEquals(0, hojeAsZero.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, hojeAsZero.get(Calendar.MINUTE));
		assertEquals(0, hojeAsZero.get(Calendar.SECOND));
		assertEquals(0, hojeAsZero.get(Calendar.MILLISECOND));
	}
	
	@Test
	public void hojeAoMeioDia() throws Exception {
		Calendar hojeAoMeioDia = CalendarUtil.hojeAs(12);
		Calendar agora = Calendar.getInstance();
		assertTrue(mesmoDia(hojeAoMeioDia, agora));
		assertEquals(12, hojeAoMeioDia.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, hojeAoMeioDia.get(Calendar.MINUTE));
		assertEquals(0, hojeAoMeioDia.get(Calendar.SECOND));
		assertEquals(0, hojeAoMeioDia.get(Calendar.MILLISECOND));
	}
	
	@Test
	public void hojeAs16h() throws Exception {
		Calendar hojeAs16h = CalendarUtil.hojeAs(16);
		Calendar agora = Calendar.getInstance();
		assertTrue(mesmoDia(hojeAs16h, agora));
		assertEquals(16, hojeAs16h.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, hojeAs16h.get(Calendar.MINUTE));
		assertEquals(0, hojeAs16h.get(Calendar.SECOND));
		assertEquals(0, hojeAs16h.get(Calendar.MILLISECOND));
	}
	
	@Test
	public void hojeAs24() throws Exception {
		Calendar hojeAs24 = CalendarUtil.hojeAs(24);
		Calendar amanha = Calendar.getInstance();
		amanha.add(Calendar.DAY_OF_MONTH, 1);
		assertTrue(mesmoDia(hojeAs24, amanha));
		assertEquals(0, hojeAs24.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, hojeAs24.get(Calendar.MINUTE));
		assertEquals(0, hojeAs24.get(Calendar.SECOND));
		assertEquals(0, hojeAs24.get(Calendar.MILLISECOND));
	}

	private boolean mesmoDia(Calendar calendar1, Calendar calendar2) {
		return compara(calendar1, calendar2, Calendar.YEAR) &&
			   compara(calendar1, calendar2, Calendar.MONTH) &&
			   compara(calendar1, calendar2, Calendar.DAY_OF_MONTH);
	}
	
	private boolean compara(Calendar calendar1, Calendar calendar2, int field) {
		return calendar1.get(field) == calendar2.get(field);
	}
}
