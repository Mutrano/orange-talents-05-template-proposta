package br.com.zupacademy.mario.proposta.domain.Cartao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.zupacademy.mario.proposta.domain.Proposta.Proposta;

@Entity
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime emitidoEm;
	
	private String titular;
	
	@Enumerated(EnumType.STRING)
	private EstadoCartao estadoCartao;
	
	@OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Bloqueio> bloqueios = new ArrayList<>();
	
	@OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<AvisoViagem> avisos = new ArrayList<>();
	
	@OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CarteiraDigital> carteiras = new ArrayList<>();
	
	@OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Parcela> parcelas = new ArrayList<>();
	
	
	@OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Biometria> biometrias = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Renegociacao renegociacao;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Vencimento vencimento;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Proposta proposta;

	private Integer limite;

	private String uuid;

	@Deprecated
	public Cartao() {
	}
	
	public Cartao(String uuid, LocalDateTime emitidoEm, String titular, EstadoCartao estadoCartao, Proposta proposta,
			Integer limite) {
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.estadoCartao = estadoCartao;
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

	public void addBiometria(Biometria biometria) {
		this.biometrias.add(biometria);
	}
	public void addAvisoViagem(AvisoViagem avisoViagem) {
		this.avisos.add(avisoViagem);
	}
	public EstadoCartao getEstadoCartao() {
		return estadoCartao;
	}

	public void setEstadoCartao(EstadoCartao estadoCartao) {
		this.estadoCartao = estadoCartao;
	}

	public String getUuid() {
		return uuid;

	}
}