package br.usp.mobile.bandejao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@ApplicationScoped
@Component
public class ComentariosBandejao {
	private HashMap<String, List<Comentario>> comentarios;
	private final PeriodoDeRefeicao periodo;
	private Calendar ultimaAtualizacao;
	
	public ComentariosBandejao(PeriodoDeRefeicao periodo) {
		this.periodo = periodo;
		this.comentarios = new HashMap<String, List<Comentario>>();
		ultimaAtualizacao = Calendar.getInstance();
		for(Bandejoes bandejao : Bandejoes.values()) {
			this.comentarios.put(bandejao.getNome(), new ArrayList<Comentario>());
		}
	}
	
	public List<Comentario> getComentarios(String bandejao) {
		return comentarios.get(bandejao);
	}

	private void limpaComentarios() {
		for(String key : comentarios.keySet()) {
			comentarios.put(key, new ArrayList<Comentario>());
		}
	}

	public void adiciona(Comentario comentario, String bandejao) {
		if(!periodo.mesmoPeriodo(ultimaAtualizacao, Calendar.getInstance())) {
			limpaComentarios();
		}
		comentarios.get(bandejao).add(comentario);
	}
}
