package br.com.zupacademy.mario.proposta.domain.Cartao;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class BiometriaController {

	private CartaoRepository repository;

	public BiometriaController(CartaoRepository repository) {
		this.repository = repository;
	}

	@PostMapping("/Cartoes/{uuid}/Biometrias")
	public ResponseEntity<Void> cadastraBiometria(@Valid @RequestBody CadastraBiometriaRequest request,
			@PathVariable String uuid, UriComponentsBuilder builder) {

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
