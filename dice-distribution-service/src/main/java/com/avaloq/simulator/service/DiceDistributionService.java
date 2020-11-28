package com.avaloq.simulator.service;

import com.avaloq.simulator.model.Result;

public interface DiceDistributionService {

	Result rollDice(int numberOfDice, int sidesOfDice, int numberOfRolls);

}
