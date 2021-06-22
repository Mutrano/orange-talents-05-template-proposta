package br.com.zupacademy.mario.proposta.domain.Cartao.dto;

public class InclusaoCarteira {
	private String email;
	private String carteira;
	
	@Deprecated
	public InclusaoCarteira() {
	}
	
	public InclusaoCarteira(String email, String carteira) {
		this.email = email;
		this.carteira = carteira;
	}
	public static InclusaoCarteira daRequisicao(CadastraCarteiraRequest request) {
			return new InclusaoCarteira(request.getEmail(),request.getEmissor().toString());
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCarteira() {
		return carteira;
	}

	public void setCarteira(String carteira) {
		this.carteira = carteira;
	}
	
	
}
