package br.usp.mobile.bandejao;

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

	public List<Comentario> listaComentariosDo(String nome) {
		return session.createQuery("from Comentario as c where c.bandejao = :bandejao")
				.setParameter("bandejao", nome)
				.list();
	}
}
