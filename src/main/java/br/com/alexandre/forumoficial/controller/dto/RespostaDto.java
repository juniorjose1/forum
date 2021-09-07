package br.com.alexandre.forumoficial.controller.dto;

import java.time.LocalDateTime;

import br.com.alexandre.forumoficial.modelo.Resposta;
import br.com.alexandre.forumoficial.modelo.Usuario;

public class RespostaDto {

	private Long id;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private Usuario autor;
	private Boolean solucao;

	public RespostaDto(Resposta resposta) {
		this.id = resposta.getId();
		this.mensagem = resposta.getMensagem();
		this.dataCriacao = resposta.getDataCriacao();
		this.autor = resposta.getAutor();
		this.solucao = resposta.getSolucao();
	}

	public Long getId() {
		return id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public Usuario getAutor() {
		return autor;
	}

	public Boolean getSolucao() {
		return solucao;
	}

}
