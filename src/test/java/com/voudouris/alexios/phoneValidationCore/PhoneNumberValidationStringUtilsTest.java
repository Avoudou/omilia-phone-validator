package com.voudouris.alexios.phoneValidationCore;

import static org.junit.Assert.*;
import org.junit.Test;

import com.voudouris.alexios.phoneValidationCore.PhoneNumberValidationStringUtils;


public class PhoneNumberValidationStringUtilsTest {
	@Test
	public void inputSplitting() {

		InputSplittingTestCase[] testcases = { new InputSplittingTestCase("", new String[] { "" }),
				new InputSplittingTestCase("25", new String[] { "25" }),
				new InputSplittingTestCase("210 5 15 700", new String[] { "210", "5", "15", "700" }),
				new InputSplittingTestCase("44 5 6 7 756", new String[] { "44", "5", "6", "7", "756" }) };
		for (InputSplittingTestCase testcase : testcases) {
			assertArrayEquals(testcase.result, PhoneNumberValidationStringUtils.splitInput(testcase.input));
		}
	}

	@Test
	public void isGreekPhoneNumberCorrectTest() {
		assertTrue(PhoneNumberValidationStringUtils.isGreekPhoneNumber("2106728342"));
		assertTrue(PhoneNumberValidationStringUtils.isGreekPhoneNumber("00302106728342"));
		assertTrue(PhoneNumberValidationStringUtils.isGreekPhoneNumber("6906728342"));
		assertTrue(PhoneNumberValidationStringUtils.isGreekPhoneNumber("00306906728342"));
	}

	@Test
	public void isGreekPhoneNumberMoreDigitisTest() {
		assertFalse(PhoneNumberValidationStringUtils.isGreekPhoneNumber("21067283429"));
		assertFalse(PhoneNumberValidationStringUtils.isGreekPhoneNumber("003021067283429"));
		assertFalse(PhoneNumberValidationStringUtils.isGreekPhoneNumber("69067283429"));
		assertFalse(PhoneNumberValidationStringUtils.isGreekPhoneNumber("003069067283429"));
		assertFalse(PhoneNumberValidationStringUtils.isGreekPhoneNumber("00309999999999999999"));

	}

	@Test
	public void isGreekPhoneNumberLessDigitisTest() {
		assertFalse(PhoneNumberValidationStringUtils.isGreekPhoneNumber("210672834"));
		assertFalse(PhoneNumberValidationStringUtils.isGreekPhoneNumber("0030210672834"));
		assertFalse(PhoneNumberValidationStringUtils.isGreekPhoneNumber("690672834"));
		assertFalse(PhoneNumberValidationStringUtils.isGreekPhoneNumber("0030690672888"));
	}

	@Test
	public void isGreekPhoneNumberWrongStartTest() {
		assertFalse(PhoneNumberValidationStringUtils.isGreekPhoneNumber("0216728342"));
		assertFalse(PhoneNumberValidationStringUtils.isGreekPhoneNumber("01302106728342"));
		assertFalse(PhoneNumberValidationStringUtils.isGreekPhoneNumber("10302106728342"));
		assertFalse(PhoneNumberValidationStringUtils.isGreekPhoneNumber("00303106728342"));
		assertFalse(PhoneNumberValidationStringUtils.isGreekPhoneNumber("8906728342"));
		assertFalse(PhoneNumberValidationStringUtils.isGreekPhoneNumber("6806728342"));
		assertFalse(PhoneNumberValidationStringUtils.isGreekPhoneNumber("00406906728342"));
	}

	@Test
	public void containsOnlyNumbersTest() {
		assertTrue(PhoneNumberValidationStringUtils.containsOnlyNumbers("210 15 0 32"));
		assertFalse(PhoneNumberValidationStringUtils.containsOnlyNumbers("210 a 0 32"));
		assertFalse(PhoneNumberValidationStringUtils.containsOnlyNumbers("210 0 32 B"));
		assertFalse(PhoneNumberValidationStringUtils.containsOnlyNumbers("+ 210 15 0 32"));
	}

	@Test
	public void containsAtMostSingleWhiteSpacesTest() {
		assertTrue(PhoneNumberValidationStringUtils.containsAtMostSingleWhiteSpaces(""));
		assertTrue(PhoneNumberValidationStringUtils.containsAtMostSingleWhiteSpaces(" "));
		assertTrue(PhoneNumberValidationStringUtils.containsAtMostSingleWhiteSpaces("1 23 456 789"));

		assertFalse(PhoneNumberValidationStringUtils.containsAtMostSingleWhiteSpaces("  "));
		assertFalse(PhoneNumberValidationStringUtils.containsAtMostSingleWhiteSpaces("       "));
		assertFalse(PhoneNumberValidationStringUtils.containsAtMostSingleWhiteSpaces("1 23  456 789"));
		assertFalse(PhoneNumberValidationStringUtils.containsAtMostSingleWhiteSpaces("1 23   456 789"));
		assertFalse(PhoneNumberValidationStringUtils.containsAtMostSingleWhiteSpaces("1 23 456   789"));
		assertFalse(PhoneNumberValidationStringUtils.containsAtMostSingleWhiteSpaces("1  23 456 789"));
	}

	static class InputSplittingTestCase {
		private String input;
		private String[] result;

		public InputSplittingTestCase(String input, String[] result) {
			super();
			this.input = input;
			this.result = result;
		}
	}

}
