package com.voudouris.alexios.phoneValidationCore;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

import org.junit.Test;

import com.voudouris.alexios.phoneValidationCore.AmbiguitiesResolver;

public class AmbiguitiesResolverTest {

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

}
