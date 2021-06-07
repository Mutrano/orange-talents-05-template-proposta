package br.com.zupacademy.mario.proposta.domain.Proposta;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String documento;
	private String email;
	private String nome;
	private String endereco;
	private Double salario;

	public Proposta() {	}
	
	public Proposta(String documento, String email, String nome, String endereco,Double salario) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Long getId() {
		return id;
	}
}
