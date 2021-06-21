package br.com.zupacademy.mario.proposta.domain.Cartao.dto;

import br.com.zupacademy.mario.proposta.domain.Cartao.EstadoBloqueio;

public class ResultadoBloqueio{
	private EstadoBloqueio resultado;

	public ResultadoBloqueio() {
	}
	public ResultadoBloqueio(EstadoBloqueio resultado) {
		this.resultado = resultado;
	}

	public EstadoBloqueio getResultado() {
		return resultado;
	}
	public boolean ehBloqueado() {
		return this.resultado == EstadoBloqueio.BLOQUEADO;
	}

	public void setResultado(EstadoBloqueio resultado) {
		this.resultado = resultado;
	}
	
	
}