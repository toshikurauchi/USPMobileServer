package br.usp.mobile.portao;

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

	public PortaoController(Result result, PortaoDAO dao) {
		this.result = result;
		this.dao = dao;
	}
	
	@Path("/portao/{numero}")
	@Post
	public void adicionaComentario(ComentarioDoPortao comentario, int numero) {
		if(!portaoValido(numero)) {
			result.notFound();
			return;
		}
		if(comentario == null) {
			comentario = new ComentarioDoPortao();
		}
		comentario.setNumero(numero);
		if(comentario.getTimestamp() == null) {
			comentario.setTimestamp(System.currentTimeMillis());
		}
		
		dao.insere(comentario);
		result.nothing();
	}

	@Path("/portao/{numero}")
	@Get
	public void listaComentarios(int numero, Long aPartirDe) {
		if(!portaoValido(numero)) {
			result.notFound();
			return;
		}
		
		List<ComentarioDoPortao> comentarios;
		if(aPartirDe == null) {
			comentarios = dao.listaComentariosDoPortaoDaUltimaHora(numero);
		}
		else {
			comentarios = dao.listaComentariosDoPortaoAPartirDe(numero, aPartirDe);
		}
		result.use(Results.http()).body(criaStringDeJSON(comentarios));
	}
	
	private String criaStringDeJSON(final List<ComentarioDoPortao> comentarios) {
		Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
			public boolean shouldSkipField(FieldAttributes field) {
				return field.getName().equals("id");
			}
			
			public boolean shouldSkipClass(Class<?> arg0) {
				return false;
			}
		}).create();
		return gson.toJson(new ComentariosDoPortao(comentarios));
	}

	private boolean portaoValido(int numero) {
		return numero == 1 || numero == 2 || numero == 3;
	}
	
	private class ComentariosDoPortao {
		public List<ComentarioDoPortao> list;
		
		public ComentariosDoPortao(List<ComentarioDoPortao> comentarios) {
			list = comentarios;
		}
	}
}
