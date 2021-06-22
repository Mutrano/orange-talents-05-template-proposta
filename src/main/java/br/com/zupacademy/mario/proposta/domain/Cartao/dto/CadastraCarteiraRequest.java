package br.com.zupacademy.mario.proposta.domain.Cartao.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.mario.proposta.domain.Cartao.EmissorCarteira;

public class CadastraCarteiraRequest {
	@Email
	@NotBlank
	private String email;

	@NotNull
	private EmissorCarteira emissor;
	
	@Deprecated
	public CadastraCarteiraRequest() {
	}

	public CadastraCarteiraRequest(@Email @NotBlank String email, @NotNull EmissorCarteira emissor) {
		this.email = email;
		this.emissor = emissor;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EmissorCarteira getEmissor() {
		return emissor;
	}

	public void setEmissor(EmissorCarteira emissor) {
		this.emissor = emissor;
	}
}
