package br.com.zupacademy.mario.proposta.domain.Cartao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CartaoRepository extends JpaRepository<Cartao, Long>{
	@Transactional
	Cartao save(Cartao cartao);
}
