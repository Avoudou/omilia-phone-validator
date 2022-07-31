package com.voudouris.alexios.phoneValidationCore;

public class PhoneNumberValidationUtils {

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

	public static String[] splitInput(String input) {
		return input.trim().split(" ");
	}
}
