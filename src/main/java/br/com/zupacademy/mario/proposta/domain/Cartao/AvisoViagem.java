package br.com.zupacademy.mario.proposta.domain.Cartao;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "aviso_viagem")
public class AvisoViagem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate validoAte;
	private String destino;
	private UUID uuid = UUID.randomUUID();
	
	@ManyToOne
	private Cartao cartao;
	
	@Deprecated
	public AvisoViagem() {}

	public AvisoViagem(LocalDate validoAte, String destino,Cartao cartao) {
		this.validoAte = validoAte;
		this.destino = destino;
		this.cartao=cartao;
	}

}
