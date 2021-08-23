package br.com.alexandre.forumoficial.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alexandre.forumoficial.dto.TopicoDetalhadoDto;
import br.com.alexandre.forumoficial.dto.TopicoDto;
import br.com.alexandre.forumoficial.modelo.Topico;
import br.com.alexandre.forumoficial.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	@GetMapping
	public ResponseEntity<List<TopicoDto>> listar(){
		List<Topico> topicos = topicoRepository.findAll();
		List<TopicoDto> topicosDto = TopicoDto.convertDto(topicos);
		
		return ResponseEntity.ok(topicosDto);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<TopicoDetalhadoDto> detalhar(@PathVariable Long id){
		Optional<Topico> topico = topicoRepository.findById(id);
		if(topico.isPresent()) {
			TopicoDetalhadoDto topicoDetalhado = TopicoDetalhadoDto.convertDto(topico.get());
			return ResponseEntity.ok(topicoDetalhado);
		}
		return ResponseEntity.notFound().build();
	}

}
