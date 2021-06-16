package br.com.zupacademy.mario.proposta.domain.Cartao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Biometria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String fingerprint;
	
	private LocalDate dataDeCriacao;
	
	@ManyToOne
	private Cartao cartao;
	
	private UUID uuid = UUID.randomUUID(); 
	
	public Biometria() {
	}
	
	public Biometria(String fingerprint, LocalDate dataDeCriacao, Cartao cartao) {
		this.fingerprint = fingerprint;
		this.dataDeCriacao = dataDeCriacao;
		this.cartao = cartao;
	}
	
	public Long getId() {
		return id;
	}
	
	public UUID getUuid() {
		return uuid;
	}
	
	
	
	
	
}
