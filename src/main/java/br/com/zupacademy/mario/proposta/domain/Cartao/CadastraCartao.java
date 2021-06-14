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

@Component
public class CadastraCartao {
	private CartaoClient cartaoClient;
	private EntityManager em;
	

	public CadastraCartao(EntityManager em,
			CartaoClient cartaoClient) {
		this.cartaoClient = cartaoClient;
		this.em=em;
	}
	
	@Transactional
	@Scheduled(fixedDelay = 5000)
	public void atrelaCartao() {
		var query = em.createQuery("SELECT p FROM Proposta p where p.cartao=null AND p.estadoProposta LIKE 'ELEGIVEL'");
		
		var propostas = (List<Proposta>) query.getResultList();
		propostas.forEach(proposta -> {
			try {

				var cartaoResponse = cartaoClient.cadastraCartao(InformacaoProposta.deProposta(proposta));
				var cartao = cartaoResponse.toModel(proposta);
				proposta.setCartao(cartao);
				em.persist(cartao);
				
			}
			catch(FeignException expn) {
				expn.printStackTrace();
			}
		});
	}
}