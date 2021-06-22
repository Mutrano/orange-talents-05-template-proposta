package br.com.zupacademy.mario.proposta.domain.Cartao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.mario.proposta.domain.Cartao.dto.CadastraCarteiraRequest;
import br.com.zupacademy.mario.proposta.domain.Cartao.dto.InclusaoCarteira;
import br.com.zupacademy.mario.proposta.domain.shared.ExecutaTransacao;
import br.com.zupacademy.mario.proposta.metrics.MinhasMetricas;
import feign.FeignException;

@RestController
public class CadastraCarteiraController {

	private MinhasMetricas metricas;
	private EntityManager em;
	private CartaoClient cartaoClient;
	private ExecutaTransacao executaTransacao;







	public CadastraCarteiraController(MinhasMetricas metricas, EntityManager em, CartaoClient cartaoClient,
			ExecutaTransacao executaTransacao) {
		this.metricas = metricas;
		this.em = em;
		this.cartaoClient = cartaoClient;
		this.executaTransacao = executaTransacao;
	}







	@PostMapping("/Cartoes/{uuid}/Carteiras")
	public ResponseEntity<Void> cadastraCarteira(@PathVariable String uuid,
			@Valid @RequestBody CadastraCarteiraRequest request, UriComponentsBuilder uribuilder) {

		// caso o cartão não exista retorna 404
		var query = em.createQuery("SELECT c from Cartao c WHERE c.uuid=:pUuid").setParameter("pUuid", uuid);

		var result = (List<Cartao>) query.getResultList();

		if (result.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O cartão informado não existe");
		}

		// caso o cartão já tenha uma carteira do tipo EmissorCarteira da requisição retorna 422
		
		query = em.createQuery("SELECT c from carteira_digital c where c.cartao.uuid=:pUuid AND c.emissor=:pEmissor")
				.setParameter("pUuid", uuid).setParameter("pEmissor", request.getEmissor());

		if (!query.getResultList().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
					"A carteira informada já foi associada ao cartão");
		}

		var cartaoEncontrado = result.get(0);
		
		//notifica o sistema legado da criação da carteira, caso ele criar salva a carteira e retorna 201, caso contrário não faz nada e retorna 500.
		try {
			var resultadoCarteira = cartaoClient.notificaCarteira(uuid, InclusaoCarteira.daRequisicao(request));
			var carteira = resultadoCarteira.paraCarteira(request, cartaoEncontrado);
			executaTransacao.salvaEComita(carteira);
			var uri = uribuilder.fromPath("/Cartoes/{uuid}/Carteiras/{uuidCarteira}").buildAndExpand(uuid,carteira.getUuid()).toUri();
			metricas.contaCarteiraDigitalSucesso(carteira.getEmissor());
			return ResponseEntity.created(uri).build();
		} catch (FeignException expn) {
			metricas.contaCarteiraDigitalFalha(request.getEmissor());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Não conseguimos processar sua requisição");
		}

	}
}
