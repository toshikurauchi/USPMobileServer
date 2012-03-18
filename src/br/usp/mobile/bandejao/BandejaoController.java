package br.usp.mobile.bandejao;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Resource
public class BandejaoController {
	private ComentariosBandejao comentarios;
	private Result result;
	private List<Comentario> comentariosMock;
	private Collection<String> bandejoes;

	public BandejaoController(ComentariosBandejao comentarios, Result result) {
		this.comentarios = comentarios;
		this.result = result;
		bandejoes = new HashSet<String>();
		for(Bandejoes bandejao : Bandejoes.values()) {
			bandejoes.add(bandejao.getNome());
		}
		comentariosMock = Arrays.asList(new Comentario("Peixe duro", TamanhoDaFila.PEQUENA), 
										new Comentario("Não tem fila!", TamanhoDaFila.SEM_FILA));
	}
	
	@Path("/bandejao")
	@Get
	public void listaBandejoes() {
		result.use(Results.json()).from(bandejoes).serialize();
	}
	
	@Path("/bandejao/{nome}/add")
	public void adiciona(Comentario comentario, String nome) {
		comentarios.adiciona(comentario, nome);
		result.nothing();
	}
	
	@Path("/bandejao/{nome}")
	@Get
	public void lista(String nome) {
		if(nome.equals("mock")) {
			result.use(Results.json()).from(comentariosMock).serialize();
		}
		else if(bandejaoValido(nome))
		result.use(Results.json()).from(comentarios.getComentarios(nome)).serialize();
	}

	private boolean bandejaoValido(String nome) {
		return bandejoes.contains(nome);
	}
}
