package com.avaloq.simulator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.avaloq.simulator.model.Result;
import com.avaloq.simulator.model.Roll;
import com.avaloq.simulator.service.DiceDistributionService;
import com.avaloq.simulator.service.DiceDistributionServiceImpl;

/**
 * Unit test for simple DiceDistributionService.
 */
public class DiceDistributionServiceTest {

	private DiceDistributionService diceDistributionService = new DiceDistributionServiceImpl();

	@Test
	public void testRollDice() {

		Result rollDice = diceDistributionService.rollDice(3, 6, 100);

		assertNotNull(rollDice);
		assertEquals(rollDice.getRolls().size(), 100);
		assertTrue(rollDice.getDetails().size() > 0);
		assertTrue(rollDice.getErrors().size() == 0);
	}

	@Test
	public void whenDiceRolledThenExpectResult() {

		Result rollDice = diceDistributionService.rollDice(3, 6, 100);

		Roll roll = rollDice.getRolls().stream().findAny().get();
		assertTrue(roll.getSum() >= 3);
		assertTrue(roll.getSum() <= 18);
	}
}
