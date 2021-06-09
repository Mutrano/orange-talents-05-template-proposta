package br.com.zupacademy.mario.proposta.domain.Proposta;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import feign.FeignException.FeignClientException;
import feign.FeignException.FeignServerException;

@RestController
@RequestMapping("/Propostas")
public class PropostaController {

	@Autowired
	private PropostaRepository repository;

	@Autowired
	private PropostaClient propostaClient;

	@PersistenceContext
	private EntityManager em;

	@PostMapping
	public ResponseEntity<Void> cadastraProposta(@RequestBody @Valid CadastraPropostaRequest request,
			UriComponentsBuilder uricomponentsBuilder) {

		var possiveisPropostas = repository.findByDocumento(request.getDocumento());

		if (!possiveisPropostas.get().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Ja existe proposta para este cliente");
		}

		var propostaSalva = repository.save(request.toModel());
		var solicitacaoAnalise = new SolicitacaoAnalise(propostaSalva.getDocumento(), propostaSalva.getNome(),
				propostaSalva.getId().toString());
		try {
			var resultadoAnalise = propostaClient.solicitaAnalise(solicitacaoAnalise);

			if (resultadoAnalise.getResultadoSolicitacao() == EstadoAnalise.SEM_RESTRICAO) {
				propostaSalva.setEstadoProposta(EstadoProposta.ELEGIVEL);
				propostaSalva = repository.save(propostaSalva);
			}
		} catch (FeignClientException expn) {
		} catch (FeignServerException expn) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Tivemos um problema processando sua proposta");
		}

		var uri = uricomponentsBuilder.path("/Propostas/{id}").buildAndExpand(propostaSalva.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
