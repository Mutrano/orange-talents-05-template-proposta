package br.com.zupacademy.mario.proposta.exception;

import java.time.LocalDateTime;

public class ErroPadrao {
	private LocalDateTime instante;
	private Integer status;
	private String erro;
	private String mensagem;
	private String caminho;
	
	public ErroPadrao(Integer status, String erro, String mensagem, String caminho) {
		this.instante= LocalDateTime.now();
		this.status = status;
		this.erro = erro;
		this.mensagem = mensagem;
		this.caminho = caminho;
	}

	public LocalDateTime getInstante() {
		return instante;
	}

	public Integer getStatus() {
		return status;
	}

	public String getErro() {
		return erro;
	}

	public String getMensagem() {
		return mensagem;
	}

	public String getCaminho() {
		return caminho;
	}
}
