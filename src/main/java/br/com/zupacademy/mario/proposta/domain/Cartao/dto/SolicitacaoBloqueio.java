package br.com.zupacademy.mario.proposta.domain.Cartao.dto;

public class SolicitacaoBloqueio{
	private String sistemaResponsavel;

	public SolicitacaoBloqueio() {
	}
	public SolicitacaoBloqueio(String sistemaResponsavel) {
		this.sistemaResponsavel = sistemaResponsavel;
	}
	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}
	public void setSistemaResponsavel(String sistemaResponsavel) {
		this.sistemaResponsavel = sistemaResponsavel;
	}
	
	
}