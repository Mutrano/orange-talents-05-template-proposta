package br.com.zupacademy.mario.proposta.domain.Proposta;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long>{
	
	@Transactional(readOnly = true)
	Optional<List<Proposta>> findByDocumento(String documento);
	
	@Transactional
	Proposta save(Proposta proposta);
	
	@Transactional(readOnly=true)
	Optional<Proposta> findByUuid(UUID uuid);

}
