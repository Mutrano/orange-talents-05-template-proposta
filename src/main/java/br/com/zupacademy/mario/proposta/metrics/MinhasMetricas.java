package br.com.zupacademy.mario.proposta.metrics;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Component
public class MinhasMetricas {

	private MeterRegistry meterRegistry;

	public MinhasMetricas(MeterRegistry meterRegistry) {
		this.meterRegistry = meterRegistry;
	}
	public void contadorDaMetrica(String metrica) {
	    Counter contador= this.meterRegistry.counter(metrica);
	    contador.increment();
	}
	
	public void contaPropostaElegivel() {
		contadorDaMetrica("proposta_elegivel");
	}
	public void contaPropostaNaoElegivel() {
		contadorDaMetrica("proposta_nao_elegivel");
	}
	public void contaBloqueioComSucesso() {
		contadorDaMetrica("bloqueio_sucesso");
	}
	public void contaBloqueioFalho() {
		contadorDaMetrica("bloqueio_falho");
	}
	public void contaAcompanhamentoProposta() {
		contadorDaMetrica("proposta_acompanhamento");
	}
	
	public void contaAviso() {
		contadorDaMetrica("aviso");
	}
	
	
}
