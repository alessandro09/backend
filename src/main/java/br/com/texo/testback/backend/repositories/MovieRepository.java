package br.com.texo.testback.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.texo.testback.backend.entities.Movie;

@Repository
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {
	String QUERY = ""
			+ "  from Movie"
			+ " where (?1 is null or year = ?1)"
			+ "     and (?2 is null or winner = ?2)";
	
	@Query(QUERY)
	List<Movie> findFiltered(Integer year, Boolean winner);
}
