package br.com.zupacademy.mario.proposta.domain.Proposta;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "propostas" ,url = "${url_proposta}")
public interface PropostaClient {
	
	
	@RequestMapping(method= RequestMethod.POST,value = "/api/solicitacao")
	ResultadoAnalise solicitaAnalise(@RequestBody InformacaoProposta solicitacao);
	
}
