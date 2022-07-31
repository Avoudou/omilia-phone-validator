package com.voudouris.alexios.phoneValidationCore;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

import org.junit.Test;

import com.voudouris.alexios.phoneValidationCore.AmbiguitiesResolver;

public class AmbiguitiesResolverTest {

	@Test
	public void getBaseCaseLinkedListTest() {
		BaseCaseTranformationCase noTranformTest1 = new BaseCaseTranformationCase(new String[] { "0" },
				new String[] { "0" });
		assertEquals(noTranformTest1.getResult(),
				new AmbiguitiesResolver(noTranformTest1.getInput()).getBaseCaseLinkedList());
		BaseCaseTranformationCase noTranformTest2 = new BaseCaseTranformationCase(new String[] { "1" },
				new String[] { "1" });
		assertEquals(noTranformTest2.getResult(),
				new AmbiguitiesResolver(noTranformTest2.getInput()).getBaseCaseLinkedList());
		BaseCaseTranformationCase noTranformTest3 = new BaseCaseTranformationCase(new String[] { "10" },
				new String[] { "10" });
		assertEquals(noTranformTest3.getResult(),
				new AmbiguitiesResolver(noTranformTest3.getInput()).getBaseCaseLinkedList());
		BaseCaseTranformationCase noTranformTest4 = new BaseCaseTranformationCase(new String[] { "19" },
				new String[] { "19" });
		assertEquals(noTranformTest4.getResult(),
				new AmbiguitiesResolver(noTranformTest4.getInput()).getBaseCaseLinkedList());
		BaseCaseTranformationCase noTranformTest5 = new BaseCaseTranformationCase(new String[] { "20" },
				new String[] { "20" });
		assertEquals(noTranformTest5.getResult(),
				new AmbiguitiesResolver(noTranformTest5.getInput()).getBaseCaseLinkedList());
		BaseCaseTranformationCase noTranformTest6 = new BaseCaseTranformationCase(new String[] { "90" },
				new String[] { "90" });
		assertEquals(noTranformTest6.getResult(),
				new AmbiguitiesResolver(noTranformTest6.getInput()).getBaseCaseLinkedList());
		BaseCaseTranformationCase noTranformTest7 = new BaseCaseTranformationCase(new String[] { "100" },
				new String[] { "100" });
		assertEquals(noTranformTest7.getResult(),
				new AmbiguitiesResolver(noTranformTest7.getInput()).getBaseCaseLinkedList());
		BaseCaseTranformationCase doubleDigitTranf1 = new BaseCaseTranformationCase(new String[] { "21" },
				new String[] { "20", "1" });
		assertEquals(doubleDigitTranf1.getResult(),
				new AmbiguitiesResolver(doubleDigitTranf1.getInput()).getBaseCaseLinkedList());
		BaseCaseTranformationCase doubleDigitTranf2 = new BaseCaseTranformationCase(new String[] { "99" },
				new String[] { "90", "9" });
		assertEquals(doubleDigitTranf2.getResult(),
				new AmbiguitiesResolver(doubleDigitTranf2.getInput()).getBaseCaseLinkedList());
		BaseCaseTranformationCase treeDigitTranf1 = new BaseCaseTranformationCase(new String[] { "101" },
				new String[] { "100", "1" });
		assertEquals(treeDigitTranf1.getResult(),
				new AmbiguitiesResolver(treeDigitTranf1.getInput()).getBaseCaseLinkedList());
		BaseCaseTranformationCase treeDigitTranf2 = new BaseCaseTranformationCase(new String[] { "119" },
				new String[] { "100", "19" });
		assertEquals(treeDigitTranf2.getResult(),
				new AmbiguitiesResolver(treeDigitTranf2.getInput()).getBaseCaseLinkedList());
		BaseCaseTranformationCase treeDigitTranf3 = new BaseCaseTranformationCase(new String[] { "135" },
				new String[] { "100", "30", "5" });
		assertEquals(treeDigitTranf3.getResult(),
				new AmbiguitiesResolver(treeDigitTranf3.getInput()).getBaseCaseLinkedList());
		BaseCaseTranformationCase comboTranf3 = new BaseCaseTranformationCase(
				new String[] { "0", "0", "30", "210", "777", "1", "12", "25" },
				new String[] { "0", "0", "30", "200", "10", "700", "70", "7", "1", "12", "20", "5" });
		assertEquals(comboTranf3.getResult(), new AmbiguitiesResolver(comboTranf3.getInput()).getBaseCaseLinkedList());
	}

