package com.trader.eBroker.util;

import java.time.LocalDateTime;

public class DateUtil {
	
	private static final String SUNDAY = "SUNDAY";
	private static final String SATURDAY = "SATURDAY";

	public static boolean checkForWorkingHours() {
		boolean workingHour = true;
		LocalDateTime now = LocalDateTime.now();
		if(SUNDAY.equals(now.getDayOfWeek().toString()) || SATURDAY.equals(now.getDayOfWeek().toString()) || now.getHour() < 9 || now.getHour() > 17) {
			workingHour =  false;
		}
		return workingHour;
	}
}
