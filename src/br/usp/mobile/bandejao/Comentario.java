package br.usp.mobile.bandejao;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
	private Calendar hora;

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

	public void setHora(Calendar hora) {
		this.hora = hora;
	}
	
	public String getBandejao() {
		return bandejao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public TamanhoDaFila getFila() {
		return fila;
	}

	public Calendar getHora() {
		return hora;
	}

	public String getHoraFormatada() {
		if(hora == null) {
			return "";
		}
		return new SimpleDateFormat("ss-mm-HH-dd-MM-yy").format(hora.getTime());
	}
	
	
}
