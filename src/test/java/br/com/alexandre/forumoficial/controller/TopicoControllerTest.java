package br.com.alexandre.forumoficial.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.alexandre.forumoficial.controller.dto.TopicoDetalhadoDto;
import br.com.alexandre.forumoficial.modelo.Curso;
import br.com.alexandre.forumoficial.modelo.Topico;
import br.com.alexandre.forumoficial.modelo.Usuario;
import br.com.alexandre.forumoficial.repository.TopicoRepository;
import br.com.alexandre.forumoficial.service.TopicoService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TopicoControllerTest {
	
	@MockBean
	private TopicoService topicoService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private TopicoRepository topicoRepository;

	@Test
	@DisplayName("deveria devolver status code 200 e trazer um tópico por id")
	public void testGetTopicoId() throws Exception {
		Curso curso = new Curso();
		curso.setNome("HTML");
		curso.setCategoria("Prog");
		
		Usuario usuario = new Usuario();
		usuario.setNome("test-user");
		usuario.setEmail("user@email.com");
		usuario.setSenha("123456");
		
		UsernamePasswordAuthenticationToken user = 
				new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(user);
		
		Topico topico = new Topico("Titulo Tópico", "Msg tópico", curso);
		
		Mockito.when(topicoService.findById(1L)).thenReturn(new TopicoDetalhadoDto(topico));
		
		mockMvc
			.perform(MockMvcRequestBuilders
					.get("/topicos/1"))
			.andExpect(MockMvcResultMatchers
					.status().is(200))
			.andExpect(MockMvcResultMatchers
					.jsonPath("titulo", Matchers.is("Titulo Tópico")));
		
	}

}
