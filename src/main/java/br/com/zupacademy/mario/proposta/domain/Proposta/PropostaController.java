package br.com.zupacademy.mario.proposta.domain.Proposta;

import java.util.Objects;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.mario.proposta.metrics.MinhasMetricas;
import feign.FeignException.FeignClientException;
import feign.FeignException.FeignServerException;
import io.opentracing.Tracer;

@RestController
@RequestMapping("/Propostas")
public class PropostaController {

	@Autowired
	private PropostaRepository repository;

	@Autowired
	private PropostaClient propostaClient;

	@Autowired
	private MinhasMetricas metricas;
    
	@Autowired
	private Tracer tracer;
	
	@PostMapping
	public ResponseEntity<Void> cadastraProposta(@RequestBody @Valid CadastraPropostaRequest request,
			UriComponentsBuilder uricomponentsBuilder) {
		
		var activeSpan = tracer.activeSpan();
		var email = activeSpan.getBaggageItem("user.email");
		if(Objects.nonNull(email)) {
			activeSpan.setBaggageItem("user.email", email);
		}
		else {
			activeSpan.setBaggageItem("user.email", request.getEmail());
			activeSpan.setTag("user.email", request.getEmail());
		}
		
		var possiveisPropostas = repository.findByDocumento(request.getDocumento());

		if (!possiveisPropostas.get().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Ja existe proposta para este cliente");
		}

		var propostaSalva = repository.save(request.toModel());
		var solicitacaoAnalise = new InformacaoProposta(propostaSalva.getDocumento(), propostaSalva.getNome(),
				propostaSalva.getId().toString());
		
		//checa com a api legado a elegibilidade da proposta
		try {
			var resultadoAnalise = propostaClient.solicitaAnalise(solicitacaoAnalise);

			if (resultadoAnalise.getResultadoSolicitacao() == EstadoAnalise.SEM_RESTRICAO) {
				propostaSalva.setEstadoProposta(EstadoProposta.ELEGIVEL);
				propostaSalva = repository.save(propostaSalva);
				metricas.contaPropostaElegivel();
			}
		} catch (FeignClientException expn) {
			metricas.contaPropostaNaoElegivel();
		} catch (FeignServerException expn) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Tivemos um problema processando sua proposta");
		}

		var uri = uricomponentsBuilder.path("/Propostas/{uuid}").buildAndExpand(propostaSalva.getUuid()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{uuid}")
	public ResponseEntity<AcompanhamentoPropostaResponse> acompanhamentoProposta(@PathVariable UUID uuid){
		
		var activeSpan = tracer.activeSpan();
		var email = activeSpan.getBaggageItem("user.email");
		if(Objects.nonNull(email)) {
			activeSpan.setBaggageItem("user.email", email);
		}
		var propostaEncontrada = repository.findByUuid(uuid);
		
		var acompanhamentoProposta = AcompanhamentoPropostaResponse.deProposta(propostaEncontrada.orElseThrow(
				()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Proposta não encontrada")  ));
		metricas.contaAcompanhamentoProposta();
		return ResponseEntity.ok().body(acompanhamentoProposta);
	}
	

}
