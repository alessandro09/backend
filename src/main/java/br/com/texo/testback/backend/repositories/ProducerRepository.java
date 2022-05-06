package br.com.texo.testback.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.texo.testback.backend.bean.IntervalBetweenAwardsBean;
import br.com.texo.testback.backend.entities.Producer;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {
	String QUERY = ""
			+ "SELECT new br.com.texo.testback.backend.bean.IntervalBetweenAwardsBean("
			+ "       MIN(m.year),"
			+ "       MAX(m.year),"
			+ "       MAX(m.year) - MIN(m.year),"
			+ "       p.name)"
			+ "  FROM Movie m"
			+ " INNER JOIN m.producerMovie as pm"
			+ " INNER JOIN pm.producer as p"
			+ " WHERE m.winner IS TRUE"
			+ " GROUP BY p.id"
			+ " ORDER BY MAX(m.year) - MIN(m.year)";
	
	@Query(value = QUERY)
	List<IntervalBetweenAwardsBean> findIntervalAwards();
}
