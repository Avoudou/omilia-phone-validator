package com.voudouris.alexios.phoneValidationCore;

import java.util.ArrayList;
import java.util.LinkedList;

public class AmbiguitiesResolverUtils {

	

	public static LinkedList<String> createBaseCase(String[] inputNumbers) {
		LinkedList<String> list = new LinkedList<>();

		for (int i = 0; i < inputNumbers.length; i++) {
			ArrayList<Integer> numbersToAdd = expandNumberAmbiguities(inputNumbers[i]);
			for (Integer num : numbersToAdd) {
				list.add(num + "");
			}
		}
		return list;
	}

	public static ArrayList<Integer> expandNumberAmbiguities(String num) {
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

	public static boolean isMergable(String a, String b) {
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


}
