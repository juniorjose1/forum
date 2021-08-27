package br.com.alexandre.forumoficial.exception;

public class ResourceNotFoundException extends RuntimeException implements INotFoundException {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public ResourceNotFoundException() {
		this.message = "Nenhum registro encontrado.";
	}

	public String getMessage() {
		return message;
	}
	
	

}
