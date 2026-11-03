package br.com.servicehub.company;

public class DuplicateCompanyDocumentException extends RuntimeException{
	
	public DuplicateCompanyDocumentException(String message) {
		super(message);
	}
}
