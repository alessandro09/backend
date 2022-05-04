package br.com.texo.testback.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.texo.testback.backend.entities.Producer;
import br.com.texo.testback.backend.projections.IntervalBetweenAwards;

@Repository
public interface ProducerRepository extends PagingAndSortingRepository<Producer, Long> {
	String QUERY = "select *, gap \"interval\" from ("
			+ "select previousWin,"
			+ "       followingWin,"
			+ "       followingWin - previousWin gap,"
			+ "       producer.name producer"
			+ "  from (select year previousWin,"
			+ "               select min(year) from movie m2 where m2.id <> q.id and m2.id_producer = q.id_producer and m2.year > q.year followingWin,"
			+ "               id_producer"
			+ "            from movie q)"
			+ "   inner join producer on id_producer = producer.id"
			+ "  where followingWin is not null) q";
	
	String QUERY_COUNT = "select count(*) from (" + QUERY + ")";
	
	@Query(value = QUERY, countQuery = QUERY_COUNT, nativeQuery = true)
	Page<IntervalBetweenAwards> findIntervalAwards(Pageable pageable);
}
