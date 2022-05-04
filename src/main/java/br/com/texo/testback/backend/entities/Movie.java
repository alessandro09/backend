package br.com.texo.testback.backend.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Movie {
	@Id
	private Long id;
	
	private Integer year;
	
	private String title;
	
	@ManyToOne
	@JoinColumn(name = "id_studio")
	private Studio studio;
	
	@ManyToOne
	@JoinColumn(name = "id_producer")
	private Producer producer;
	
	private Boolean winner;

	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

	public Integer getYear() { return year; }

	public void setYear(Integer year) { this.year = year; }

	public String getTitle() { return title; }

	public void setTitle(String title) { this.title = title; }

	public Studio getStudio() { return studio; }

	public void setStudio(Studio studio) { this.studio = studio; }

	public Producer getProducer() { return producer; }

	public void setProducer(Producer producer) { this.producer = producer; }

	public Boolean getWinner() { return winner; }

	public void setWinner(Boolean winner) { this.winner = winner; }
}
