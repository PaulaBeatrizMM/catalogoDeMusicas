package br.org.serratec.catalogoDeMusicas.model;

import br.org.serratec.catalogoDeMusicas.dto.MusicasDto;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "catalogo")
public class Musica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	private String titulo;
	private String artista;
	@Enumerated(EnumType.STRING)
	private Genero genero;
	private String anoLancamento;
	
	public Musica () {
		
	}
	
	
	public Musica(Long id, String titulo, String artista, Genero genero, String anoLancamento) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.artista = artista;
		this.genero = genero;
		this.anoLancamento = anoLancamento;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getArtista() {
		return artista;
	}
	public void setArtista(String artista) {
		this.artista = artista;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	public String getAnoLancamento() {
		return anoLancamento;
	}
	public void setAnoLancamento(String anoLancamento) {
		this.anoLancamento = anoLancamento;
	}
	

	public MusicasDto toDto() {
        return new MusicasDto(this.id, this.titulo, this.artista, this.genero,
                this.anoLancamento);
    }
}
