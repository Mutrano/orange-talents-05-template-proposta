package br.com.zupacademy.mario.proposta.domain.Cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Renegociacao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Integer quantidade;
	private BigDecimal valor;
	private LocalDateTime dataDeCriacao;
	private UUID uuid;
	
	@OneToOne
	private Cartao cartao;
	
	@Deprecated
	public Renegociacao() {}
	
	
	public Renegociacao(UUID uuid, Integer quantidade, BigDecimal valor,
			LocalDateTime dataDeCriacao,Cartao cartao ) {
		this.uuid=uuid;
		this.quantidade = quantidade;
		this.valor = valor;
		this.dataDeCriacao = dataDeCriacao;
		this.cartao= cartao;

	}
}
