package br.com.zupacademy.mario.proposta.domain.shared;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
@Converter
public class Crypto implements AttributeConverter<String, String>{
	
	@Value("${proposta.salt}")
	private   String salt;
	
	@Value("${proposta.password}")
	private  String password;
	

	@Override
	public String convertToDatabaseColumn(String attribute) {
		return Encryptors.queryableText(password,salt).encrypt(attribute);
	}


	@Override
	public String convertToEntityAttribute(String dbData) {
		return Encryptors.queryableText(password,salt).decrypt(dbData);
	}
}
