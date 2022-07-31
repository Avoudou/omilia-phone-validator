package com.voudouris.alexios.phoneValidationCore;

import static org.junit.Assert.*;
import org.junit.Test;

import com.voudouris.alexios.phoneValidationCore.PhoneNumberValidationUtils;

public class NumberValidatorTest {

	@Test
	public void isGreekPhoneNumberCorrectTest() {
		assertTrue(PhoneNumberValidationUtils.isGreekPhoneNumber("2106728342"));
		assertTrue(PhoneNumberValidationUtils.isGreekPhoneNumber("00302106728342"));
		assertTrue(PhoneNumberValidationUtils.isGreekPhoneNumber("6906728342"));
		assertTrue(PhoneNumberValidationUtils.isGreekPhoneNumber("00306906728342"));
	}
	
	@Test
	public void isGreekPhoneNumberMoreDigitisTest() {
		assertFalse(PhoneNumberValidationUtils.isGreekPhoneNumber("21067283429"));
		assertFalse(PhoneNumberValidationUtils.isGreekPhoneNumber("003021067283429"));
		assertFalse(PhoneNumberValidationUtils.isGreekPhoneNumber("69067283429"));
		assertFalse(PhoneNumberValidationUtils.isGreekPhoneNumber("003069067283429"));
		assertFalse(PhoneNumberValidationUtils.isGreekPhoneNumber("00309999999999999999"));
		
	}
	@Test
	public void isGreekPhoneNumberLessDigitisTest() {
		assertFalse(PhoneNumberValidationUtils.isGreekPhoneNumber("210672834"));
		assertFalse(PhoneNumberValidationUtils.isGreekPhoneNumber("0030210672834"));
		assertFalse(PhoneNumberValidationUtils.isGreekPhoneNumber("690672834"));
		assertFalse(PhoneNumberValidationUtils.isGreekPhoneNumber("0030690672888"));		
	}
	@Test
	public void isGreekPhoneNumberWrongStartTest() {
		assertFalse(PhoneNumberValidationUtils.isGreekPhoneNumber("0216728342"));
		assertFalse(PhoneNumberValidationUtils.isGreekPhoneNumber("01302106728342"));
		assertFalse(PhoneNumberValidationUtils.isGreekPhoneNumber("10302106728342"));
		assertFalse(PhoneNumberValidationUtils.isGreekPhoneNumber("00303106728342"));
		assertFalse(PhoneNumberValidationUtils.isGreekPhoneNumber("8906728342"));
		assertFalse(PhoneNumberValidationUtils.isGreekPhoneNumber("6806728342"));
		assertFalse(PhoneNumberValidationUtils.isGreekPhoneNumber("00406906728342"));	
	}
	

}
