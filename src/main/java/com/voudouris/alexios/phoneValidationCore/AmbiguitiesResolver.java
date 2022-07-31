package com.voudouris.alexios.phoneValidationCore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class AmbiguitiesResolver {

	private String[] inputNumbers;

	private ArrayList<LinkedList<String>> possiblePhoneNumbers;

	public AmbiguitiesResolver(String[] inputNumbers) {
		this.inputNumbers = inputNumbers;
	}

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
				if (AmbiguitiesResolverUtils.isMergable(current.get(i), current.get(i + 1))) {
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

	
	public ArrayList<LinkedList<String>> getPossiblePhoneNumbers() {
		return possiblePhoneNumbers;
	}

}
