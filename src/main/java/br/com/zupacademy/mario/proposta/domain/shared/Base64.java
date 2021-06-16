package br.com.zupacademy.mario.proposta.domain.shared;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({FIELD,METHOD})
@Retention(RUNTIME)
@Constraint(validatedBy = {Base64Validator.class})
public @interface Base64 {

	String message() default "Valor não é um Base64 válido";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	String campo() ;
	
}

