package br.com.zupacademy.mario.proposta.domain.Cartao;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
public class VencimentoResponse {
	@JsonProperty
	private String id;
	@JsonProperty
	private Integer dia;
	@JsonProperty
	private LocalDateTime dataDeCriacao;

	@Deprecated
	public VencimentoResponse() {
	}

	public VencimentoResponse(String id, Integer dia, LocalDateTime dataDeCriacao) {
		this.id = id;
		this.dia = dia;
		this.dataDeCriacao = dataDeCriacao;
	}

	public Vencimento toModel(Cartao cartao ) {
		return new Vencimento(UUID.fromString(id), dia, dataDeCriacao, cartao);
	}


	


}
