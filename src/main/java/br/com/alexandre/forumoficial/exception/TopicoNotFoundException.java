package br.com.alexandre.forumoficial.exception;

public class TopicoNotFoundException extends RuntimeException implements INotFoundException {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public TopicoNotFoundException(Long id) {
		this.message = String.format("Topico com id %d não encontrado", id);
	}

	public String getMessage() {
		return message;
	}
	

}
