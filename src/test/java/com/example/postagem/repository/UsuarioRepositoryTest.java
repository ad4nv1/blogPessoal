package com.example.postagem.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import com.example.postagem.model.Usuario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		
		//LocalDate data = LocalDate.parse("2000-07-22", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		Usuario usuario = new Usuario(0L, "João da Silva", "joao@email.com.br", "13465278");
		if (!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			usuarioRepository.save(usuario);
		usuario = new Usuario(0L, "Manuel da Silva", "manuel@email.com.br", "13465278");
		if (!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			usuarioRepository.save(usuario);
		usuario = new Usuario(0L, "Frederico da Silva", "frederico@email.com.br", "13465278");
		if (!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			usuarioRepository.save(usuario);
		usuario = new Usuario(0L, "Paulo Antunes", "paulo@email.com.br", "13465278");
		if (!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			usuarioRepository.save(usuario);
	}

	@Test
	@DisplayName("💾 Retorna o nome")
	public void deveNomeRetornaNome() {

		Optional <Usuario> usuario = usuarioRepository.findByNome("João da Silva");
		assertTrue(usuario.get().getNome().equals("João da Silva"));
	}

	@Test
	@DisplayName("💾 Retorna 3 usuarios")
	public void findAllByNomeContainingIgnoreCaseRetornaTresUsuarios() {

		List<Usuario> listaDeUsuarios = usuarioRepository
		.findAllByNomeContainingIgnoreCase("Silva");
		
		assertEquals(3, listaDeUsuarios.size());
	}

	@AfterAll
	public void end() {
		System.out.println("Teste Finalizado!");
	}
	
}