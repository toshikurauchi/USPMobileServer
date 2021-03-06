package br.usp.mobile.bandejao;

import java.util.Calendar;

import br.usp.mobile.bandejao.calendar.CalendarUtil;

public enum PeriodoDeRefeicao {
	CAFE(0, 10),
	ALMOCO(10, 16),
	JANTAR(16, 24);
	
	private final int inicio;
	private final int fim;

	private PeriodoDeRefeicao(int inicio, int fim) {
		this.inicio = inicio;
		this.fim = fim;
	}

	public Calendar getInicioHoje() {
		return CalendarUtil.hojeAs(inicio);
	}
	
	public Calendar getFimHoje() {
		return CalendarUtil.hojeAs(fim);
	}
	
	public static PeriodoDeRefeicao calculaPeriodo(int hora) {
		if(CAFE.inicio <= hora && hora < CAFE.fim) {
			return CAFE;
		}
		else if(ALMOCO.inicio <= hora && hora < ALMOCO.fim) {
			return ALMOCO;
		}
		return JANTAR;
	}

	public static boolean mesmoPeriodo(Calendar data1, Calendar data2) {
		return camposIguais(data1, data2, Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH) &&
			   calculaPeriodo(data1.get(Calendar.HOUR_OF_DAY)) == calculaPeriodo(data2.get(Calendar.HOUR_OF_DAY));
	}
	
	private static boolean camposIguais(Calendar data1, Calendar data2, int ... campos) {
		for(int campo : campos) {
			if(data1.get(campo) != data2.get(campo)) {
				return false;
			}
		}
		return true;
	}
}
