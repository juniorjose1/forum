package br.com.alexandre.forumoficial.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alexandre.forumoficial.controller.dto.TopicoDetalhadoDto;
import br.com.alexandre.forumoficial.controller.dto.TopicoDto;
import br.com.alexandre.forumoficial.controller.form.TopicoAtualizadoForm;
import br.com.alexandre.forumoficial.controller.form.TopicoForm;
import br.com.alexandre.forumoficial.exception.ResourceNotFoundException;
import br.com.alexandre.forumoficial.exception.TopicoNotFoundException;
import br.com.alexandre.forumoficial.modelo.Curso;
import br.com.alexandre.forumoficial.modelo.Topico;
import br.com.alexandre.forumoficial.repository.TopicoRepository;

@Service
public class TopicoService {

	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoService cursoService;
	
	public Page<TopicoDto> findAll(String nomeCurso, Pageable pageResult){
		if(nomeCurso == null) {
			Page<Topico> topicos = topicoRepository.findAll(pageResult);
			return convertTopicoDto(topicos);
		}
		Page<Topico> topicosCurso = topicoRepository.findByCursoNome(nomeCurso, pageResult);
		return convertTopicoDto(topicosCurso);
	}
	
	public TopicoDetalhadoDto findById(Long id){
		Optional<Topico> topico = topicoRepository.findById(id);
		Topico topicoEncontrado = topico.orElseThrow(() -> new TopicoNotFoundException(id)); 
		return new TopicoDetalhadoDto(topicoEncontrado);
	}
	
	public Topico save(TopicoForm topicoForm) {
		return topicoRepository.save(convertTopico(topicoForm));
	}
	
	public void deleteById(Long id) {
		Optional<Topico> topico = topicoRepository.findById(id);
		topico.orElseThrow(() -> new TopicoNotFoundException(id));
		topicoRepository.deleteById(id);
	}
	
	public void update(Long id, TopicoAtualizadoForm topicoAtualizadoForm) {
		Optional<Topico> topico = topicoRepository.findById(id);
		Topico topicoEncontrado = topico.orElseThrow(() -> new TopicoNotFoundException(id));
		topicoEncontrado.setMensagem(topicoAtualizadoForm.getMensagem());
		topicoEncontrado.setTitulo(topicoAtualizadoForm.getTitulo());
	}
	
	private Page<TopicoDto> convertTopicoDto(Page<Topico> topicos) {
		if(!topicos.isEmpty()) {
			return topicos.map(TopicoDto::new);
		}
		throw new ResourceNotFoundException();
	}
	
	private Topico convertTopico(TopicoForm topicoForm) {
		Curso curso = cursoService.findByNome(topicoForm.getNomeCurso());
		return new Topico(topicoForm.getTitulo(), topicoForm.getMensagem(), curso);
	}

	
	

}
