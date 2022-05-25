package br.com.serratec.Livraria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.Livraria.exception.IdNotFoundException;
import br.com.serratec.Livraria.model.Livro;
import br.com.serratec.Livraria.service.LivroService;

@RestController
@RequestMapping("/livro")
public class LivroController {
	
	@Autowired
	LivroService service;

	@GetMapping
	public ResponseEntity<List<Livro>> getAll(@RequestParam(required=false) String parametro){
		HttpHeaders headers = new HttpHeaders();
		headers.add("Livro-List", "Segue a lista de livros");
		return new ResponseEntity<List<Livro>>(service.getAll(parametro) ,headers,HttpStatus.valueOf(202));
	}
	
	@GetMapping("/{id}")
	ResponseEntity<Livro> getOneById(@PathVariable Integer id) throws IdNotFoundException{
		HttpHeaders headers = new HttpHeaders();
		headers.add("Livro-By-Id", "Segue o livro escolhido");
		return new ResponseEntity<Livro>(service.getOneById(id),headers,HttpStatus.valueOf(200));
	}
	@PostMapping
	ResponseEntity <Livro> post(@RequestBody Livro livro){
		service.post(livro);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Livro-Post", "Livro Adicionado com sucesso!");
		return new ResponseEntity<Livro>(headers,HttpStatus.valueOf(201));
	}
	@PutMapping("/{id}")
	ResponseEntity<?> update(@RequestBody Livro livro,@PathVariable Integer id) throws IdNotFoundException{
		service.update(id,livro);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Livro-Updated", "Livro de id "+id+" modificado com sucesso");
		return new ResponseEntity<Livro>(headers,HttpStatus.valueOf(202));
	}
	@DeleteMapping("/{id}")
	ResponseEntity<?> delete(@PathVariable Integer id) throws IdNotFoundException{
		service.delete(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Livro-Deleted", "Livro de id "+id+" Deletado com sucesso");
		return new ResponseEntity<Livro>(headers,HttpStatus.OK);
	}
}
