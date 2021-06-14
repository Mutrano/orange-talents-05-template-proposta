package br.com.zupacademy.mario.proposta.domain.Cartao;

import java.time.LocalDate;

public class AvisoViagemResponse {
	private LocalDate validoAte;
	private String destino;

	@Deprecated
	public AvisoViagemResponse() {
	}

	public AvisoViagemResponse(LocalDate validoAte, String destino) {
		this.validoAte = validoAte;
		this.destino = destino;
	}

	public AvisoViagem toModel(Cartao cartao) {
		return new AvisoViagem(validoAte, destino, cartao);
	}
}
