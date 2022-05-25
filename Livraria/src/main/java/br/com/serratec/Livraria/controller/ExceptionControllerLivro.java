package br.com.serratec.Livraria.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.serratec.Livraria.exception.IdNotFoundException;

@RestControllerAdvice
public class ExceptionControllerLivro {

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<?> IdNotFoundException(IdNotFoundException e){
		HttpHeaders headers = new HttpHeaders();
		headers.add("Id-Error", e.getMessage());
		headers.add("Error-Code", "ID-NOT-FOUND");
		return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
	}
}
