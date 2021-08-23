package br.com.alexandre.forumoficial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alexandre.forumoficial.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
	
}
