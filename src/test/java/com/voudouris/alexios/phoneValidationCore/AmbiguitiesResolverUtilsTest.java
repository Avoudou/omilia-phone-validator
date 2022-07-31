package com.voudouris.alexios.phoneValidationCore;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import org.junit.Test;

public class AmbiguitiesResolverUtilsTest {

	@Test
	public void inputSplitting() {

		InputSplittingTestCase[] testcases = { new InputSplittingTestCase("", new String[] { "" }),
				new InputSplittingTestCase("25", new String[] { "25" }),
				new InputSplittingTestCase("210 5 15 700", new String[] { "210", "5", "15", "700" }),
				new InputSplittingTestCase("44 5 6 7 756", new String[] { "44", "5", "6", "7", "756" }) };
		for (InputSplittingTestCase testcase : testcases) {
			assertArrayEquals(testcase.result, AmbiguitiesResolverUtils.splitInput(testcase.input));

		}
	}

	@Test
	public void expandNumberAmbiguitiesSingleDigitTest() {
		assertEquals(new ArrayList<>(Arrays.asList(0)), AmbiguitiesResolverUtils.expandNumberAmbiguities("0"));
		assertEquals(new ArrayList<>(Arrays.asList(1)), AmbiguitiesResolverUtils.expandNumberAmbiguities("1"));
		assertEquals(new ArrayList<>(Arrays.asList(5)), AmbiguitiesResolverUtils.expandNumberAmbiguities("5"));
		assertEquals(new ArrayList<>(Arrays.asList(9)), AmbiguitiesResolverUtils.expandNumberAmbiguities("9"));
	}

	@Test
	public void expandNumberAmbiguitiesDoubleDigitTest() {
		assertEquals(new ArrayList<>(Arrays.asList(10)), AmbiguitiesResolverUtils.expandNumberAmbiguities("10"));
		assertEquals(new ArrayList<>(Arrays.asList(19)), AmbiguitiesResolverUtils.expandNumberAmbiguities("19"));
		assertEquals(new ArrayList<>(Arrays.asList(20)), AmbiguitiesResolverUtils.expandNumberAmbiguities("20"));
		assertEquals(new ArrayList<>(Arrays.asList(20, 1)), AmbiguitiesResolverUtils.expandNumberAmbiguities("21"));
		assertEquals(new ArrayList<>(Arrays.asList(90, 9)), AmbiguitiesResolverUtils.expandNumberAmbiguities("99"));
	}

	@Test
	public void expandNumberAmbiguitiesThreeDigitTest() {
		assertEquals(new ArrayList<>(Arrays.asList(100)), AmbiguitiesResolverUtils.expandNumberAmbiguities("100"));
		assertEquals(new ArrayList<>(Arrays.asList(100, 19)), AmbiguitiesResolverUtils.expandNumberAmbiguities("119"));
		assertEquals(new ArrayList<>(Arrays.asList(100, 20)), AmbiguitiesResolverUtils.expandNumberAmbiguities("120"));
		assertEquals(new ArrayList<>(Arrays.asList(100, 20, 1)),
				AmbiguitiesResolverUtils.expandNumberAmbiguities("121"));
		assertEquals(new ArrayList<>(Arrays.asList(500, 30, 6)),
				AmbiguitiesResolverUtils.expandNumberAmbiguities("536"));
		assertEquals(new ArrayList<>(Arrays.asList(900, 90, 9)),
				AmbiguitiesResolverUtils.expandNumberAmbiguities("999"));
	}

	@Test
	public void createBaseCaseTest() {
		AmbiguityExpantionTestCase[] testCases = {
				new AmbiguityExpantionTestCase(new String[] { "999", "0", "30", "210", "777", "1", "12", "1" },
						new String[] { "900", "90", "9", "0","30", "200", "10", "700", "70", "7", "1", "12", "1" }),
				new AmbiguityExpantionTestCase(new String[] { "0", "0", "30", "210", "777", "1", "12", "25" },
						new String[] { "0", "0", "30", "200", "10", "700", "70", "7", "1", "12", "20", "5" }),
				new AmbiguityExpantionTestCase(new String[] { "2", "10", "35", "10", "777", "1", "12", "995" },
						new String[] { "2", "10", "30","5" ,"10",  "700", "70", "7", "1", "12", "900", "90","5" })

		};
		for(AmbiguityExpantionTestCase testcase:testCases) {
			assertEquals(testcase.getResult(), AmbiguitiesResolverUtils.createBaseCase(testcase.getInput()));
		}
	}

	@Test
	public void isMergableTest() {
		assertFalse(AmbiguitiesResolverUtils.isMergable("0", "1"));
		assertFalse(AmbiguitiesResolverUtils.isMergable("10", "1"));
		assertFalse(AmbiguitiesResolverUtils.isMergable("20", "0"));
		assertFalse(AmbiguitiesResolverUtils.isMergable("100", "0"));
		assertFalse(AmbiguitiesResolverUtils.isMergable("111", "1"));
		assertFalse(AmbiguitiesResolverUtils.isMergable("110", "11"));
		assertFalse(AmbiguitiesResolverUtils.isMergable("100", "236"));
		assertFalse(AmbiguitiesResolverUtils.isMergable("200", "100"));

		assertTrue(AmbiguitiesResolverUtils.isMergable("20", "1"));
		assertTrue(AmbiguitiesResolverUtils.isMergable("90", "9"));
		assertTrue(AmbiguitiesResolverUtils.isMergable("100", "1"));
		assertTrue(AmbiguitiesResolverUtils.isMergable("100", "19"));
		assertTrue(AmbiguitiesResolverUtils.isMergable("900", "99"));
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

	static class AmbiguityExpantionTestCase {

		private String[] input;
		private LinkedList<String> results = new LinkedList<>();

		public AmbiguityExpantionTestCase(String[] input, String[] results) {
			super();
			this.input = input;
			Collections.addAll(this.results, results);
		}

		public String[] getInput() {
			return input;
		}

		public LinkedList<String> getResult() {
			return results;
		}

	}

}
