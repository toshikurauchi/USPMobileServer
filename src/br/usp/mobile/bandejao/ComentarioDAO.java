package br.usp.mobile.bandejao;

import static br.usp.mobile.bandejao.calendar.CalendarUtil.horaAtual;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class ComentarioDAO {
	private final Session session;

	public ComentarioDAO(Session session) {
		this.session = session;
	}

	public void insere(Comentario comentario) {
		session.save(comentario);
	}

	public List<Comentario> listaComentariosAtuaisDo(String nome) {
		PeriodoDeRefeicao periodo = PeriodoDeRefeicao.calculaPeriodo(horaAtual().get(Calendar.HOUR_OF_DAY));
		return session.createQuery("from Comentario as c " +
								   "where c.bandejao = :bandejao and " +
								         "c.hora >= :inicioPeriodo and " +
								         "c.hora < :fimPeriodo")
				.setParameter("bandejao", nome)
				.setParameter("inicioPeriodo", periodo.getInicioHoje())
				.setParameter("fimPeriodo", periodo.getFimHoje())
				.list();
	}
}
