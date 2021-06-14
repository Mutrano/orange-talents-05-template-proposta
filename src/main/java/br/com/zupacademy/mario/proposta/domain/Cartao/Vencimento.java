package br.com.zupacademy.mario.proposta.domain.Cartao;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Vencimento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer dia;
	private LocalDateTime dataDeCriacao;
	private UUID uuid;

	@OneToOne
	private Cartao cartao;

	@Deprecated
	public Vencimento() {
	}

	public Vencimento(UUID uuid, Integer dia, LocalDateTime dataDeCriacao, Cartao cartao) {
		this.uuid = uuid;
		this.dia = dia;
		this.dataDeCriacao = dataDeCriacao;
		this.cartao = cartao;

	}

}
