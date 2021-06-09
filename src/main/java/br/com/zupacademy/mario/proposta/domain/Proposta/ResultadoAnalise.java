package br.com.zupacademy.mario.proposta.domain.Proposta;

public class ResultadoAnalise {
	private String documento;
	private String nome;

	private String idProposta;
	private EstadoAnalise resultadoSolicitacao;
	
	@Deprecated
	public ResultadoAnalise() {
		
	}

	public ResultadoAnalise(String documento, String nome, String idProposta, EstadoAnalise resultadoSolicitacao) {
		this.documento = documento;
		this.nome = nome;
		this.idProposta = idProposta;
		this.resultadoSolicitacao = resultadoSolicitacao;
	}

	public String getIdProposta() {
		return idProposta;
	}

	public EstadoAnalise getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}
}
