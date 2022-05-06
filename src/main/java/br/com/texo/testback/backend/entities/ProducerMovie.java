package br.com.texo.testback.backend.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProducerMovie {
	@Id
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_producer")
	private Producer producer;
	
	@ManyToOne
	@JoinColumn(name = "id_movie")
	private Movie movie;

	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

	public Producer getProducer() { return producer; }
	
	public void setProducer(Producer producer) { this.producer = producer; }

	public Movie getMovie() { return movie; }

	public void setMovie(Movie movie) { this.movie = movie; }
}
