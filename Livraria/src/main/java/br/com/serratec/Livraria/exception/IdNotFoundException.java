package br.com.serratec.Livraria.exception;

public class IdNotFoundException extends Exception {

	@Override
	public String getMessage() {
		return "O id não foi encontrado";
	}

}
