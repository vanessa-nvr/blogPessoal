package com.blogpessoal.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.blogpessoal.blogpessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
    
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start(){
		usuarioRepository.deleteAll();
		usuarioRepository.save(new Usuario(0L, "Cersei Santos", "cersei@email.com.br", "13465278", "https://i.imgur.com/wYTCtRu.jpg"));
		usuarioRepository.save(new Usuario(0L, "Yakult Santos", "yakult@email.com.br", "13465278", "https://i.imgur.com/ZmcYXQK.jpg"));
		usuarioRepository.save(new Usuario(0L, "Pumba Silva", "pumba@email.com.br", "13465278", "https://i.imgur.com/sjpa0Gg.jpg"));
        usuarioRepository.save(new Usuario(0L, "Margo Silva Santos", "margo@email.com.br", "13465278", "https://i.imgur.com/4clqUdj.jpg"));

	}

	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUmUsuario() {
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("cersei@email.com.br");
		assertTrue(usuario.get().getUsuario().equals("cersei@email.com.br"));
	}

	@Test
	@DisplayName("Retorna 3 usuarios")
	public void deveRetornarTresUsuarios() {
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Santos");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Cersei Santos"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Yakult Santos"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Margo Silva Santos"));
		
	}

	@AfterAll
	public void end() {
		usuarioRepository.deleteAll();
	}
	
}
