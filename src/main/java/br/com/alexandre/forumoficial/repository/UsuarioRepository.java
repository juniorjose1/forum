package br.com.alexandre.forumoficial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alexandre.forumoficial.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findByEmail(String email);
	
}
