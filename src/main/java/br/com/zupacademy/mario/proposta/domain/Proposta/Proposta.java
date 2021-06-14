package br.com.zupacademy.mario.proposta.domain.Proposta;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.zupacademy.mario.proposta.domain.Cartao.Cartao;

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

	@Enumerated(EnumType.STRING)
	private EstadoProposta estadoProposta;

	private UUID uuid = UUID.randomUUID();

	@OneToOne
	private Cartao cartao;
	
	@Deprecated
	public Proposta() {
	}

	public Proposta(String documento, String email, String nome, String endereco, Double salario) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
		this.estadoProposta = EstadoProposta.NAO_ELEGIVEL;
	}

	public Long getId() {
		return id;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setEstadoProposta(EstadoProposta estadoProposta) {
		this.estadoProposta = estadoProposta;
	}

	public String getNome() {
		return nome;
	}

	public String getDocumento() {
		return documento;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}
}