	@Test
	public void isMergableTest() {
		assertFalse(new AmbiguitiesResolver(new String[] {}).isMergable("0", "1"));
		assertFalse(new AmbiguitiesResolver(new String[] {}).isMergable("10", "1"));
		assertFalse(new AmbiguitiesResolver(new String[] {}).isMergable("20", "0"));
		assertFalse(new AmbiguitiesResolver(new String[] {}).isMergable("100", "0"));
		assertFalse(new AmbiguitiesResolver(new String[] {}).isMergable("111", "1"));
		assertFalse(new AmbiguitiesResolver(new String[] {}).isMergable("110", "11"));
		assertFalse(new AmbiguitiesResolver(new String[] {}).isMergable("100", "236"));
		assertFalse(new AmbiguitiesResolver(new String[] {}).isMergable("200", "100"));
		
		
		assertTrue(new AmbiguitiesResolver(new String[] {}).isMergable("20", "1"));
		assertTrue(new AmbiguitiesResolver(new String[] {}).isMergable("90", "9"));
		assertTrue(new AmbiguitiesResolver(new String[] {}).isMergable("100", "1"));
		assertTrue(new AmbiguitiesResolver(new String[] {}).isMergable("100", "19"));
		assertTrue(new AmbiguitiesResolver(new String[] {}).isMergable("900", "99"));
	}

	@Test
	public void calculateResultsComparisonTest() {
		String[] phoneInput0 = { "2", "10", "6", "9", "30", "6", "6", "4" };
		String[] phoneInput1 = { "2", "10", "69", "30", "6", "6", "4" };

		AmbiguitiesResolver resolver0 = new AmbiguitiesResolver(phoneInput0);
		resolver0.calculateResults();
		ArrayList<LinkedList<String>> results0 = resolver0.getPossiblePhoneNumbers();
		HashSet<LinkedList<String>> resultSet0 = new HashSet<>(results0);

		AmbiguitiesResolver resolver1 = new AmbiguitiesResolver(phoneInput1);
		resolver1.calculateResults();
		ArrayList<LinkedList<String>> results1 = resolver1.getPossiblePhoneNumbers();
		HashSet<LinkedList<String>> resultSet1 = new HashSet<>(results1);

		HashSet<LinkedList<String>> expectedSet0 = new HashSet<>();
		LinkedList<String> expectedChild00 = new LinkedList<>(Arrays.asList("2", "10", "6", "9", "30", "6", "6", "4"));
		expectedSet0.add(expectedChild00);
		String[] expectedChild01Content = { "2", "10", "6", "9", "36", "6", "4" };
		LinkedList<String> expectedChild01 = new LinkedList<>();
		expectedChild01.addAll(Arrays.asList(expectedChild01Content));
		expectedSet0.add(expectedChild01);

		HashSet<LinkedList<String>> expectedSet1 = new HashSet<>();
		String[] expectedChild10Content = { "2", "10", "60", "9", "30", "6", "6", "4" };
		LinkedList<String> expectedChild10 = new LinkedList<>();
		expectedChild10.addAll(Arrays.asList(expectedChild10Content));
		expectedSet1.add(expectedChild10);
		String[] expectedChild11Content = { "2", "10", "69", "36", "6", "4" };
		LinkedList<String> expectedChild11 = new LinkedList<>();
		expectedChild11.addAll(Arrays.asList(expectedChild11Content));
		expectedSet1.add(expectedChild11);
		String[] expectedChild12Content = { "2", "10", "69", "30", "6", "6", "4" };
		LinkedList<String> expectedChild12 = new LinkedList<>();
		expectedChild12.addAll(Arrays.asList(expectedChild12Content));
		expectedSet1.add(expectedChild12);
		String[] expectedChild13Content = { "2", "10", "60", "9", "36", "6", "4" };
		LinkedList<String> expectedChild13 = new LinkedList<>();
		expectedChild13.addAll(Arrays.asList(expectedChild13Content));
		expectedSet1.add(expectedChild13);

		assertEquals(expectedSet0, resultSet0);
		assertEquals(expectedSet1, resultSet1);
	}

	@Test
	public void calculateResultsLengthVerificationTest() {
		String[] phoneInput0 = { "0", "0", "30", "69", "700", "24", "1", "3", "50", "2" };
		AmbiguitiesResolver resolver0 = new AmbiguitiesResolver(phoneInput0);
		resolver0.calculateResults();
		ArrayList<LinkedList<String>> results0 = resolver0.getPossiblePhoneNumbers();
		assertEquals(16, results0.size());

		String[] phoneInput1 = { "2", "10", "690", "33", "665", "66", "4" };
		AmbiguitiesResolver resolver1 = new AmbiguitiesResolver(phoneInput1);
		resolver1.calculateResults();
		ArrayList<LinkedList<String>> results1 = resolver1.getPossiblePhoneNumbers();
		assertEquals(32, results1.size());
	}

	static class BaseCaseTranformationCase {

		private String[] input;
		private LinkedList<String> results = new LinkedList<>();

		public BaseCaseTranformationCase(String[] input, String[] results) {
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
