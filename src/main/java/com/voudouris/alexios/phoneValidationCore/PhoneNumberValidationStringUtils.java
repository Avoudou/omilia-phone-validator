package com.voudouris.alexios.phoneValidationCore;

/**
 * Provides general String utilities for phone validation.
 */
public class PhoneNumberValidationStringUtils {

	/**
	 * @param String
	 *            representation of a telephone number without any whitespace
	 *            separation.
	 * @return True if the number starts either with "2"or"69" and has 10 digits or
	 *         starts with "00302","003069" and has 14 digits. False in all other
	 *         cases.
	 */
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

	/**
	 * Splits the String around the single whitespace character.
	 */
	public static String[] splitInput(String inputString) {
		return inputString.trim().split(" ");
	}

	/**
	 * Returns True if the input String contains only 3-digit numbers blocks
	 * separated by single white space.,False otherwise.
	 */
	public static boolean isValidInput(String inputString) {
		return containsOnlyNumbers(inputString) && containsAtMostSingleWhiteSpaces(inputString)
				&& hasMaxThreeCharBlocks(inputString) && !hasBlockWithZeroPrefix(inputString);
	}

	/**
	 * Returns True if the String only contains numbers.
	 */
	public static boolean containsOnlyNumbers(String inputString) {
		String removedWhiteSpacesInput = inputString.replaceAll("\\s", "");
		return removedWhiteSpacesInput.matches("[0-9]+");
	}

	/**
	 * Returns True if the String dosn't contain two or more consecutive whitespace
	 * characters.
	 */
	public static boolean containsAtMostSingleWhiteSpaces(String inputString) {
		return !inputString.contains("  ");
	}

	/**
	 * Returns True if the input consists of substrings up to 3 characters long
	 * separated by single whitespace.
	 */
	public static boolean hasMaxThreeCharBlocks(String inputString) {
		String[] separatedNumbersList = splitInput(inputString);
		for (String num : separatedNumbersList) {
			if (num.length() > 3) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Returns True if the string contains blocks of numbers starting with 0 and have length more than 1.
	 */
	public static boolean hasBlockWithZeroPrefix(String inputString) {
		String[] blocks = splitInput(inputString);
		for (String str : blocks) {
			if (str.startsWith("0") && str.length() > 1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Method to be used to print if a phone number is a valid Greek phone number or
	 * not.
	 */
	public static String getGreekPhoneNumValidityReport(String candidateNumber) {
		return isGreekPhoneNumber(candidateNumber) ? ("[phone number: VALID]") : ("[phone number: INVALID]");
	}
}
