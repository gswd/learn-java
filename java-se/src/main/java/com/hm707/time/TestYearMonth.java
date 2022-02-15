package com.hm707.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class TestYearMonth {
	public static void main(String[] args) {

		LocalDate now = LocalDate.now();
		System.out.println(now.getYear());
		System.out.println(now.getMonthValue());

		YearMonth ym1 = YearMonth.now();
		System.out.println(ym1);
		System.out.println(ym1.format(DateTimeFormatter.ofPattern("yyyyMM")));

		YearMonth ym = YearMonth.now().minusMonths(1);
		System.out.println(ym);//2019-05
		System.out.println(ym.format(DateTimeFormatter.ofPattern("yyyyMM")));


		String ymdt = DateTimeFormatter.ofPattern("dd-HH-mm-ss-SSS").format(LocalDateTime.now());

		System.out.println(ymdt);


	}
}
