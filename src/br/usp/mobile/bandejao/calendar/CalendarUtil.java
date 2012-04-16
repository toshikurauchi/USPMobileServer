package br.usp.mobile.bandejao.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {

	public static Calendar hojeAs(int hora) {
		Calendar agora = Calendar.getInstance();
		if(hora == 24) {
			agora.add(Calendar.DAY_OF_MONTH, 1);
			hora = 0;
		}
		agora.set(Calendar.HOUR_OF_DAY, hora);
		String formatado = new SimpleDateFormat("yyyy-MM-dd-").format(agora.getTime());
		Date horaCerta = null;
		try {
			horaCerta = new SimpleDateFormat("yyyy-MM-dd-HH").parse(formatado + String.format("%02d", hora));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar horaCertaCalendar = Calendar.getInstance();
		horaCertaCalendar.setTime(horaCerta);
		return horaCertaCalendar;
	}

}
