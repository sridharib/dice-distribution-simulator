package com.avaloq.simulator.model;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Result class for DiceDistributionService
 */
@Data
@AllArgsConstructor
public class Result {

	private List<Roll> rolls;
	private Map<Integer, Integer> details;
	private List<String> errors;
}
