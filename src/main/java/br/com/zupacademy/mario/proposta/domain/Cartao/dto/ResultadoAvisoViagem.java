package br.com.zupacademy.mario.proposta.domain.Cartao.dto;

public class ResultadoAvisoViagem {

	private EstadoAviso estado;

	public ResultadoAvisoViagem() {
	}

	public ResultadoAvisoViagem(EstadoAviso estado) {
		this.estado = estado;
	}
	public boolean ehFalho() {
		return this.estado.equals(EstadoAviso.FALHA);
	}
}
