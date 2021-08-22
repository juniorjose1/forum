package br.com.alexandre.forumoficial.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alexandre.forumoficial.modelo.StatusTopico;
import br.com.alexandre.forumoficial.modelo.Topico;

public class TopicoDto {

	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private StatusTopico status;
	private String nomeAutor;
	private String nomeCurso;

	public TopicoDto(Topico topico) {
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
		this.status = topico.getStatus();
		this.nomeAutor = topico.getAutor().getNome();
		this.nomeCurso = topico.getCurso().getNome();
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

	public StatusTopico getStatus() {
		return status;
	}

	public void setStatus(StatusTopico status) {
		this.status = status;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public static List<TopicoDto> convertDto(List<Topico> topicos) {
		return topicos.stream().map(TopicoDto::new).collect(Collectors.toList());
	}

	

}
