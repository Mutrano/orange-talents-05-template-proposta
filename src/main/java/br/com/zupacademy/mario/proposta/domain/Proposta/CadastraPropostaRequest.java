package br.com.zupacademy.mario.proposta.domain.Proposta;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import br.com.zupacademy.mario.proposta.domain.shared.CpfOrCnpj;

public class CadastraPropostaRequest {
	
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	private String nome;
	
	@CpfOrCnpj
	@NotBlank
	private String documento;
	
	@NotBlank
	private String endereco;

	@NotNull
	@PositiveOrZero
	private Double salario;

	@Deprecated
	public CadastraPropostaRequest() {
	}

	public CadastraPropostaRequest(@Email @NotBlank String email, @NotBlank String nome, @NotBlank String documento,
			@NotBlank String endereco, @NotNull Double salario) {
		this.email = email;
		this.nome = nome;
		this.documento = documento;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Proposta toModel() {
		return new Proposta(this.documento, this.email, this.nome, this.endereco,this.salario);
	}
	
	
	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEndereco() {
		return endereco;
	}

	public Double getSalario() {
		return salario;
	}

}
