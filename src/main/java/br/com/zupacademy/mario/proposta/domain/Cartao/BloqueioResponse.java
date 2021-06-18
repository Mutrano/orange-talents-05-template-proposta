package br.com.zupacademy.mario.proposta.domain.Cartao;

import java.time.Instant;
import java.util.UUID;

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
