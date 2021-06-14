package br.com.zupacademy.mario.proposta.domain.Cartao;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Bloqueio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime bloqueadoEm;
	private String sistemaResponsavel;
	private boolean ativo;
	private UUID uuid;
	
	@ManyToOne
	private Cartao cartao;
	
	public Bloqueio() {}
	
	public Bloqueio(UUID uuid,LocalDateTime bloqueadoEm, String sistemaResponsavel, boolean ativo,Cartao cartao) {
		this.uuid = uuid;
		this.bloqueadoEm = bloqueadoEm;
		this.sistemaResponsavel = sistemaResponsavel;
		this.ativo = ativo;
		this.cartao=cartao;
	}

}
