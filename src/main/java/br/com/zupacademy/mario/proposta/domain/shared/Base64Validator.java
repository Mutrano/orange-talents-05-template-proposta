package br.com.zupacademy.mario.proposta.domain.shared;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class Base64Validator implements ConstraintValidator<Base64, String> {

	private String campo;



	@Override
	public void initialize(Base64 constraintAnnotation) {
		this.campo = constraintAnnotation.campo();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		try {
			java.util.Base64.getDecoder().decode(value);
			return true;
		}
		catch(IllegalArgumentException | NullPointerException expn) {
			return false;
		}
	}
}
