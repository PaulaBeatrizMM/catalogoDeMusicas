package br.org.serratec.catalogoDeMusicas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.catalogoDeMusicas.dto.MusicasDto;
import br.org.serratec.catalogoDeMusicas.model.Musica;
import br.org.serratec.catalogoDeMusicas.repository.MusicaRepository;

@Service
public class MusicaService {
	@Autowired
	private MusicaRepository repositorio;
	
	public List<MusicasDto> listarTodos() {
		return repositorio.findAll().stream()
				.map(c -> new MusicasDto(c.getId(), c.getTitulo(), c.getArtista(), c.getGenero(), c.getAnoLancamento()))
				.toList();
		
	}
	
	public Optional<MusicasDto> listarPorId(Long id) {
		Optional<Musica> musica = repositorio.findById(id);
		if (musica.isPresent()) {
			return Optional.of(musica.get().toDto());
		} 
		return Optional.empty();
	}
	
	public MusicasDto salvarMusica(MusicasDto musica) {
        Musica musicaEntity = repositorio.save(musica.toEntity());
        return musicaEntity.toDto();
    }
	
	public Optional<MusicasDto> atualizarMusica(Long id, MusicasDto musica) {
		Musica entMusica = musica.toEntity();
		if (repositorio.existsById(id)) {
			entMusica.setId(id);
			repositorio.save(entMusica);
			return Optional.of(entMusica.toDto());
		} 
		return Optional.empty();
	}
	
	public boolean excluirMusica(Long id) {
        if(!repositorio.existsById(id)){
            return false;
        }

        repositorio.deleteById(id);
        return true;
    }

}
