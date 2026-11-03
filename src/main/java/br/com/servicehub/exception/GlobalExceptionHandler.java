package br.com.servicehub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.servicehub.company.DuplicateCompanyDocumentException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(DuplicateCompanyDocumentException.class)
	public ResponseEntity<ApiError> handlerDuplicateCompany(DuplicateCompanyDocumentException exception) {
		
		ApiError error = new ApiError(HttpStatus.CONFLICT.value(), "Conflict", exception.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgument(IllegalArgumentException exception) {

        ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), "Bad Request", exception.getMessage());

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException exception) {

        String message = exception.getBindingResult().getFieldErrors().stream().findFirst().map(fieldError -> fieldError.getDefaultMessage()).orElse("Dados inválidos.");

        ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), "Bad Request", message);

        return ResponseEntity.badRequest().body(error);
    }
}
