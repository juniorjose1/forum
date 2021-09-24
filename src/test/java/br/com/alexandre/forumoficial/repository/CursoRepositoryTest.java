package br.com.alexandre.forumoficial.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.alexandre.forumoficial.modelo.Curso;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CursoRepositoryTest {
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@Test
	@DisplayName("deveria cadastrar um curso e buscá-lo ao pesquisar pelo seu nome")
	public void test() {
		Curso curso = new Curso();
		curso.setNome("JAVASCRIPT");
		curso.setCategoria("Programação");
		cursoRepository.save(curso);
		Curso cursoEncontrado = cursoRepository.findByNome("JAVASCRIPT");
		Assertions.assertThat(cursoEncontrado).isNotNull();
		Assertions.assertThat(cursoEncontrado).isEqualTo(curso);
	}
	
	@Test
	@DisplayName("não deveria carregar um curso que não esteja cadastrado")
	public void test2() {
		String curso = "PHYTON";
		Curso cursoEncontrado = cursoRepository.findByNome(curso);
		Assertions.assertThat(cursoEncontrado).isNull();
	}

}
