package br.com.zupacademy.mario.proposta.domain.Proposta;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long>{

	Optional<List<Proposta>> findByDocumento(String documento);
}
