/**
 * 
 */
package com.star.sud.employee.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @author sudarshan
 *
 */
public class DateUtil {

	public static Date getDate(Date date, Boolean isEod) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		if (isEod)
			calendar.set(year, month, day, 23, 59, 59);
		else
			calendar.set(year, month, day, 00, 00, 00);
		Date toDate = calendar.getTime();
		return toDate;
	}

	public static String getTimeDifference(Date eod, Date bod) {
		long diff = eod.getTime() - bod.getTime();
		long seconds = diff / 1000 % 60;
		long minutes = diff / (60 * 1000) % 60;
		long hours = (diff / (60 * 60 * 1000)) % 24;
		String timeDifference = hours + ":" + minutes + ":" + seconds;
		return timeDifference;
	}

}
