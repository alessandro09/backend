package br.com.texo.testback.backend.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class StudioMovie {
	@Id
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_studio")
	private Studio studio;
	
	@ManyToOne
	@JoinColumn(name = "id_movie")
	private Movie movie;

	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

	public Studio getStudio() { return studio; }

	public void setStudio(Studio studio) { this.studio = studio; }

	public Movie getMovie() { return movie; }

	public void setMovie(Movie movie) { this.movie = movie; }
}
