package br.com.zupacademy.mario.proposta.exception;

public class CampoMensagem {
	private String campo;
	private String mensagem;
	
	public CampoMensagem(String campo, String mensagem) {
		this.campo = campo;
		this.mensagem = mensagem;
	}

	public String getCampo() {
		return campo;
	}

	public String getMensagem() {
		return mensagem;
	}

	
}
