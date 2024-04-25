package com.todo.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -8006818509264757117L;

	public ResourceNotFoundException(String message) {
        super(message);
    }

}
