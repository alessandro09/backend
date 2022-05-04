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
	
	public static final String EXPECTED_RESULT = "{\"min\":[{\"previousWin\":1986,\"followingWin\":1987,\"interval\":1,\"producer\":\"Yoram Globus and Menahem Golan\"},{\"previousWin\":2011,\"followingWin\":2012,\"interval\":1,\"producer\":\"Wyck Godfrey, Stephenie Meyer and Karen Rosenfelt\"}],\"max\":[{\"previousWin\":1980,\"followingWin\":1989,\"interval\":9,\"producer\":\"Jerry Weintraub\"},{\"previousWin\":1985,\"followingWin\":1993,\"interval\":8,\"producer\":\"Dino De Laurentiis\"}]}";
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void shouldReturnTwoProducersInEachExtremeOfAwards() throws JsonProcessingException {
		var response = restTemplate.getForObject("http://localhost:" + port + "/producer/extreme", ResponseBean.class);
		
		var result = new ObjectMapper().writeValueAsString(response);
		
		assertEquals(EXPECTED_RESULT, result);
	}

}
