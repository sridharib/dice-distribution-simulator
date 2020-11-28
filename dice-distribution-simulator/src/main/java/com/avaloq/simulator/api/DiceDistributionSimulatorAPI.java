package com.avaloq.simulator.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avaloq.simulator.model.Result;
import com.avaloq.simulator.service.DiceDistributionService;

import reactor.core.publisher.Mono;

@RestController
public class DiceDistributionSimulatorAPI {

	private DiceDistributionService diceDistributionService;

	public DiceDistributionSimulatorAPI(DiceDistributionService diceDistributionService) {
		this.diceDistributionService = diceDistributionService;
	}

	@GetMapping("/simulate")
	public ResponseEntity<Mono<Result>> rollDice(@RequestParam(defaultValue = "3") Integer numberOfDice,
			@RequestParam(defaultValue = "6") Integer sidesOfDice,
			@RequestParam(defaultValue = "100") Integer numberOfRolls) {

		List<String> errors = validateInputs(numberOfDice, sidesOfDice, numberOfRolls);
		if (!errors.isEmpty()) {
			return ResponseEntity.badRequest().body(Mono.justOrEmpty(new Result(null, null, errors)));
		}

		return ResponseEntity
				.ok(Mono.justOrEmpty(diceDistributionService.rollDice(numberOfDice, sidesOfDice, numberOfRolls)));
	}

	private List<String> validateInputs(Integer numberOfDice, Integer sidesOfDice, Integer numberOfRolls) {

		List<String> errors = new ArrayList<>();
		if (numberOfDice < 1) {
			errors.add("Number of dice should be at least 1");
		}
		if (numberOfRolls < 1) {
			errors.add("Number of rolls should be at least 1");
		}
		if (sidesOfDice < 4) {
			errors.add("Sides of the dice should be at least 4");
		}
		return errors;
	}
}
