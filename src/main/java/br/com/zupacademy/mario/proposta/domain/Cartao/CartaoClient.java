package br.com.zupacademy.mario.proposta.domain.Cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.zupacademy.mario.proposta.domain.Cartao.dto.AvisoViagemInfo;
import br.com.zupacademy.mario.proposta.domain.Cartao.dto.CadastraCartaoResponse;
import br.com.zupacademy.mario.proposta.domain.Cartao.dto.ResultadoAvisoViagem;
import br.com.zupacademy.mario.proposta.domain.Cartao.dto.ResultadoBloqueio;
import br.com.zupacademy.mario.proposta.domain.Cartao.dto.SolicitacaoBloqueio;
import br.com.zupacademy.mario.proposta.domain.Proposta.InformacaoProposta;

@FeignClient(name = "cartoes" ,url = "${url_cartoes}")
public interface CartaoClient {

	
	@PostMapping(value="/api/cartoes")
	CadastraCartaoResponse cadastraCartao(@RequestBody InformacaoProposta proposta	 );
	
	@PostMapping(value="/api/cartoes/{uuid}/bloqueios")
	ResultadoBloqueio notificaBloqueio(@PathVariable String uuid,@RequestBody SolicitacaoBloqueio solicitacaoBloqueio);

	@PostMapping(value="/api/cartoes/{uuid}/avisos")
	ResultadoAvisoViagem notificaAviso(@PathVariable String uuid, @RequestBody AvisoViagemInfo aviso);
}