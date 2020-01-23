package com.hm707.time;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class TestLocalDate {
	public static void main(String[] args) {

		String a = "201904";
		String b = "201909";

		System.out.println(a.compareTo(b));

		//testLocalDate();
		//
		//System.out.println("-----------");


		System.out.println("--------");
		System.out.println(.1 + .2);
		System.out.println(0.1f + 0.2f);

		BigDecimal bd1 = BigDecimal.valueOf(.1);
		BigDecimal bd2 = BigDecimal.valueOf(.2);
		System.out.println(bd1.add(bd2).doubleValue());

	}

	public static void testLocalDate() {
		LocalDate now = LocalDate.parse("2019-08-15");
		LocalDateTime dateTime = LocalDateTime.of(now, LocalTime.MAX);
		Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());

		System.out.println(date);
	}
}
