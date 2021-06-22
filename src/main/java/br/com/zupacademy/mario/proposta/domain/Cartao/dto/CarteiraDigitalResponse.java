package br.com.zupacademy.mario.proposta.domain.Cartao.dto;

import java.time.Instant;
import java.util.UUID;

import br.com.zupacademy.mario.proposta.domain.Cartao.Cartao;
import br.com.zupacademy.mario.proposta.domain.Cartao.CarteiraDigital;
import br.com.zupacademy.mario.proposta.domain.Cartao.EmissorCarteira;

public class CarteiraDigitalResponse {
	
	private String id;
	private String email;
	private Instant associadaEm;
	private String emissor;

	@Deprecated
	public CarteiraDigitalResponse() {
	}

	public CarteiraDigitalResponse(String id, String email, Instant associadaEm, String emissor) {
		this.id = id;
		this.email = email;
		this.associadaEm = associadaEm;
		this.emissor = emissor;
	}
	
	public CarteiraDigital toModel(Cartao cartao) {
		return new CarteiraDigital(UUID.fromString(id),email, associadaEm, EmissorCarteira.valueOf(emissor), cartao);
	}

}
