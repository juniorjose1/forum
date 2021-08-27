package br.com.alexandre.forumoficial.exception;

public class CursoNotFoundException extends RuntimeException implements INotFoundException {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public CursoNotFoundException(String nome) {
		this.message = String.format("Curso '%s' não encontrado", nome);
	}

	public String getMessage() {
		return message;
	}
	
	

}
