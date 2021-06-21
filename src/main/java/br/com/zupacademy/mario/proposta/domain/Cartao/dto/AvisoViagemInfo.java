package br.com.zupacademy.mario.proposta.domain.Cartao.dto;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zupacademy.mario.proposta.domain.Cartao.AvisoViagem;
import br.com.zupacademy.mario.proposta.domain.Cartao.Cartao;

public class AvisoViagemInfo {
	@FutureOrPresent
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private LocalDate validoAte;
	
	@NotBlank
	private String destino;

	@Deprecated
	public AvisoViagemInfo() {
	}

	public AvisoViagemInfo(LocalDate validoAte, String destino) {
		this.validoAte = validoAte;
		this.destino = destino;
	}
	//quando recebemos o aviso
	public AvisoViagem toModel(Cartao cartao) {
		return new AvisoViagem(validoAte, destino, cartao,null,null);
	}
	//quando somos nos criando o aviso
	public AvisoViagem toModel(Cartao cartao, String ipDaSolicitacao,String userAgentDaSolicitacao) {
		return new AvisoViagem(validoAte, destino, cartao, ipDaSolicitacao, userAgentDaSolicitacao);
	}

	public LocalDate getValidoAte() {
		return validoAte;
	}

	public void setValidoAte(LocalDate validoAte) {
		this.validoAte = validoAte;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}
	
}