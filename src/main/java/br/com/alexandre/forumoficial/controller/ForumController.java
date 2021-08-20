package br.com.alexandre.forumoficial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alexandre.forumoficial.dto.TopicoDto;
import br.com.alura.forum.repository.TopicoRepository;

@Controller
@RequestMapping(name = "/topicos")
public class ForumController {
	
	@Autowired
	private TopicoRepository topicoRepository;
	

}
