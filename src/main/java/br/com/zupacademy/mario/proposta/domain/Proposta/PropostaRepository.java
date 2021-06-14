package br.com.zupacademy.mario.proposta.domain.Proposta;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.zupacademy.mario.proposta.domain.Cartao.Cartao;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long>{
	
	@Transactional(readOnly = true)
	Optional<List<Proposta>> findByDocumento(String documento);
	
	@Transactional
	Proposta save(Proposta proposta);
	
	@Transactional
	List<Proposta> findByCartaoEquals(String valorDoWhere);
}
