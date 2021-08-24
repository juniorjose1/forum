package br.com.alexandre.forumoficial.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.alexandre.forumoficial.exception.ResourceNotFoundException;
import br.com.alexandre.forumoficial.modelo.Topico;

public class TopicoDto {

	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;

	public TopicoDto(Topico topico) {
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public static Page<TopicoDto> convertDto(Page<Topico> topicos) {
		if(!topicos.isEmpty()) {
			return topicos.map(TopicoDto::new);
		}
		throw new ResourceNotFoundException("Recurso não encontrado");
	}

	

}
