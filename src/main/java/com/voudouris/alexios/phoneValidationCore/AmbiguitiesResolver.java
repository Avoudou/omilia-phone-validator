package com.voudouris.alexios.phoneValidationCore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *Provides the main functionality required by the project.
 *Stores a single phone number input given in the constructor  {@link #AmbiguitiesResolver(String[])} and all the possible
 *phone numbers  the input can generate.  
 */
public class AmbiguitiesResolver {

	private String[] inputNumbers;

	private ArrayList<LinkedList<String>> possiblePhoneNumbers;

	public AmbiguitiesResolver(String[] inputNumbers) {
		this.inputNumbers = inputNumbers;
	}

	/**
	 * Method to calculate all the possible phone numbers given the instance input.
	 */
	public void calculateResults() {

		Queue<LinkedList<String>> queue = new LinkedList<>();
		Set<LinkedList<String>> explored = new HashSet<>();
		queue.add(AmbiguitiesResolverUtils.createBaseCase(this.inputNumbers));

		while (!queue.isEmpty()) {
			LinkedList<String> current = queue.poll();
			if (explored.contains(current)) {
				continue;
			}
			explored.add(current);
			for (int i = 0; i < current.size() - 1; i++) {
				if (AmbiguitiesResolverUtils.isMergeable(current.get(i), current.get(i + 1))) {
					LinkedList<String> resultCase = new LinkedList<>(current);
					int mergeResult = Integer.parseInt(current.get(i)) + Integer.parseInt(current.get(i + 1));
					resultCase.set(i, mergeResult + "");
					resultCase.remove(i + 1);
					queue.add(resultCase);
				}
			}
		}
		possiblePhoneNumbers = new ArrayList<>(explored);
	}

	
	/**
	 * @return The possible phone numbers generated.
	 */
	public ArrayList<LinkedList<String>> getPossiblePhoneNumbers() {
		return possiblePhoneNumbers;
	}

}
