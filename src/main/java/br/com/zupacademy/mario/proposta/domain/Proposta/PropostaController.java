package br.com.zupacademy.mario.proposta.domain.Proposta;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/Propostas")
public class PropostaController {

	@Autowired
	private PropostaRepository repository;

	@PostMapping
	public ResponseEntity<Void> cadastraProposta(@RequestBody @Valid CadastraPropostaRequest dto, UriComponentsBuilder uricomponentsBuilder){
		var propostaSalva = repository.save(dto.toModel());
		var uri = uricomponentsBuilder.path("/Propostas/{id}").buildAndExpand(propostaSalva.getId()).toUri();
		return ResponseEntity.created(uri).build();
	
	}

}
