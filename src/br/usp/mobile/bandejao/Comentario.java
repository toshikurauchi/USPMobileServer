package br.usp.mobile.bandejao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;

import com.google.gson.annotations.SerializedName;

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
	@SerializedName("horaDescartada")
	private Calendar hora;
	@Transient
	@SerializedName("hora")
	private String horaFormatada;

	public Comentario(String texto, TamanhoDaFila fila, Calendar hora) {
		this.texto = texto;
		this.fila = fila;
		this.hora = hora;
	}

	public Comentario() {
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
		calculaHoraFormatada();
		return this.horaFormatada;
	}

	public void calculaHoraFormatada() {
		if(hora == null) {
			this.horaFormatada = "";
		}
		else {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ss-mm-HH-dd-MM-yy");
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
			this.horaFormatada = simpleDateFormat.format(hora.getTime());
		}
	}
	
	
}
