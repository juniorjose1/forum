package br.com.alexandre.forumoficial.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alexandre.forumoficial.exception.CursoNotFoundException;
import br.com.alexandre.forumoficial.modelo.Curso;
import br.com.alexandre.forumoficial.repository.CursoRepository;

@ExtendWith(MockitoExtension.class)
public class CursoServiceTest {
	
	@InjectMocks
	private CursoService cursoService;
	
	@Mock
	private CursoRepository cursoRepository;

	@Test
	@DisplayName("deveria retornar o curso ao buscar pelo seu nome")
	public void test1() {
		Curso curso = new Curso();
		curso.setNome("PHYTON");
		curso.setCategoria("Prog");
		
		Mockito.when(cursoRepository.findByNome("PHYTON")).thenReturn(curso);
		
		Curso cursoEncontrado = cursoService.findByNome("PHYTON");
		
		Assertions.assertThat(cursoEncontrado).isEqualTo(curso);
		
		Mockito.verify(cursoRepository, Mockito.times(1)).findByNome("PHYTON");
	}
	
	@Test
	@DisplayName("deveria lançar exceção caso não encontre um curso")
	public void test2() {
		Assertions.assertThatExceptionOfType(CursoNotFoundException.class)
								.isThrownBy(() -> cursoService.findByNome("FLUTTER"));
	}

}
