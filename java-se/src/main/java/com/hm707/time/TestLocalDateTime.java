package com.hm707.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class TestLocalDateTime {
	public static void main(String[] args) {
		LocalDateTime now = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
		LocalDateTime sevenDaysAgo = now.minusDays(7);
		long fromDate = sevenDaysAgo.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		long toDate = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		System.out.println(fromDate);
		System.out.println(toDate);

	}
}
