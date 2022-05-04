package br.com.texo.testback.backend.services;

import static br.com.texo.testback.backend.services.components.ExtremeComponent.Interval.GREATER;
import static br.com.texo.testback.backend.services.components.ExtremeComponent.Interval.LESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.texo.testback.backend.bean.ResponseBean;
import br.com.texo.testback.backend.services.components.ExtremeComponent;

@Service
public class ProducerService {
	
	@Autowired
	private ExtremeComponent extremeComponent;
	
	public ResponseBean getExtremes(int size) {
		var greater = extremeComponent.get(size, GREATER);
		var less = extremeComponent.get(size, LESS);
		
		return new ResponseBean(less, greater);
	}
}
