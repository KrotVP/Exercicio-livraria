package br.com.serratec.Livraria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.serratec.Livraria.exception.IdNotFoundException;
import br.com.serratec.Livraria.model.Livro;
import br.com.serratec.Livraria.repository.LivroRepositorio;

@Service
public class LivroService {

	@Autowired
	LivroRepositorio repositorio;
	
	public List<Livro> getAll(String parametro){
		if(parametro==null) {
			return repositorio.findAll();
		}
		Sort sort= Sort.by(parametro);
		return repositorio.findAll(sort);
	}
	public Livro getOneById(Integer id) throws IdNotFoundException {
		Optional<Livro> optional= repositorio.findById(id);
		if(optional.isEmpty()) {
			throw new IdNotFoundException();
		}
		return optional.get();
	}
	public void post(Livro livro) {
	repositorio.save(livro);
	}
	public void update(Integer id,Livro livro) throws IdNotFoundException {
	Optional<Livro> optional = repositorio.findById(id);
	if (optional.isEmpty()) {
		throw new IdNotFoundException();
	}
	Livro oldLivro = optional.get();
	if (livro.getId() != null) {
		oldLivro.setId(livro.getId());
	}
	if (livro.getTitulo() != null &&!livro.getTitulo().equals("")  ) {
		oldLivro.setTitulo(livro.getTitulo());
	}
	if(livro.getAutor()!= null && !livro.getAutor().equals("")) {
		oldLivro.setAutor(livro.getAutor());
	}
	if(livro.getTipo()!= null && !livro.getTipo().equals("")) {
		oldLivro.setTipo(livro.getTipo());
	}
	if(livro.getDataLancamento()!= null ) {
		oldLivro.setDataLancamento(livro.getDataLancamento());
	}
	repositorio.save(oldLivro);

}
	public void delete(Integer id) throws IdNotFoundException {
		Optional<Livro> optional = repositorio.findById(id);
		if (optional.isEmpty()) {
			throw new IdNotFoundException();
		}
		repositorio.deleteById(id);
	}
}
