package br.com.zupacademy.mario.proposta.domain.Cartao;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.mario.proposta.domain.Cartao.dto.CadastraBiometriaRequest;
import io.opentracing.Tracer;

@RestController
public class BiometriaController {

	private CartaoRepository repository;
	private Tracer tracer;

	public BiometriaController(CartaoRepository repository, Tracer tracer) {
		this.repository = repository;
		this.tracer = tracer;
	}


	@PostMapping("/Cartoes/{uuid}/Biometrias")
	public ResponseEntity<Void> cadastraBiometria(@Valid @RequestBody CadastraBiometriaRequest request,
			@PathVariable String uuid, UriComponentsBuilder builder) {
		
		//propaga span do Jaeger
				var activeSpan = tracer.activeSpan();
				var email = activeSpan.getBaggageItem("user.email");
				if(Objects.nonNull(email)) {
					activeSpan.setBaggageItem("user.email", email);
				}
		
		var cartaoEncontrado = repository.findByUuid(uuid).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "O cartão informado não foi encontrado"));

		var biometria = new Biometria(request.getBiometria(), LocalDate.now(), cartaoEncontrado);

		cartaoEncontrado.addBiometria(biometria);

		repository.save(cartaoEncontrado);

		var uri = builder.fromPath("/Cartoes/{uuid}/Biometrias/{uuidBiometria}")
				.buildAndExpand(uuid, biometria.getUuid()).toUri();

		return ResponseEntity.created(uri).build();
	}

}
