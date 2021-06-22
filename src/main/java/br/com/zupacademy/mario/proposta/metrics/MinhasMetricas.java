package br.com.zupacademy.mario.proposta.metrics;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import br.com.zupacademy.mario.proposta.domain.Cartao.EmissorCarteira;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;

@Component
public class MinhasMetricas {

	private MeterRegistry meterRegistry;

	public MinhasMetricas(MeterRegistry meterRegistry) {
		this.meterRegistry = meterRegistry;
	}

	public void contadorDaMetrica(String metrica) {
		Counter contador = this.meterRegistry.counter(metrica);
		contador.increment();
	}
	public void contadorDaMetrica(String metrica,Collection<Tag> tags) {
		Counter contador = this.meterRegistry.counter(metrica, tags);
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

	public void contaCarteiraDigitalSucesso(EmissorCarteira emissor) {
		Collection<Tag> tags = new ArrayList<>();
		tags.add(Tag.of("emissor", emissor.toString()));
		contadorDaMetrica("carteira_sucesso", tags);
	}
	public void contaCarteiraDigitalFalha(EmissorCarteira emissor) {
		Collection<Tag> tags = new ArrayList<>();
		tags.add(Tag.of("emissor", emissor.toString()));
		contadorDaMetrica("carteira_falha", tags);
	}

}
