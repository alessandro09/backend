package br.com.texo.testback.backend.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.texo.testback.backend.bean.ResponseBean;

@ActiveProfiles({"test"})
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ProducerControllerTest {
	
	public static final String EXPECTED_RESULT = "{\"min\":[{\"previousWin\":1990,\"followingWin\":1991,\"interval\":1,\"producer\":\"Joel Silver\"},{\"previousWin\":1984,\"followingWin\":1990,\"interval\":6,\"producer\":\"Bo Derek\"}],\"max\":[{\"previousWin\":1985,\"followingWin\":1994,\"interval\":9,\"producer\":\"Buzz Feitshans\"},{\"previousWin\":2002,\"followingWin\":2015,\"interval\":13,\"producer\":\"Matthew Vaughn\"}]}";
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void shouldReturnTwoProducersInEachExtremeOfAwards() throws JsonProcessingException {
		var response = restTemplate.getForObject("http://localhost:" + port + "/producer/extreme-producer-awards", ResponseBean.class);
		
		var result = new ObjectMapper().writeValueAsString(response);
		
		assertEquals(EXPECTED_RESULT, result);
	}

}
