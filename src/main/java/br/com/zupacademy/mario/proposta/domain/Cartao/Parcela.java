package br.com.zupacademy.mario.proposta.domain.Cartao;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Parcela {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer quantidade;
	private BigDecimal valor;
	private UUID uuid=UUID.randomUUID();
	
	@ManyToOne
	private Cartao cartao;
	
	@Deprecated
	public Parcela() {}
	
	public Parcela(UUID uuid, Integer quantidade, BigDecimal valor,Cartao cartao) {
		this.uuid=uuid;
		this.quantidade = quantidade;
		this.valor = valor;
		this.cartao=cartao;
	}	
}
