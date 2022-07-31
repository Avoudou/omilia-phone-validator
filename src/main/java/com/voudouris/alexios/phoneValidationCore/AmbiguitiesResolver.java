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
		queue.add(getBaseCaseLinkedList());

		while (!queue.isEmpty()) {
			LinkedList<String> current = queue.poll();
			if (explored.contains(current)) {
				continue;
			}
			explored.add(current);
			for (int i = 0; i < current.size() - 1; i++) {
				if (isMergable(current.get(i), current.get(i + 1))) {
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

	LinkedList<String> getBaseCaseLinkedList() {
		LinkedList<String> list = new LinkedList<>();

		for (int i = 0; i < this.inputNumbers.length; i++) {
			int checkedInt = Integer.parseInt(this.inputNumbers[i]);
			if (checkedInt <= 19) {
				list.add(this.inputNumbers[i]);
			} else if ((checkedInt <= 99) && (checkedInt % 10 == 0)) {
				list.add(this.inputNumbers[i]);
			} else if ((checkedInt <= 99) && (checkedInt % 10 != 0)) {
				String firstDigit = this.inputNumbers[i].charAt(0) + "0";
				String secondDigit = this.inputNumbers[i].charAt(1) + "";
				list.add(firstDigit);
				list.add(secondDigit);
			} else if (checkedInt >= 100 && checkedInt % 100 == 0) {
				list.add(this.inputNumbers[i]);
			} else if (checkedInt >= 100 && checkedInt % 100 <= 9) {
				String firstDigit = this.inputNumbers[i].charAt(0) + "00";
				String secondDigit = this.inputNumbers[i].charAt(2) + "";
				list.add(firstDigit);
				list.add(secondDigit);
			} else if (checkedInt >= 100 && checkedInt % 100 <= 19) {
				String firstDigit = this.inputNumbers[i].charAt(0) + "00";
				String secondDigit = this.inputNumbers[i].substring(1);
				list.add(firstDigit);
				list.add(secondDigit);
			} else if (checkedInt >= 100 && checkedInt % 100 >= 20 && checkedInt % 10 == 0) {
				String firstDigit = this.inputNumbers[i].charAt(0) + "00";
				String secondDigit = this.inputNumbers[i].substring(1);
				list.add(firstDigit);
				list.add(secondDigit);
			} else if (checkedInt >= 100 && checkedInt % 100 >= 20 && checkedInt % 10 != 0) {
				String firstDigit = this.inputNumbers[i].charAt(0) + "00";
				String secondDigit = this.inputNumbers[i].charAt(1) + "0";
				String thirdDigit = this.inputNumbers[i].charAt(2) + "";
				list.add(firstDigit);
				list.add(secondDigit);
				list.add(thirdDigit);
			}
		}
		return list;
	}

	boolean isMergable(String a, String b) {
		int aParsed = Integer.parseInt(a);
		if (a.length() == 1) {
			return false;
		} else if (a.length() == 2 && aParsed % 10 == 0 && aParsed >= 20) {
			if (b.length() == 1 && !b.equals("0")) {
				return true;
			}
		} else if (a.length() == 3) {
			if (aParsed % 100 == 0 && b.length() != 3 && !b.equals("0")) {
				return true;
			} else if (aParsed % 10 == 0 && b.length() == 1 && !b.equals("0")) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<LinkedList<String>> getPossiblePhoneNumbers() {
		return possiblePhoneNumbers;
	}

}
