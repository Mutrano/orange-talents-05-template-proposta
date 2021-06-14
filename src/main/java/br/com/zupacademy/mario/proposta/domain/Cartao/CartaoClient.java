package br.com.zupacademy.mario.proposta.domain.Cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.zupacademy.mario.proposta.domain.Proposta.InformacaoProposta;

@FeignClient(name = "cartoes" ,url = "${url_cartoes}")
public interface CartaoClient {

	
	@PostMapping(value="/api/cartoes")
	CadastraCartaoResponse cadastraCartao(@RequestBody InformacaoProposta proposta	 );
}
