package com.trader.eBroker.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;



public class DateUtilTest {
	
	private static LocalDateTime dateTime;
	
	@Test
	@DisplayName("Should check if datetime is weekday and in working hours")
	public void shouldReturnTrue() {
		String str = "2021-12-13 10:30";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		dateTime = LocalDateTime.parse(str, formatter);
		try(MockedStatic<LocalDateTime> date = Mockito.mockStatic(LocalDateTime.class)) {
			date.when(LocalDateTime :: now).thenReturn(dateTime);
			Assertions.assertTrue(DateUtil.checkForWorkingHours());
		} 
	}
	
	@Test
	@DisplayName("Should check if datetime is weekend or out of working hours")
	public void shouldReturnfalse() {
		String str = "2021-12-12 10:30";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		dateTime = LocalDateTime.parse(str, formatter);
		try(MockedStatic<LocalDateTime> date = Mockito.mockStatic(LocalDateTime.class)) {
			date.when(LocalDateTime :: now).thenReturn(dateTime);
			Assertions.assertFalse(DateUtil.checkForWorkingHours());
		} 
	}
	
}
