package br.com.alexandre.forumoficial.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String msg;
	
	public ResourceNotFoundException() {
	}
	
	public ResourceNotFoundException(String msg) {
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg;
	}

}
