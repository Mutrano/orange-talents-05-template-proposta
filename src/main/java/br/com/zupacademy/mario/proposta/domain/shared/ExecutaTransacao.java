package br.com.zupacademy.mario.proposta.domain.shared;

import java.util.function.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ExecutaTransacao {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public <T> T salvaEComita(T objeto) {
		manager.persist(objeto);
		return objeto;
	}

	@Transactional
	public <T> T atualizaEComita(T objeto) {
		return manager.merge(objeto);
    }
    
    @Transactional
    public <T> T executa(Supplier<T> supplier){
        return supplier.get();
    }
}

