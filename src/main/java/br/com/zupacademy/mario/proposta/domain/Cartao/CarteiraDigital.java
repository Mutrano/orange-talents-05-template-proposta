package br.com.zupacademy.mario.proposta.domain.Cartao;

import java.time.Instant;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	private Instant associadaEm = Instant.now();
	
	@Enumerated(EnumType.STRING)
	private EmissorCarteira emissor;
	
	private UUID uuid;
	
	@ManyToOne
	private Cartao cartao;
	
	@Deprecated
	public CarteiraDigital(){}
	//quando somos nós criando a carteira
	public CarteiraDigital(UUID uuid, String email, EmissorCarteira emissor , Cartao cartao) {
		this.uuid = uuid;
		this.email = email;
		this.emissor = emissor;
		this.cartao=cartao;
	}
	//quando recebemos a carteira
	public CarteiraDigital(UUID uuid, String email,Instant associadaEm, EmissorCarteira emissor , Cartao cartao) {
		this.uuid = uuid;
		this.email = email;
		this.associadaEm=associadaEm;
		this.emissor = emissor;
		this.cartao=cartao;
	}
	
	public String getEmail() {
		return email;
	}
	
	public EmissorCarteira getEmissor() {
		return emissor;
	}

	public Object getUuid() {
		return uuid;
	}
	
	
	
	
}
