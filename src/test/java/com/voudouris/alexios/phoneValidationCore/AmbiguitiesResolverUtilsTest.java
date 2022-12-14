package com.voudouris.alexios.phoneValidationCore;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import org.junit.Test;

public class AmbiguitiesResolverUtilsTest {

	@Test
	public void createBaseCaseTest() {
		AmbiguityExpantionTestCase[] testCases = {
				new AmbiguityExpantionTestCase(new String[] { "999", "0", "30", "210", "777", "1", "12", "1" },
						new String[] { "900", "90", "9", "0", "30", "200", "10", "700", "70", "7", "1", "12", "1" }),
				new AmbiguityExpantionTestCase(new String[] { "0", "0", "30", "210", "777", "1", "12", "25" },
						new String[] { "0", "0", "30", "200", "10", "700", "70", "7", "1", "12", "20", "5" }),
				new AmbiguityExpantionTestCase(new String[] { "2", "10", "35", "10", "777", "1", "12", "995" },
						new String[] { "2", "10", "30", "5", "10", "700", "70", "7", "1", "12", "900", "90", "5" })

		};
		for (AmbiguityExpantionTestCase testcase : testCases) {
			assertEquals(testcase.getResult(), AmbiguitiesResolverUtils.createBaseCase(testcase.getInput()));
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
	public void isMergableTest() {
		assertFalse(AmbiguitiesResolverUtils.isMergeable("0", "1"));
		assertFalse(AmbiguitiesResolverUtils.isMergeable("10", "1"));
		assertFalse(AmbiguitiesResolverUtils.isMergeable("20", "0"));
		assertFalse(AmbiguitiesResolverUtils.isMergeable("100", "0"));
		assertFalse(AmbiguitiesResolverUtils.isMergeable("111", "1"));
		assertFalse(AmbiguitiesResolverUtils.isMergeable("110", "11"));
		assertFalse(AmbiguitiesResolverUtils.isMergeable("100", "236"));
		assertFalse(AmbiguitiesResolverUtils.isMergeable("200", "100"));

		assertTrue(AmbiguitiesResolverUtils.isMergeable("20", "1"));
		assertTrue(AmbiguitiesResolverUtils.isMergeable("90", "9"));
		assertTrue(AmbiguitiesResolverUtils.isMergeable("100", "1"));
		assertTrue(AmbiguitiesResolverUtils.isMergeable("100", "19"));
		assertTrue(AmbiguitiesResolverUtils.isMergeable("900", "99"));
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
