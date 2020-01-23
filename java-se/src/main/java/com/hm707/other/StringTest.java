package com.hm707.other;

import java.nio.charset.Charset;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Random;

/**
 * Created by LH on 2018/5/14.
 */
public class StringTest {

	public static void main(String[] args) {
		//String names = "22, ";
		//int position = names.lastIndexOf(", ");
		//
		//System.out.println(position);
		//if (position >= 0) {
		//	names = names.substring(0, position);
		//}
		//System.out.println(names);

		//System.out.println(Character.isValidCodePoint(99));
		//System.out.println(Character.toChars(0xFFFF)[1]);
		char a = '冬';
		System.out.println(Integer.toHexString((int) a));
		System.out.println((int) a);

		System.out.println(Character.toChars(0x2F81A));

		LocalDateTime localDateTime = LocalDateTime.of(2018, 1, 22, 10, 10, 0);
		localDateTime = LocalDateTime.now();

		Instant instant = localDateTime.toInstant(ZoneOffset.of("+08:00"));
		Date date = new Date(instant.toEpochMilli());
		System.out.println(date);

		System.out.println(instant);

		System.out.println(new Date(0));

		System.out.println("--------------------");

		LocalDateTime ldt = LocalDateTime.now();
		ldt = ldt.withHour(15).withMinute(20).withSecond(0).withNano(0);
		ldt = ldt.toLocalDate().atTime(15, 10);
		ldt = ldt.plusHours(1);
		ldt = ldt.with(ChronoField.MILLI_OF_DAY, 0);
		System.out.println(DateTimeFormatter.ISO_DATE_TIME.format(ldt));

		LocalDate nowDate = LocalDate.of(2018, 8, 13);
		DayOfWeek dayOfWeek = nowDate.getDayOfWeek();
		System.out.println(dayOfWeek);

		LocalDate ld = LocalDate.now();
		ldt = ld.with(TemporalAdjusters.next(DayOfWeek.TUESDAY)).atTime(10, 0);
		System.out.println(ldt);

		System.out.println(LocalTime.MAX);

		long aa = ld.range(ChronoField.DAY_OF_MONTH).getMaximum();
		System.out.println(aa);


		LocalDate born = LocalDate.of(1990,06,20);
		int year = Period.between(born, LocalDate.now()).getYears();
		System.out.println("birth day -> " + year);

		long lateMinutes = Duration.between(
				LocalTime.of(9,0),
				LocalTime.now()).toMinutes();

		System.out.println(lateMinutes);

		System.out.println(Math.random());

		System.out.println("------------------");

		Random random1 = new Random(2222);
		for (int i = 0; i < 10; i++) {
			System.out.print(random1.nextInt(100) + "  ");

		}
		System.out.println();
		Random random2 = new Random(2222);
		for (int i = 0; i < 10; i++) {
			System.out.print(random2.nextInt(100) + "  ");

		}
	}
}
