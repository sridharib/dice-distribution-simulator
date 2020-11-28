package com.avaloq.simulator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
public class DiceDistributionSimulatorAPITest {

	@Autowired
	private WebTestClient webClient;

	@Test
	public void whenAPICalledWithNoParametersThenExpectOk() throws Exception {

		webClient.get().uri("/api/v1/simulate").header(HttpHeaders.ACCEPT, "application/json").exchange().expectStatus()
				.isOk().expectBody().jsonPath("$.rolls").isNotEmpty().jsonPath("$.details").isNotEmpty()
				.jsonPath("$.errors").isEmpty();
	}

	@Test
	public void whenAPICalledWithParametersThenExpectOk() throws Exception {

		webClient.get().uri(
				"/api/v1/simulate?numberOfDice={numberOfDice}&numberOfRolls={numberOfRolls}&sidesOfDice={sidesOfDice}",
				3, 100, 6).header(HttpHeaders.ACCEPT, "application/json").exchange().expectStatus().isOk().expectBody()
				.jsonPath("$.rolls").isNotEmpty().jsonPath("$.details").isNotEmpty().jsonPath("$.errors").isEmpty();
	}

	@Test
	public void whenAPICalledWithInvalidParametersThenExpectBadRequest() throws Exception {

		String expectedError = "{\"rolls\":[],\"details\":{},\"errors\":[\"Number of dice should be at least 1\",\"Number of rolls should be at least 1\",\"Sides of the dice should be at least 4\"]}";
		webClient.get().uri(
				"/api/v1/simulate?numberOfDice={numberOfDice}&numberOfRolls={numberOfRolls}&sidesOfDice={sidesOfDice}",
				0, 0, 0).header(HttpHeaders.ACCEPT, "application/json").exchange().expectStatus().isBadRequest()
				.expectBody().jsonPath("$.rolls").isEmpty().jsonPath("$.details").isEmpty().jsonPath("$.errors")
				.isNotEmpty().json(expectedError);
	}

}
