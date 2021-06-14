package br.com.zupacademy.mario.proposta.domain.Cartao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.zupacademy.mario.proposta.domain.Proposta.Proposta;

@Entity
public class Cartao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDateTime emitidoEm;

	private String titular;

	@OneToMany(mappedBy = "cartao",cascade = CascadeType.ALL)
	private List<Bloqueio> bloqueios = new ArrayList<>();
	
	@OneToMany(mappedBy = "cartao",cascade = CascadeType.ALL)
	private List<AvisoViagem> avisos = new ArrayList<>();
	
	@OneToMany(mappedBy = "cartao",cascade = CascadeType.ALL)
	private List<CarteiraDigital> carteiras = new ArrayList<>();
	
	@OneToMany(mappedBy = "cartao",cascade = CascadeType.ALL)
	private List<Parcela> parcelas = new ArrayList();
	
	@OneToOne(cascade = CascadeType.ALL)
	private Renegociacao renegociacao;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Vencimento vencimento;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Proposta proposta;
	
	private Integer limite;
	
	private String uuid;
	
	@Deprecated
	public Cartao() {}
	
	public Cartao(String uuid,LocalDateTime emitidoEm, String titular,
			Proposta proposta, Integer limite) {
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.proposta = proposta;
		this.limite = limite;
		this.uuid = uuid;
	}

	public void setParcelas(List<Parcela> parcelas) {
		this.parcelas = parcelas;
	}

	public void setBloqueios(List<Bloqueio> bloqueios) {
		this.bloqueios = bloqueios;
	}

	public void setAvisos(List<AvisoViagem> avisos) {
		this.avisos = avisos;
	}

	public void setCarteiras(List<CarteiraDigital> carteiras) {
		this.carteiras = carteiras;
	}

	public void setRenegociacao(Renegociacao renegociacao) {
		this.renegociacao = renegociacao;
	}

	public void setVencimento(Vencimento vencimento) {
		this.vencimento = vencimento;
	}
	
}
