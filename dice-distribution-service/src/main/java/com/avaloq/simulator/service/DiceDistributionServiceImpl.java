package com.avaloq.simulator.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.avaloq.simulator.model.Result;
import com.avaloq.simulator.model.Roll;

import lombok.extern.slf4j.Slf4j;

/**
 * Service implementation of DiceDistributionService
 */
@Slf4j
@Service
public class DiceDistributionServiceImpl implements DiceDistributionService {

	/*
	 * {@inheritDoc}
	 */
	@Override
	public Result rollDice(int numberOfDice, int sidesOfDice, int numberOfRolls) {

		Map<Integer, Integer> details = new HashMap<>();
		List<Roll> rolls = new ArrayList<>();
		for (int i = 1; i <= numberOfRolls; i++) {
			log.info("Roll {}:", i);
			int sum = 0;
			for (int j = 1; j <= numberOfDice; j++) {
				int diceVal = randomDiceValue(1, sidesOfDice);
				sum += diceVal;
				log.info("\tDice {} value {}", j, diceVal);
			}
			log.info("\tsum {}", sum);

			Roll roll = new Roll();
			roll.setRoll(i);
			roll.setSum(sum);
			rolls.add(roll);

			Integer occurrence = details.computeIfAbsent(sum, v -> 0) + 1;
			details.put(sum, occurrence);
		}

		return new Result(rolls, details, Collections.emptyList());
	}

	/**
	 * Method to generate a random dice value within a given range
	 * 
	 * @param min
	 * @param max
	 * @return the random dice value
	 */
	private int randomDiceValue(int min, int max) {

		return (int) ((Math.random() * (max - min + 1)) + min);
	}
}
