package br.com.zupacademy.mario.proposta.domain.Cartao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.zupacademy.mario.proposta.domain.Proposta.Proposta;

public class CadastraCartaoResponse {
	private String id;

	private LocalDateTime emitidoEm;

	private String titular;

	private List<BloqueioResponse> bloqueios = new ArrayList<>();

	private List<AvisoViagemResponse> avisos = new ArrayList<>();

	private List<CarteiraDigitalResponse> carteiras = new ArrayList<>();

	private List<ParcelaResponse> parcelas = new ArrayList();

	private Integer limite;

	private RenegociacaoResponse renegociacao;

	private VencimentoResponse vencimento;

	private String idProposta;

	@Deprecated
	public CadastraCartaoResponse() {
	}

	public CadastraCartaoResponse(String id, LocalDateTime emitidoEm, String titular, List<BloqueioResponse> bloqueios,
			List<AvisoViagemResponse> avisos, List<CarteiraDigitalResponse> carteiras, List<ParcelaResponse> parcelas,
			Integer limite, RenegociacaoResponse renegociacao, VencimentoResponse vencimento, String idProposta) {
		this.id = id;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.bloqueios = bloqueios;
		this.avisos = avisos;
		this.carteiras = carteiras;
		this.parcelas = parcelas;
		this.limite = limite;
		this.renegociacao = renegociacao;
		this.vencimento = vencimento;
		this.idProposta = idProposta;
	}

	public Cartao toModel(Proposta proposta) { 
		var cartao = new Cartao(id,emitidoEm, titular,EstadoCartao.ATIVO, proposta, limite);
		cartao.setBloqueios(bloqueios.stream()
				.map(dto -> dto.toModel(cartao)).collect(Collectors.toList()));
		
		cartao.setAvisos(avisos.stream()
				.map(dto -> dto.toModel(cartao)).collect(Collectors.toList()));
		
		cartao.setParcelas(parcelas.stream()
				.map(dto -> dto.toModel(cartao)).collect(Collectors.toList()));
		
		cartao.setCarteiras(carteiras.stream()
				.map(dto -> dto.toModel(cartao)).collect(Collectors.toList()));
		if(renegociacao!=null) {
			cartao.setRenegociacao(renegociacao.toModel(cartao));
		}
		cartao.setVencimento(vencimento.toModel(cartao));
		return cartao;

	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public void setEmitidoEm(LocalDateTime emitidoEm) {
		this.emitidoEm = emitidoEm;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public List<BloqueioResponse> getBloqueios() {
		return bloqueios;
	}

	public void setBloqueios(List<BloqueioResponse> bloqueios) {
		this.bloqueios = bloqueios;
	}

	public List<AvisoViagemResponse> getAvisos() {
		return avisos;
	}

	public void setAvisos(List<AvisoViagemResponse> avisos) {
		this.avisos = avisos;
	}

	public List<CarteiraDigitalResponse> getCarteiras() {
		return carteiras;
	}

	public void setCarteiras(List<CarteiraDigitalResponse> carteiras) {
		this.carteiras = carteiras;
	}

	public List<ParcelaResponse> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<ParcelaResponse> parcelas) {
		this.parcelas = parcelas;
	}

	public Integer getLimite() {
		return limite;
	}

	public void setLimite(Integer limite) {
		this.limite = limite;
	}

	public RenegociacaoResponse getRenegociacao() {
		return renegociacao;
	}

	public void setRenegociacao(RenegociacaoResponse renegociacao) {
		this.renegociacao = renegociacao;
	}

	public VencimentoResponse getVencimento() {
		return vencimento;
	}

	public void setVencimento(VencimentoResponse vencimento) {
		this.vencimento = vencimento;
	}

	public String getIdProposta() {
		return idProposta;
	}

	public void setIdProposta(String idProposta) {
		this.idProposta = idProposta;
	}

	@Override
	public String toString() {
		return "CadastraCartaoResponse [id=" + id + ", emitidoEm=" + emitidoEm + ", titular=" + titular + ", bloqueios="
				+ bloqueios + ", avisos=" + avisos + ", carteiras=" + carteiras + ", parcelas=" + parcelas + ", limite="
				+ limite + ", renegociacao=" + renegociacao + ", vencimento=" + vencimento + ", idProposta="
				+ idProposta + "]";
	}

}
