package br.com.zupacademy.mario.proposta.exception;

import java.util.ArrayList;
import java.util.List;

public class ErroDeValidacao extends ErroPadrao {
	private List<CampoMensagem> erros = new ArrayList<>();

	public ErroDeValidacao(Integer status, String erro, String mensagem, String caminho) {
		super(status, erro, mensagem, caminho);
	}

	public void addErro(CampoMensagem erro) {
		erros.add(erro);
	}

	public List<CampoMensagem> getErros() {
		return erros;
	}

}
