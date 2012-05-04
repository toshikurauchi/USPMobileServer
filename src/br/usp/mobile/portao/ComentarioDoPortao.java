package br.usp.mobile.portao;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.validator.NotNull;

@Entity
public class ComentarioDoPortao {

	@Id
	@GeneratedValue
	private Long id;
	private int numero;
	@NotNull
	private Long timestamp;
	private Double latitude;
	private Double longitude;
	@Lob
	private String comentario;
	@Enumerated(EnumType.STRING)
	@NotNull
	private Sentido sentido;
	
	public ComentarioDoPortao() {
	}
	
	public ComentarioDoPortao(int numero, Long timestamp, Double latitude, Double longitude, String comentario, Sentido sentido) {
		this.numero = numero;
		this.timestamp = timestamp;
		this.latitude = latitude;
		this.longitude = longitude;
		this.comentario = comentario;
		this.setSentido(sentido);
	}

	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Sentido getSentido() {
		return sentido;
	}

	public void setSentido(Sentido sentido) {
		this.sentido = sentido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
