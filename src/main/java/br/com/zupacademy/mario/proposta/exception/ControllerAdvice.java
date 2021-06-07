package br.com.zupacademy.mario.proposta.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErroDeValidacao> erroDeValidacao(MethodArgumentNotValidException expn, HttpServletRequest request) {
		final Integer status = HttpStatus.BAD_REQUEST.value();
		final String erro = "Erro de validação";
		final String mensagem = "Tente novamente com outros valores";
		final String caminho = request.getRequestURI();

		var erroDeValidacao = new ErroDeValidacao(status, erro, mensagem, caminho);

		expn.getBindingResult().getFieldErrors().stream()
			.forEach(err -> {
				String campo = err.getField();
				String mensagemPadrao = err.getDefaultMessage();
				erroDeValidacao.addErro(new CampoMensagem(campo, mensagemPadrao));
		});

		return ResponseEntity.badRequest().body(erroDeValidacao);
	}
}