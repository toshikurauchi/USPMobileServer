package br.usp.mobile.bandejao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Resource
public class BandejaoController {
	private Result result;
	private static List<Comentario> comentariosMock;
	private Collection<String> bandejoes;
	private ComentarioDAO dao;

	public BandejaoController(Result result, ComentarioDAO dao) {
		this.result = result;
		this.dao = dao;
		bandejoes = new HashSet<String>();
		for(Bandejoes bandejao : Bandejoes.values()) {
			bandejoes.add(bandejao.getNome());
		}
		inicializaMock();
	}
	
	@Path("/bandejao")
	@Get
	public void listaBandejoes() {
		result.use(Results.json()).from(bandejoes).serialize();
	}
	
	@Path("/bandejao/{nome}")
	@Post
	public void adiciona(Comentario comentario, String nome) {
		comentario.setHora(Calendar.getInstance());
		if(isMock(nome)) {
			inicializaMock();
			comentario.setBandejao(nome);
			comentariosMock.add(comentario);
			
			result.nothing();
		}
		else if(bandejaoValido(nome)) {
			comentario.setBandejao(nome);
			dao.insere(comentario);
			
			result.nothing();
		}
		else {
			result.notFound();
		}
	}
	
	@Path("/bandejao/{nome}")
	@Get
	public void lista(String nome) {
		if(isMock(nome)) {
			result.use(Results.http()).body(criaStringDeJSON(comentariosMock));
		}
		else if(bandejaoValido(nome)) {
			result.use(Results.http()).body(criaStringDeJSON(dao.listaComentariosAtuaisDo(nome)));
		}
		else {
			result.notFound();
		}
	}
	
	@Path("/bandejao/{nome}/periodo")
	@Get
	public void periodo(String nome) {
		PeriodoDeRefeicao periodo = PeriodoDeRefeicao.calculaPeriodo(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		result.use(Results.http()).body(periodo.name());
	}

	private String criaStringDeJSON(List<Comentario> comentarios) {
		boolean primeiro = true;
		StringBuilder jsonBuilder = new StringBuilder();
		jsonBuilder.append("{\"list\":[");
		for (Comentario comentario : comentarios) {
			if(primeiro) {
				primeiro = false;
			}
			else {
				jsonBuilder.append(",");
			}
			jsonBuilder.append("{\"texto\":\"" + 
								comentario.getTexto() + 
								"\",\"hora\":\"" + 
								comentario.getHoraFormatada() + 
								"\",\"fila\":\"" +
								comentario.getFila() +
								"\"}");
			
		}
		jsonBuilder.append("]}");
		return jsonBuilder.toString();
	}

	private boolean isMock(String nome) {
		return "mock".equals(nome);
	}

	private boolean bandejaoValido(String nome) {
		return bandejoes.contains(nome);
	}
	
	private void inicializaMock() {
		if(comentariosMock == null || comentariosMock.size() > 500) {
			comentariosMock = new ArrayList<Comentario>();
			comentariosMock.add(new Comentario("Peixe duro", TamanhoDaFila.PEQUENA, Calendar.getInstance()));
			comentariosMock.add(new Comentario("Não tem fila!", TamanhoDaFila.SEM_FILA, Calendar.getInstance()));
		}
	}
}
