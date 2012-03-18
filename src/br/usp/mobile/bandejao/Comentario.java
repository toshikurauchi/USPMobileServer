package br.usp.mobile.bandejao;

public class Comentario {

	private String texto;
	private TamanhoDaFila fila;

	public Comentario() {
		
	}
	
	public Comentario(String texto, TamanhoDaFila fila) {
		this.texto = texto;
		this.fila = fila;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
}
