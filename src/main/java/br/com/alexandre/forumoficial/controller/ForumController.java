package br.com.alexandre.forumoficial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alexandre.forumoficial.dto.TopicoDto;
import br.com.alexandre.forumoficial.modelo.Topico;
import br.com.alexandre.forumoficial.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class ForumController {
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	@GetMapping
	public ResponseEntity<List<TopicoDto>> listar(){
		List<Topico> topicos = topicoRepository.findAll();
		List<TopicoDto> topicosDto = TopicoDto.convertDto(topicos);
		
		return ResponseEntity.ok(topicosDto);
	}
	

}
