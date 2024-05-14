package br.org.serratec.catalogoDeMusicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.catalogoDeMusicas.model.Musica;

public interface MusicaRepository extends JpaRepository<Musica, Long>{
	

}
