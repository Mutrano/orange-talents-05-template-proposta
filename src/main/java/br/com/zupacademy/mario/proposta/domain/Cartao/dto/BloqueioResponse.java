package br.com.zupacademy.mario.proposta.domain.Cartao.dto;

import java.time.Instant;
import java.util.UUID;

import br.com.zupacademy.mario.proposta.domain.Cartao.Bloqueio;
import br.com.zupacademy.mario.proposta.domain.Cartao.Cartao;

public class BloqueioResponse {

	private UUID id;
	private Instant bloqueadoEm;
	private String sistemaResponsavel;
	private boolean ativo;

	@Deprecated
	public BloqueioResponse() {
	}
	
	public BloqueioResponse(UUID id, Instant bloqueadoEm, String sistemaResponsavel, boolean ativo) {
		this.id = id;
		this.bloqueadoEm = bloqueadoEm;
		this.sistemaResponsavel = sistemaResponsavel;
		this.ativo = ativo;
	}

	public Bloqueio toModel(Cartao cartao) {
		return new Bloqueio(id,bloqueadoEm, sistemaResponsavel, ativo,cartao,null,null);
	}
}
