package com.voudouris.alexios.phoneValidationCore;

import static org.junit.Assert.*;
import org.junit.Test;

import com.voudouris.alexios.phoneValidationCore.PhoneNumberValidationUtils;

public class NumberValidatorTest {

	@Test
	public void inputSplitting() {
	

		InputSplittingTestCase[] testcases = {
				new InputSplittingTestCase("", new String[] {""}),
				new InputSplittingTestCase("25", new String[] { "25" }), 
				new InputSplittingTestCase("210 5 15 700", new String[] { "210", "5", "15", "700" }),
				new InputSplittingTestCase("44 5 6 7 756", new String[] { "44", "5", "6", "7", "756" })
				};
		for(InputSplittingTestCase testcase:testcases) {
			assertArrayEquals(testcase.result, PhoneNumberValidationUtils.splitInput(testcase.input));
			
		}
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
