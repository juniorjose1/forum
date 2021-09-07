package br.com.alexandre.forumoficial.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alexandre.forumoficial.modelo.StatusTopico;
import br.com.alexandre.forumoficial.modelo.Topico;

public class TopicoDetalhadoDto {

	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private StatusTopico status;
	private String nomeAutor;
	private String nomeCurso;
	private List<RespostaDto> respostas = new ArrayList<>();

	public TopicoDetalhadoDto(Topico topico) {
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
		this.status = topico.getStatus();
		this.nomeAutor = topico.getAutor().getNome();
		this.nomeCurso = topico.getCurso().getNome();
		this.respostas.addAll(topico.getRespostas().stream().map(RespostaDto::new).collect(Collectors.toList()));
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

	public List<RespostaDto> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<RespostaDto> respostas) {
		this.respostas = respostas;
	}

	public static TopicoDetalhadoDto convertDto(Topico topico) {
		return new TopicoDetalhadoDto(topico);
	}

}
