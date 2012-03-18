package br.usp.mobile.bandejao;

public enum Bandejoes {
	FISICA("fisica"), 
	QUIMICA("quimica"),
	CENTRAL("central"),
	COCESP("cocesp"),
	PROFESSORES("professores"),
	DIREITO("direito"),
	SAUDE_PUBLICA("saude_publica"),
	ENFERMAGEM("enfermagem");
	
	private final String nome;

	private Bandejoes(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
