package com.hm707.time;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class TestYearMonth {
	public static void main(String[] args) {
		YearMonth ym = YearMonth.now().minusMonths(1);
		System.out.println(ym);//2019-05
		System.out.println(ym.format(DateTimeFormatter.ofPattern("yyyyMM")));

	}
}
