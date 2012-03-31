package br.usp.mobile.bandejao;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Comentario {

	@Id
	@GeneratedValue
	private Long id;
	@Lob
	private String texto;
	@Enumerated(EnumType.STRING)
	private TamanhoDaFila fila;
	private String bandejao;

	public Comentario() {
		
	}
	
	public Comentario(String texto, TamanhoDaFila fila) {
		this.texto = texto;
		this.fila = fila;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public void setBandejao(String bandejao) {
		this.bandejao = bandejao;
	}
	
	public void setFila(TamanhoDaFila fila) {
		this.fila = fila;
	}

	public String getBandejao() {
		return bandejao;
	}
}
