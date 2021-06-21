package br.com.zupacademy.mario.proposta.domain.Cartao.dto;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.zupacademy.mario.proposta.domain.Cartao.Cartao;
import br.com.zupacademy.mario.proposta.domain.Cartao.Parcela;

public class ParcelaResponse {

	private Integer quantidade;
	private BigDecimal valor;
	private String id;

	@Deprecated
	public ParcelaResponse() {
	}

	public ParcelaResponse(String id,Integer quantidade, BigDecimal valor ) {
		this.quantidade = quantidade;
		this.valor = valor;
		this.id = id;
	}
	
	public Parcela toModel(Cartao cartao) {
		return new Parcela(UUID.fromString(id),quantidade, valor, cartao);
	}

}
