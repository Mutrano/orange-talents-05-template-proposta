package br.com.zupacademy.mario.proposta.domain.Cartao.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.zupacademy.mario.proposta.domain.Cartao.Cartao;
import br.com.zupacademy.mario.proposta.domain.Cartao.CarteiraDigital;

public class CarteiraDigitalResponse {
	
	private String id;
	private String email;
	private LocalDateTime associadaEm;
	private String emissor;

	@Deprecated
	public CarteiraDigitalResponse() {
	}

	public CarteiraDigitalResponse(String id, String email, LocalDateTime associadaEm, String emissor) {
		this.id = id;
		this.email = email;
		this.associadaEm = associadaEm;
		this.emissor = emissor;
	}
	
	public CarteiraDigital toModel(Cartao cartao) {
		return new CarteiraDigital(UUID.fromString(id),email, associadaEm, emissor, cartao);
	}

}
