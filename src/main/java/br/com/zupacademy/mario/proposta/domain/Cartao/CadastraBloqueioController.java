package br.com.zupacademy.mario.proposta.domain.Cartao;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.mario.proposta.domain.shared.ExecutaTransacao;

@RestController
public class CadastraBloqueioController {
	
	private EntityManager em;
	private ExecutaTransacao executaTransacao;

	public CadastraBloqueioController(EntityManager em, ExecutaTransacao executaTransacao) {
		this.em = em;
		this.executaTransacao = executaTransacao;
	}


	@PostMapping("/Cartoes/{uuid}/Bloqueios")
	public ResponseEntity<Void> cadastraBloqueio(@PathVariable String uuid, HttpServletRequest request){
		var ipDaRequest = request.getRemoteAddr();
		var userAgentDaRequest = request.getHeader("User-Agent");
		
		//checar se o cartao ja existe, caso exista retorna 404
		var query = em.createQuery("SELECT c from Cartao c WHERE c.uuid=:pUuid")
				.setParameter("pUuid", uuid);
		
		var result =  (List<Cartao>) query.getResultList();
		
		if (result.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O cartão informado não existe");
		}
		
		//checar se o cartao ja esta bloqueado, caso esteja retornar 422
		query = em.createQuery("SELECT c,b FROM Bloqueio b "
				+ "RIGHT JOIN b.cartao c "
				+ "WHERE c.uuid=:pUuid AND b.ativo=true")
				.setParameter("pUuid", uuid);
		
		if (!query.getResultList().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "O cartão informado ja esta bloqueado");
		}

		var cartaoEncontrado = result.get(0);
		var bloqueio = new Bloqueio(UUID.randomUUID(),Instant.now(), "Proposta", true, cartaoEncontrado, ipDaRequest, userAgentDaRequest);
		
		executaTransacao.salvaEComita(bloqueio);

		return ResponseEntity.ok().build();
	}
}