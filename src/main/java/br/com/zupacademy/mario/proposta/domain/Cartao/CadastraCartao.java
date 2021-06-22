package br.com.zupacademy.mario.proposta.domain.Cartao;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.zupacademy.mario.proposta.domain.Proposta.InformacaoProposta;
import br.com.zupacademy.mario.proposta.domain.Proposta.Proposta;
import feign.FeignException;
import io.opentracing.Tracer;

@Component
public class CadastraCartao {
	private CartaoClient cartaoClient;
	private EntityManager em;
	private Tracer tracer;

	public CadastraCartao(CartaoClient cartaoClient, EntityManager em, Tracer tracer) {
		this.cartaoClient = cartaoClient;
		this.em = em;
		this.tracer = tracer;
	}

	@Transactional
	@Scheduled(fixedDelay = 5000)
	public void atrelaCartao() {
		var query = em.createQuery("SELECT p FROM Proposta p where p.cartao=null AND p.estadoProposta LIKE 'ELEGIVEL'");

		var propostas = (List<Proposta>) query.getResultList();
		propostas.forEach(proposta -> {
			// propaga tracer do Jaeger
			var activeSpan = tracer.activeSpan();
			var email = activeSpan.getBaggageItem("user.email");
			if (Objects.nonNull(email)) {
				activeSpan.setBaggageItem("user.email", email);
			} 
			else {
				activeSpan.setBaggageItem("user.email", proposta.getEmail());
			}
			try {
				var cartaoResponse = cartaoClient.cadastraCartao(InformacaoProposta.deProposta(proposta));
				var cartao = cartaoResponse.toModel(proposta);
				proposta.setCartao(cartao);
				em.persist(cartao);
			} catch (FeignException expn) {
				expn.printStackTrace();
			}
		});
	}
}