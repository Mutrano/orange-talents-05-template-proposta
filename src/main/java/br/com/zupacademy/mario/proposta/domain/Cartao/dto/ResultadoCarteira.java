package br.com.zupacademy.mario.proposta.domain.Cartao.dto;

import java.util.UUID;

import br.com.zupacademy.mario.proposta.domain.Cartao.Cartao;
import br.com.zupacademy.mario.proposta.domain.Cartao.CarteiraDigital;

public class ResultadoCarteira {
	private EstadoCarteira resultado;
	private String id;
	
	@Deprecated
	public ResultadoCarteira() {
	}

	public ResultadoCarteira(EstadoCarteira resultado, String id) {
		this.resultado = resultado;
		this.id = id;
	}
	public CarteiraDigital paraCarteira(CadastraCarteiraRequest request,Cartao cartao) {
		return new CarteiraDigital(UUID.fromString(id), request.getEmail(), request.getEmissor(), cartao);
	}
	public EstadoCarteira getResultado() {
		return resultado;
	}

	public void setResultado(EstadoCarteira resultado) {
		this.resultado = resultado;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
