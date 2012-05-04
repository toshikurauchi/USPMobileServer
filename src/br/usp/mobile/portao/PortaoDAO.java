package br.usp.mobile.portao;

import java.util.List;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class PortaoDAO {
	
	private Session session;

	public PortaoDAO(Session session) {
		this.session = session;
	}

	public void insere(ComentarioDoPortao comentario) {
		session.save(comentario);
	}

	public List<ComentarioDoPortao> listaComentariosDoPortaoDaUltimaHora(int numero, Sentido sentido) {
		return listaComentariosDoPortaoAPartirDe(numero, System.currentTimeMillis() - 1000*60*60, sentido);
	}
	
	public List<ComentarioDoPortao> listaComentariosDoPortaoAPartirDe(int numero, long timestamp, Sentido sentido) {
		return session.createQuery("from ComentarioDoPortao as c " +
						    "where c.numero = :numero and " +
						          "c.timestamp >= :timestamp and " +
						    	  "c.sentido = :sentido")
	        .setParameter("numero", numero)
	        .setParameter("timestamp", timestamp)
	        .setParameter("sentido", sentido)
	        .list();
	}

}
