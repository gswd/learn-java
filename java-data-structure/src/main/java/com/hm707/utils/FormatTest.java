package com.hm707.utils;

import java.text.DecimalFormat;

public class FormatTest {
	public static void main(String[] args) {
		DecimalFormat format = new DecimalFormat("#.######");

		String result = format.format(29.00) + "%";


		System.out.println(result);
		System.out.println("----------");

		DecimalFormat format1 = new DecimalFormat("#,###");

		String result1 = format1.format(Long.valueOf("10000"));


		System.out.println(result1);
	}
}
