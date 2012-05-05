package br.usp.mobile.portao;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Resource
public class PortaoController {
	private Result result;
	private PortaoDAO dao;
	private static List<ComentarioDoPortao> comentariosMock;

	public PortaoController(Result result, PortaoDAO dao) {
		this.result = result;
		this.dao = dao;
		inicializaComentariosMock();
	}
	
	@Path("/portao/{numero}/{sentido}")
	@Post
	public void adicionaComentario(ComentarioDoPortao comentario, int numero, Sentido sentido) {
		if(!portaoValido(numero)) {
			result.notFound();
			return;
		}
		if(comentario == null) {
			comentario = new ComentarioDoPortao();
		}
		comentario.setNumero(numero);
		comentario.setSentido(sentido);
		if(comentario.getTimestamp() == null) {
			comentario.setTimestamp(System.currentTimeMillis());
		}
		
		if(portaoMock(numero)) {
			inicializaComentariosMock();
			comentariosMock.add(comentario);
		}
		else {
			dao.insere(comentario);
		}
		
		result.nothing();
	}

	@Path("/portao/{numero}/{sentido}")
	@Get
	public void listaComentarios(int numero, Long aPartirDe, Sentido sentido) {
		if(!portaoValido(numero)) {
			result.notFound();
			return;
		}
		
		List<ComentarioDoPortao> comentarios;
		if(portaoMock(numero)) {
			comentarios = comentariosMock;
		}
		else if(aPartirDe == null) {
			comentarios = dao.listaComentariosDoPortaoDaUltimaHora(numero, sentido);
		}
		else {
			comentarios = dao.listaComentariosDoPortaoAPartirDe(numero, aPartirDe, sentido);
		}
		result.use(Results.http()).body(criaStringDeJSON(comentarios));
	}
	
	private boolean portaoMock(int numero) {
		return numero == 100;
	}

	private String criaStringDeJSON(final List<ComentarioDoPortao> comentarios) {
		Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
			public boolean shouldSkipField(FieldAttributes field) {
				return field.getName().equals("id") || field.getName().equals("sentido");
			}
			
			public boolean shouldSkipClass(Class<?> arg0) {
				return false;
			}
		}).create();
		return gson.toJson(new ComentariosDoPortao(comentarios));
	}

	private boolean portaoValido(int numero) {
		return numero == 1 || numero == 2 || numero == 3 || numero == 100;
	}
	
	private class ComentariosDoPortao {
		public List<ComentarioDoPortao> list;
		
		public ComentariosDoPortao(List<ComentarioDoPortao> comentarios) {
			list = comentarios;
		}
	}
	
	private void inicializaComentariosMock() {	
		if(comentariosMock == null || comentariosMock.size() > 1000) {
			comentariosMock = new ArrayList<ComentarioDoPortao>();
			comentariosMock.add(new ComentarioDoPortao(0, 12345678l, 4312.4321, 2345.2345, "Algum comentário", Sentido.ENTRANDO, 100.45, "toshi"));
			comentariosMock.add(new ComentarioDoPortao(0, 12345678l, 4312.4321, 2345.2345, "Algum comentário", Sentido.ENTRANDO, 100.45, null));
			comentariosMock.add(new ComentarioDoPortao(0, 12345678l, 4312.4321, 2345.2345, "Algum comentário", Sentido.ENTRANDO, null, null));
			comentariosMock.add(new ComentarioDoPortao(0, 12345678l, 4312.4321, 2345.2345, "Algum comentário", Sentido.SAINDO, null, null));
			comentariosMock.add(new ComentarioDoPortao(0, 12345678l, 4312.4321, 2345.2345, null, Sentido.ENTRANDO, null, null));
			comentariosMock.add(new ComentarioDoPortao(0, 12345678l, 4312.4321, null, null, Sentido.ENTRANDO, null, null));
			comentariosMock.add(new ComentarioDoPortao(0, 12345678l, null, null, null, Sentido.ENTRANDO, null, null));
			comentariosMock.add(new ComentarioDoPortao(0, 12345678l, null, null, null, Sentido.ENTRANDO, null, null));
		}
	}
}
