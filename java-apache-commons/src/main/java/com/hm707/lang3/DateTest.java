package com.hm707.lang3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;

public class DateTest {
	public static void main(String[] args) {

		Map<String, Object> map = new HashMap<>();
		map.put("ss", "ss111");
		System.out.println(map);
		try {
			Date date = FastDateFormat.getInstance("yyyyMM").parse("201808");

			String dateStr = DateFormatUtils.format(date, "yyyy-MM-dd");
			System.out.println(dateStr);

			Date date1 = DateUtils.parseDate("201802", "yyyyMM");

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date1);
			calendar.set(Calendar.DAY_OF_MONTH,	calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

			String lastDay = DateFormatUtils.format(calendar.getTime(), "yyyyMMdd");

			System.out.println(lastDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		//System.out.println(getMonthLastDay());
	}


	public static String getMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,	calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat lastDay= new SimpleDateFormat("yyyy-MM-dd");
		return  lastDay.format(calendar.getTime());
	}

}
