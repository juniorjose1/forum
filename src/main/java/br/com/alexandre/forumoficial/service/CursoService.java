package br.com.alexandre.forumoficial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alexandre.forumoficial.exception.CursoNotFoundException;
import br.com.alexandre.forumoficial.modelo.Curso;
import br.com.alexandre.forumoficial.repository.CursoRepository;

@Service
public class CursoService {
	
	@Autowired
	private CursoRepository cursoRepository;
	
	public Curso findByNome(String nomeCurso) {
		Curso curso = cursoRepository.findByNome(nomeCurso);
		if(curso != null) {
			return curso;
		}
		throw new CursoNotFoundException(nomeCurso);
	}

}
