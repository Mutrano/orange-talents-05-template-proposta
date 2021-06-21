package br.com.zupacademy.mario.proposta.domain.Cartao;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.mario.proposta.domain.Cartao.dto.AvisoViagemInfo;
import br.com.zupacademy.mario.proposta.metrics.MinhasMetricas;

@RestController
public class CadastraAvisoController {

	private CartaoRepository repository;
	private MinhasMetricas metricas;
	public CadastraAvisoController(CartaoRepository repository, MinhasMetricas metricas) {
		this.repository = repository;
		this.metricas = metricas;
	}

	@PostMapping("/Cartoes/{uuid}/AvisosViagem")
	ResponseEntity<Void> cadastraAviso(@PathVariable String uuid, @Valid @RequestBody AvisoViagemInfo request,HttpServletRequest httpRequest){
		var ipdaRequest = httpRequest.getRemoteAddr();
		var userAgentDaRequest = httpRequest.getHeader("User-Agent");
		
		var cartaoEncontrado = repository.findByUuid(uuid)
			.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"O cartão não foi encontrado"));
		
		cartaoEncontrado.addAvisoViagem(request.toModel(cartaoEncontrado,ipdaRequest,userAgentDaRequest));
		repository.save(cartaoEncontrado);
		metricas.contaAviso();
		return ResponseEntity.ok().build();
	}
}