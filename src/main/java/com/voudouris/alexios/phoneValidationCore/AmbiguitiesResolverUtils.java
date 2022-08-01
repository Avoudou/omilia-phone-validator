package com.voudouris.alexios.phoneValidationCore;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Utility class containing methods used specifically in
 * {@link AmbiguitiesResolver}
 */
public class AmbiguitiesResolverUtils {

	/**
	 * Creates the base case for the {@link AmbiguitiesResolver} algorithm to
	 * calculate all the possible phone numbers if the input contains ambiguities by
	 * calling {@link #expandNumberAmbiguities(String)} for each String
	 * representation of numbers in the input array.
	 * @return LinkedList containing the expanded input
	 */
	static LinkedList<String> createBaseCase(String[] inputNumbers) {
		LinkedList<String> list = new LinkedList<>();

		for (int i = 0; i < inputNumbers.length; i++) {
			ArrayList<Integer> numbersToAdd = expandNumberAmbiguities(inputNumbers[i]);
			for (Integer num : numbersToAdd) {
				list.add(num + "");
			}
		}
		return list;
	}

	/**
	 * Given a String representation of a number it splits it to number components
	 * according to the pronunciation constraints given, such as the sum of the
	 * numbers equals the input.
	 */
	static ArrayList<Integer> expandNumberAmbiguities(String num) {
		ArrayList<Integer> list = new ArrayList<>();
		String tail;

		if (num.length() >= 3) {
			for (int i = 0; i < num.length() - 2; i++) {
				int toAdd = (int) (Integer.parseInt(num.charAt(i) + "") * (Math.pow(10, num.length() - i - 1)));
				if (toAdd != 0) {
					list.add(toAdd);
				} else {
					continue;
				}
			}
			tail = num.substring(num.length() - 2);
		} else {
			tail = num;
		}
		int lastTwoDigits = Integer.parseInt(tail);
		if ((lastTwoDigits <= 19 || (lastTwoDigits % 10 == 0)) && !(lastTwoDigits == 0 && tail.length() == 2)) {
			list.add(lastTwoDigits);
		} else if (!(lastTwoDigits == 0)) {
			list.add(lastTwoDigits - lastTwoDigits % 10);
			list.add((lastTwoDigits % 10));
		}

		return list;
	}
	
	
	/**
	 * Checks if the two numbers assuming they were pronounced have an ambiguity and can be merged.
	 */
	static boolean isMergeable(String num0, String num1) {
		int aParsed = Integer.parseInt(num0);
		if (num0.length() == 1) {
			return false;
		} else if (num0.length() == 2 && aParsed % 10 == 0 && aParsed >= 20) {
			if (num1.length() == 1 && !num1.equals("0")) {
				return true;
			}
		} else if (num0.length() == 3) {
			if (aParsed % 100 == 0 && num1.length() != 3 && !num1.equals("0")) {
				return true;
			} else if (aParsed % 10 == 0 && num1.length() == 1 && !num1.equals("0")) {
				return true;
			}
		}
		return false;
	}
}
