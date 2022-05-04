package br.com.texo.testback.backend.services.components;

import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Sort.by;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import br.com.texo.testback.backend.bean.IntervalBetweenAwardsBean;
import br.com.texo.testback.backend.repositories.ProducerRepository;

@Component
public class ExtremeComponent {
	
	@Autowired
	private ProducerRepository producerRepository;
	
	public List<IntervalBetweenAwardsBean> get(int limit, Interval interval) {
		var config = of(0, 2, by(interval.getDirection(), "gap"));
		
		return producerRepository.findIntervalAwards(config).getContent()
				.stream()
				.map(IntervalBetweenAwardsBean::new)
				.collect(Collectors.toList());
	}
	
	public static enum Interval {
		GREATER(DESC), LESS(ASC);
		
		private Direction direction;
		
		Interval(Direction direction) { this.direction = direction; }
		
		public Direction getDirection() { return direction; }
	}
}
