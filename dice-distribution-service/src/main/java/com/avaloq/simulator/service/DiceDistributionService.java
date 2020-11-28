package com.avaloq.simulator.service;

import com.avaloq.simulator.model.Result;

/**
 * Service interface for dice distribution
 */
public interface DiceDistributionService {

	/**
	 * Method to dice distribution simulation
	 * 
	 * @param numberOfDice
	 * @param sidesOfDice
	 * @param numberOfRolls
	 * @return the result
	 */
	Result rollDice(int numberOfDice, int sidesOfDice, int numberOfRolls);

}
