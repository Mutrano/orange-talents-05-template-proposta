package br.com.zupacademy.mario.proposta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Component;

//baseado em https://stackoverflow.com/a/57141701

@Component
public class HealthCheck implements HealthIndicator {

	@Autowired
	JdbcTemplate template;

	@Override
	public Health health() {
		try {
			int errorCode = check(); // checa se o db esta conectado
			if (errorCode != 1) {
				return Health.down().build();
			}
			return Health.up().build();
		} catch (Exception ex) {
			return Health.down().build();
		}

	}

	private int check() {
		List<Object> results = template.query("select 1 from proposta", new SingleColumnRowMapper<>());
		return results.size();
	}
}