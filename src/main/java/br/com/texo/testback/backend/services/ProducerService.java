package br.com.texo.testback.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.texo.testback.backend.bean.ResponseBean;
import br.com.texo.testback.backend.repositories.ProducerRepository;

@Service
public class ProducerService {
	
	@Autowired
	private ProducerRepository producerRepository;
	
	public ResponseBean getExtremes(int size) {
		var intervals = producerRepository.findIntervalAwards();
		
		int findQtd = intervals.size();
		
		if (findQtd < size) size = findQtd; 
		
		var greater = intervals.subList(0, size);
		var less = intervals.subList(findQtd - size, findQtd);
		
		return new ResponseBean(less, greater);
	}
}
