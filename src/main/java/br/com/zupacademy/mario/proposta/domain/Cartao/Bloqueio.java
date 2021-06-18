package br.com.zupacademy.mario.proposta.domain.Cartao;

import java.time.Instant;
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
	private Instant bloqueadoEm;
	private String sistemaResponsavel;
	private boolean ativo;
	private UUID uuid;
	private String ipDoSolicitante;
	private String userAgentDoSolicitante;
	
	@ManyToOne
	private Cartao cartao;
	
	public Bloqueio() {}
	
	public Bloqueio(UUID uuid,Instant bloqueadoEm, String sistemaResponsavel, boolean ativo,Cartao cartao, String ipDoSolicitante, String userAgentdoSolicitante) {
		this.uuid = uuid;
		this.bloqueadoEm = bloqueadoEm;
		this.sistemaResponsavel = sistemaResponsavel;
		this.ativo = ativo;
		this.cartao=cartao;
		this.ipDoSolicitante=ipDoSolicitante;
		this.userAgentDoSolicitante=userAgentdoSolicitante;
	}
	public UUID getUuid() {
		return uuid;
	}
}
