package com.voudouris.alexios.phoneValidationCore;

public class PhoneNumberValidationStringUtils {

	public static boolean isGreekPhoneNumber(String candidateNumber) {

		int length = candidateNumber.length();

		if (length == 10) {
			return candidateNumber.startsWith("2") || candidateNumber.startsWith("69");
		}
		if (length == 14) {
			return candidateNumber.startsWith("00302") || candidateNumber.startsWith("003069");
		}

		return false;
	}

	public static String[] splitInput(String inputString) {
		return inputString.trim().split(" ");
	}

	public static boolean isValidInput(String inputString) {
		return containsOnlyNumbers(inputString) && containsAtMostSingleWhiteSpaces(inputString)
				&& hasMaxThreeDigitNumbers(inputString);
	}

	public static boolean containsOnlyNumbers(String inputString) {
		String removedWhiteSpacesInput = inputString.replaceAll("\\s", "");
		return removedWhiteSpacesInput.matches("[0-9]+");
	}

	public static boolean containsAtMostSingleWhiteSpaces(String inputString) {
		return !inputString.contains("  ");
	}

	public static boolean hasMaxThreeDigitNumbers(String inputString) {
		String[] separatedNumbersList = splitInput(inputString);
		for (String num : separatedNumbersList) {
			if (num.length() > 3) {
				return false;
			}

		}
		return true;
	}

	public static String getValidityReport(String candidateNumber) {
		return isGreekPhoneNumber(candidateNumber) ? ("[phone number: VALID]") : ("[phone number: INVALID]");
	}
}
