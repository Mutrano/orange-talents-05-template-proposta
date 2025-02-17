package br.com.zupacademy.mario.proposta.domain.Cartao.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.zupacademy.mario.proposta.domain.Cartao.Cartao;
import br.com.zupacademy.mario.proposta.domain.Cartao.Renegociacao;

public class RenegociacaoResponse {
	private String id;
	private Integer quantidade;
	private BigDecimal valor;
	private LocalDateTime dataDeCriacao;

	@Deprecated
	public RenegociacaoResponse() {
	}

	public RenegociacaoResponse(String id, Integer quantidade, BigDecimal valor, LocalDateTime dataDeCriacao) {
		this.id = id;
		this.quantidade = quantidade;
		this.valor = valor;
		this.dataDeCriacao = dataDeCriacao;
	}
	public Renegociacao toModel(Cartao cartao) {
		return new Renegociacao(UUID.fromString(id), quantidade, valor, dataDeCriacao, cartao);
	}

}
