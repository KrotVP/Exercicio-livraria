package br.com.serratec.Livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.Livraria.model.Livro;

public interface LivroRepositorio extends JpaRepository<Livro,Integer> {
	
}
