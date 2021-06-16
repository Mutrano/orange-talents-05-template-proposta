package br.com.zupacademy.mario.proposta.domain.Proposta;

import java.util.UUID;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class AcompanhamentoPropostaResponse {
	private UUID id;
	private String documento;
	private String email;
	private String nome;
	private String endereco;
	private Double salario;

	@Enumerated(EnumType.STRING)
	private EstadoProposta estadoProposta;

	private String idCartao;

	@Deprecated
	public AcompanhamentoPropostaResponse() {}
	
	public AcompanhamentoPropostaResponse(UUID id, String documento, String email, String nome, String endereco,
			Double salario, EstadoProposta estadoProposta, String idCartao) {
		this.id = id;
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
		this.estadoProposta = estadoProposta;
		this.idCartao = idCartao;
	}
	public static AcompanhamentoPropostaResponse deProposta(Proposta proposta) {
		
		var id=proposta.getUuid();
		var documento=proposta.getDocumento();
		var email=proposta.getEmail();
		var nome = proposta.getNome();
		var endereco= proposta.getEndereco();
		var salario = proposta.getSalario();
		var estadoProposta = proposta.getEstadoProposta();
		var idCartao = proposta.temCartao()? proposta.getCartao().getUuid() : null;
		
		return new AcompanhamentoPropostaResponse(id, documento, email, nome, endereco, salario, estadoProposta, idCartao);
	}

	public UUID getId() {
		return id;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public Double getSalario() {
		return salario;
	}

	public EstadoProposta getEstadoProposta() {
		return estadoProposta;
	}

	public String getIdCartao() {
		return idCartao;
	}
	
	
}
