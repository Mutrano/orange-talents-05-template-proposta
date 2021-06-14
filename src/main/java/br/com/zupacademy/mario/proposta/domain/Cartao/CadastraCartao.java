package br.com.zupacademy.mario.proposta.domain.Cartao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.zupacademy.mario.proposta.domain.Proposta.InformacaoProposta;
import br.com.zupacademy.mario.proposta.domain.Proposta.Proposta;
import br.com.zupacademy.mario.proposta.domain.Proposta.PropostaRepository;
import feign.FeignException;
import feign.FeignException.FeignClientException;
import feign.FeignException.FeignServerException;

@Component
public class CadastraCartao {
	
	private PropostaRepository propostaRepository;
	private CartaoRepository cartaoRepository;
	private CartaoClient cartaoClient;
	

	public CadastraCartao(PropostaRepository propostaRepository, CartaoRepository cartaoRepository,
			CartaoClient cartaoClient) {
		this.propostaRepository = propostaRepository;
		this.cartaoRepository = cartaoRepository;
		this.cartaoClient = cartaoClient;
	}
	
	@Transactional
	@Scheduled(fixedDelay = 5000)
	public void atrelaCartao() {

		
		var propostas = propostaRepository.findByCartaoEquals(null);
		
		propostas.forEach(proposta -> {
			try {

				var cartaoResponse = cartaoClient.cadastraCartao(InformacaoProposta.deProposta(proposta));
				var cartao = cartaoResponse.toModel(proposta);
				proposta.setCartao(cartao);
				cartaoRepository.save(cartao);
				
			}
			catch(FeignException expn) {
				expn.printStackTrace();
			}
		});
	}
}