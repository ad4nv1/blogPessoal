package com.example.postagem.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsuarioTest {
	
	private Usuario usuario;
	private Usuario usuarioNulo = new Usuario();
	@Autowired
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	
	Validator validator = factory.getValidator();
	
	@BeforeEach
	public void start() {

		usuario = new Usuario("João da Silva", "joao@email.com.br", "13465278");
		//usuario = new Usuario();
		//usuario.setNome("João da Silva");
		//usuario.setSenha("13465278");
		//usuario.setUsuario("joao@email.com.br");
	}

	@Test
	@DisplayName("✔ Valida Atributos Não Nulos")
	void testValidaAtributos() {
		
		Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuario);
		
		System.out.println(violacao.toString());
		
		assertTrue(violacao.isEmpty());
		
	}
	
	@Test
	@DisplayName("✖ Não Valida Atributos Nulos")
	void testNaoValidaAtributos() {
		
		Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuarioNulo);
		
		System.out.println(violacao.toString());
		
		assertFalse(violacao.isEmpty());
		
	}

}