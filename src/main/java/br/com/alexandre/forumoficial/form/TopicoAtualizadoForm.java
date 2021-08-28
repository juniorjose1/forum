package br.com.alexandre.forumoficial.form;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class TopicoAtualizadoForm {
	
	@NotEmpty
	@Length(min = 5)
	private String titulo;
	
	@NotEmpty
	@Length(min = 10)
	private String mensagem;
	
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
	
	

}
