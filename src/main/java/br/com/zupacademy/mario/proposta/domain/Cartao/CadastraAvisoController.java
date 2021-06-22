package br.com.zupacademy.mario.proposta.domain.Cartao;

import java.util.Objects;

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
import feign.FeignException;
import io.opentracing.Tracer;

@RestController
public class CadastraAvisoController {

	private CartaoRepository repository;
	private MinhasMetricas metricas;
	private CartaoClient cartaoClient;
	private Tracer tracer;
	
	public CadastraAvisoController(CartaoRepository repository, MinhasMetricas metricas, CartaoClient cartaoClient,
			Tracer tracer) {
		this.repository = repository;
		this.metricas = metricas;
		this.cartaoClient = cartaoClient;
		this.tracer = tracer;
	}

	@PostMapping("/Cartoes/{uuid}/AvisosViagem")
	ResponseEntity<Void> cadastraAviso(@PathVariable String uuid, @Valid @RequestBody AvisoViagemInfo request,HttpServletRequest httpRequest){
		//propaga span do Jaeger
		var activeSpan = tracer.activeSpan();
		var email = activeSpan.getBaggageItem("user.email");
		if(Objects.nonNull(email)) {
			activeSpan.setBaggageItem("user.email", email);
		}
		
		var ipdaRequest = httpRequest.getRemoteAddr();
		var userAgentDaRequest = httpRequest.getHeader("User-Agent");
		
		var cartaoEncontrado = repository.findByUuid(uuid)
			.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"O cartão não foi encontrado"));
		
		//caso o sistema legado retorne 200(CRIADO) cria o aviso, salva e retorna 200, caso contrario retorna 500
		try {
			cartaoClient.notificaAviso(cartaoEncontrado.getUuid(),request);
			
			cartaoEncontrado.addAvisoViagem(request.toModel(cartaoEncontrado,ipdaRequest,userAgentDaRequest));
			repository.save(cartaoEncontrado);
			metricas.contaAviso();
			return ResponseEntity.ok().build();
		}
		catch(FeignException expn) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Nao conseguimos processar sua requisição");
		}
	}
}