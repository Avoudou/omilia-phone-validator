package com.voudouris.alexios.phoneValidationCore;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import org.junit.Test;
import com.voudouris.alexios.phoneValidationCore.AmbiguitiesResolver;

public class AmbiguitiesResolverTest {

	@Test
	public void calculateResultsComparisonTest() {
		CalculateResultsTestCase[] testCases = {
				new CalculateResultsTestCase(new String[] { "2", "10", "6", "9", "30", "6", "6", "4" },
						new String[][] { { "2", "10", "6", "9", "30", "6", "6", "4" },
								{ "2", "10", "6", "9", "36", "6", "4" } }),
				new CalculateResultsTestCase(new String[] { "2", "10", "69", "30", "6", "6", "4" },
						new String[][] { { "2", "10", "60", "9", "30", "6", "6", "4" },
								{ "2", "10", "69", "36", "6", "4" }, { "2", "10", "69", "30", "6", "6", "4" },
								{ "2", "10", "60", "9", "36", "6", "4" } })

		};

		for (CalculateResultsTestCase testCase : testCases) {
			AmbiguitiesResolver resolver = new AmbiguitiesResolver(testCase.getInput());
			resolver.calculateResults();
			assertEquals(testCase.getExpectedSet(), new HashSet<>(resolver.getPossiblePhoneNumbers()));
		}
	}

	@Test
	public void calculateResultsLengthVerificationTest() {
		String[][] phoneInputs = new String[][] { { "0", "0", "30", "69", "700", "24", "1", "3", "50", "2" },
				{ "2", "10", "690", "33", "665", "66", "4" } };
		int[] expectedResults = new int[] { 16, 32 };

		for (int i = 0; i < phoneInputs.length; i++) {
			AmbiguitiesResolver resolver = new AmbiguitiesResolver(phoneInputs[i]);
			resolver.calculateResults();
			assertEquals(expectedResults[i], resolver.getPossiblePhoneNumbers().size());
		}
	}

	static class CalculateResultsTestCase {
		private HashSet<LinkedList<String>> expectedSet;
		private String[] input;

		public CalculateResultsTestCase(String[] phoneInput, String[][] expectedResults) {
			this.input = phoneInput;
			this.expectedSet = new HashSet<>();
			for (String[] strArr : expectedResults) {
				expectedSet.add(new LinkedList<>(Arrays.asList(strArr)));
			}

		}

		public HashSet<LinkedList<String>> getExpectedSet() {
			return expectedSet;
		}

		public String[] getInput() {
			return input;
		}
	}

}
