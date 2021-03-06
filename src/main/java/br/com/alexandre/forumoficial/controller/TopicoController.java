package br.com.alexandre.forumoficial.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alexandre.forumoficial.controller.dto.TopicoDetalhadoDto;
import br.com.alexandre.forumoficial.controller.dto.TopicoDto;
import br.com.alexandre.forumoficial.controller.form.TopicoAtualizadoForm;
import br.com.alexandre.forumoficial.controller.form.TopicoForm;
import br.com.alexandre.forumoficial.modelo.Topico;
import br.com.alexandre.forumoficial.service.TopicoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
	
	@Autowired
	private TopicoService topicoService;
	
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
	            value = "Pagina a ser carregada", defaultValue = "0"),
	    @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
	            value = "Quantidade de registros", defaultValue = "5"),
	    @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
	            value = "Ordenacao dos registros(atributo,desc ou asc)")
	})
	@GetMapping
	@Cacheable("listaTopicos")
	public ResponseEntity<Page<TopicoDto>> findAll(String nomeCurso, 
			@PageableDefault(page = 0, size = 10, sort = "titulo", direction = Direction.ASC) @ApiIgnore Pageable pageResult){
		Page<TopicoDto> topicos = topicoService.findAll(nomeCurso, pageResult);
		return ResponseEntity.ok(topicos);
	}
	
	@GetMapping("/{id}")
	@Cacheable("topicoDetalhado")
	public ResponseEntity<TopicoDetalhadoDto> findById(@PathVariable Long id){
		TopicoDetalhadoDto topicoDetalhadoDto = topicoService.findById(id);
		
		return ResponseEntity.ok(topicoDetalhadoDto);
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value = {"listaTopicos"}, allEntries = true)
	public ResponseEntity<TopicoDetalhadoDto> save(@Valid @RequestBody TopicoForm topicoForm, UriComponentsBuilder uriBuilder){
		Topico topico = topicoService.save(topicoForm);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new TopicoDetalhadoDto(topico));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = {"listaTopicos", "topicoDetalhado"}, allEntries = true)
	public ResponseEntity<?> delete(@PathVariable Long id){
		topicoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = {"listaTopicos", "topicoDetalhado"}, allEntries = true)
	public ResponseEntity<?> update(@PathVariable Long id, 
			@RequestBody TopicoAtualizadoForm topicoAtualizadoForm){
		topicoService.update(id, topicoAtualizadoForm);
		return ResponseEntity.noContent().build();
	}
}
