package br.org.serratec.catalogoDeMusicas.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.catalogoDeMusicas.dto.MusicasDto;
import br.org.serratec.catalogoDeMusicas.service.MusicaService;
import jakarta.validation.Valid;

@Controller
@RestController
@RequestMapping("/musicas")
public class MusicasController {
	
	@Autowired
	private MusicaService servico;
	
	@GetMapping
    public ResponseEntity<List<MusicasDto>> listar(){
        return ResponseEntity.ok(servico.listarTodos());
    
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<MusicasDto> buscar(@PathVariable Long id) {
        Optional<MusicasDto> musica = servico.listarPorId(id);
        if (musica.isPresent()) {
            return ResponseEntity.ok(musica.get());
        }
        return ResponseEntity.notFound().build();
    }
	
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MusicasDto inserir(@Valid @RequestBody MusicasDto musica) {
        return servico.salvarMusica(musica);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<MusicasDto> atualizar(@PathVariable Long id, @RequestBody MusicasDto musicaAlterada) {
        Optional<MusicasDto> musica = servico.atualizarMusica(id, musicaAlterada);

        if (musica.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(musica.get());
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if(!servico.excluirMusica(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
	
	@GetMapping("/artista")
	public ResponseEntity<List<MusicasDto>> buscarArtista(@RequestBody String artista ) {
		
	    return ResponseEntity.ok(servico.obterPorArtista(artista));
	    }
	
	@GetMapping("/artistaMenos")
    public ResponseEntity<List<MusicasDto>> buscarArtistaMenos(@RequestBody String nome){
        return ResponseEntity.ok(servico.obterArtistasMenos(nome));
    }
	
	@GetMapping("/genero")
    public ResponseEntity<List<MusicasDto>> obterPorNome(@RequestBody String nome){
        return ResponseEntity.ok(servico.findByGenero(nome));
		
	}
	
	@GetMapping("/ano")
    public ResponseEntity<List<MusicasDto>> obterMusicaEntre(@RequestBody EntreAnosDTO anoDTO){
        int ano1 = anoDTO.ano1;
        int ano2 = anoDTO.ano2;
        System.out.println("Chegou aqui");
        
        return ResponseEntity.ok(servico.obterMusicaEntre(ano1, ano2));
    }
	
	@GetMapping("/buscarPorTitulo")
    public ResponseEntity<List<MusicasDto>> buscarPorTitulo(@RequestParam String titulo) {
        List<MusicasDto> musicas = servico.findByTituloContainingIgnoreCase(titulo);
        if (musicas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(musicas);
    }
	
}




