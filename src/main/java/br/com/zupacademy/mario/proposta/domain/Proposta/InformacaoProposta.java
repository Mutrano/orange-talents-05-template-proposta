 package br.com.zupacademy.mario.proposta.domain.Proposta;

public class InformacaoProposta {
	private String documento;
	private String nome;
	private String idProposta;
	
	@Deprecated
	public InformacaoProposta() {}

	public InformacaoProposta(String documento, String nome, String idProposta) {
		this.documento = documento;
		this.nome = nome;
		this.idProposta = idProposta;
	}

	public static InformacaoProposta deProposta(Proposta proposta) {
		return new InformacaoProposta(proposta.getDocumento(),proposta.getNome(),proposta.getUuid().toString());
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getIdProposta() {
		return idProposta;
	}
	

}