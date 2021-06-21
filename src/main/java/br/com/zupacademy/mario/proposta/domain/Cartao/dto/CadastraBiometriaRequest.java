package br.com.zupacademy.mario.proposta.domain.Cartao.dto;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.mario.proposta.domain.shared.Base64;

public class CadastraBiometriaRequest {
	
	
	@Base64(campo = "biometria")
	@NotBlank
	private String biometria;
	
	@Deprecated
	public CadastraBiometriaRequest() {}
	
	public CadastraBiometriaRequest(String biometria) {
		this.biometria = biometria;
	}
	
	public String getBiometria() {
		return biometria;
	}
	
	
}
