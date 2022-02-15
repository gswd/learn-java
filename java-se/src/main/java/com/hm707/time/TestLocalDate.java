package com.hm707.time;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

public class TestLocalDate {
	static Random random = new Random();
	public static void main(String[] args) {

		String a = "201904";
		String b = "201909";

		System.out.println(a.compareTo(b));

		//testLocalDate();
		//
		//System.out.println("-----------");


		System.out.println("--------");
//		System.out.println(.1 + .2);
//		System.out.println(0.1f + 0.2f);
//
//		BigDecimal bd1 = BigDecimal.valueOf(.1);
//		BigDecimal bd2 = BigDecimal.valueOf(.2);
//		System.out.println(bd1.add(bd2).doubleValue());

		randomApprovalNo();
	}

	public static void testLocalDate() {
		LocalDate now = LocalDate.parse("2019-08-15");
		LocalDateTime dateTime = LocalDateTime.of(now, LocalTime.MAX);
		Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());

		System.out.println(date);
	}

	public static void randomApprovalNo(){

		int randomint = random.nextInt(10000);
		System.out.println(randomint);

		LocalDate now = LocalDate.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		int day = now.getDayOfMonth();

		YearMonth yearMonth = YearMonth.now();
		System.out.println(now.format(DateTimeFormatter.ofPattern("yyMMdd")));

		System.out.println("year: " + year);
		System.out.println("month: " + month);
		System.out.println("day: " + day);
	}
}
