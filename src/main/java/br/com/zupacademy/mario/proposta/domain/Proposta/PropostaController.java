package br.com.zupacademy.mario.proposta.domain.Proposta;

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


@RestController
@RequestMapping("/Propostas")
public class PropostaController {

	@Autowired
	private PropostaRepository repository;

	@PostMapping
	public ResponseEntity<Void> cadastraProposta(@RequestBody @Valid CadastraPropostaRequest dto, UriComponentsBuilder uricomponentsBuilder){
		var possiveisPropostas = repository.findByDocumento(dto.getDocumento());
		if(!possiveisPropostas.get().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Ja existe proposta para este cliente");
		}
		
		var propostaSalva = repository.save(dto.toModel());
		
		var uri = uricomponentsBuilder.path("/Propostas/{id}").buildAndExpand(propostaSalva.getId()).toUri();
		return ResponseEntity.created(uri).build();
	
	}

}
