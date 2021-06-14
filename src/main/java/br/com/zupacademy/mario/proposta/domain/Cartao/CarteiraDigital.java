package br.com.zupacademy.mario.proposta.domain.Cartao;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "carteira_digital")
public class CarteiraDigital {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String email;
	private LocalDateTime associadaEm;
	private String emissor;
	private UUID uuid;
	
	@ManyToOne
	private Cartao cartao;
	
	@Deprecated
	public CarteiraDigital(){}
	
	public CarteiraDigital(UUID uuid, String email, LocalDateTime associadaEm, String emissor , Cartao cartao) {
		this.uuid = uuid;
		this.email = email;
		this.associadaEm = associadaEm;
		this.emissor = emissor;
		this.cartao=cartao;
	}
	
	
}
