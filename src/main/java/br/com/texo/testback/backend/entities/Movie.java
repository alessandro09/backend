package br.com.texo.testback.backend.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Movie {
	@Id
	private Long id;
	
	private Integer year;
	
	private String title;
	
	private Boolean winner;
	
	@OneToMany
	private List<ProducerMovie> producerMovie;
	
	@OneToMany
	private List<StudioMovie> studioMovie;

	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

	public Integer getYear() { return year; }

	public void setYear(Integer year) { this.year = year; }

	public String getTitle() { return title; }

	public void setTitle(String title) { this.title = title; }

	public Boolean getWinner() { return winner; }

	public void setWinner(Boolean winner) { this.winner = winner; }
	
	public List<ProducerMovie> getProducerMovie() { return producerMovie; }
	
	public void setProducerMovie(List<ProducerMovie> producerMovie) { this.producerMovie = producerMovie; }
	
	public List<StudioMovie> getStudioMovie() { return studioMovie; }
	
	public void setStudioMovie(List<StudioMovie> studioMovie) { this.studioMovie = studioMovie; }
}
