package br.org.serratec.catalogoDeMusicas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.catalogoDeMusicas.model.Genero;
import br.org.serratec.catalogoDeMusicas.model.Musica;

public interface MusicaRepository extends JpaRepository<Musica, Long>{
	
	
	
	List<Musica>findByArtistaLike(String artista);
	
	List<Musica> findByArtistaNotLike(String nome);
	
	List<Musica> findByGenero(Genero genero);
	
	
	

}
