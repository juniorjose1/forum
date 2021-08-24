package br.com.alexandre.forumoficial.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alexandre.forumoficial.dto.TopicoDetalhadoDto;
import br.com.alexandre.forumoficial.dto.TopicoDto;
import br.com.alexandre.forumoficial.exception.ResourceNotFoundException;
import br.com.alexandre.forumoficial.modelo.Topico;
import br.com.alexandre.forumoficial.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	@GetMapping
	public ResponseEntity<Page<TopicoDto>> listar(String nomeCurso, Pageable pageResult){
		try {
			if(nomeCurso == null) {
				Page<Topico> topicos = topicoRepository.findAll(pageResult);
				return ResponseEntity.ok(TopicoDto.convertDto(topicos));
			}
			Page<Topico> topicosCurso = topicoRepository.findByCursoNome(nomeCurso, pageResult);
			return ResponseEntity.ok(TopicoDto.convertDto(topicosCurso));
		} catch(ResourceNotFoundException ex) {
			return ResponseEntity.notFound().build();
		}
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
