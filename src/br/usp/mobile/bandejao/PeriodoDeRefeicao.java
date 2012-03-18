package br.usp.mobile.bandejao;

import java.util.Calendar;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@Component
@ApplicationScoped
public class PeriodoDeRefeicao {

	public boolean mesmoPeriodo(Calendar data1, Calendar data2) {
		return camposIguais(data1, data2, Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH) &&
			   calculaPeriodo(data1.get(Calendar.HOUR_OF_DAY)) == calculaPeriodo(data2.get(Calendar.HOUR_OF_DAY));
	}

	public int calculaPeriodo(int hora) {
		if(0 <= hora && hora < 11) {
			return 1;
		}
		else if(11 <= hora && hora < 16) {
			return 2;
		}
		return 3;
	}

	private boolean camposIguais(Calendar data1, Calendar data2, int ... campos) {
		for(int campo : campos) {
			if(data1.get(campo) != data2.get(campo)) {
				return false;
			}
		}
		return true;
	}
	
	
}
