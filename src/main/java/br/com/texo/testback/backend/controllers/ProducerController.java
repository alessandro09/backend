package br.com.texo.testback.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.texo.testback.backend.bean.ParametersBean;
import br.com.texo.testback.backend.bean.ResponseBean;
import br.com.texo.testback.backend.services.ProducerService;

@RestController
@RequestMapping("producer")
public class ProducerController {
	
	@Autowired
	private ProducerService producerService;
	
	@GetMapping("extreme-producer-awards")
	public ResponseBean findExtremeProducerAwards(ParametersBean parametersBean) {
		 return producerService.getExtremes(2);
	}

}
