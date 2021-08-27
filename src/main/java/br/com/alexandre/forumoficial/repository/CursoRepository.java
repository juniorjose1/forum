package br.com.alexandre.forumoficial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alexandre.forumoficial.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
	
	public Curso findByNome(String nome);

}
