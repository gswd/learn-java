package com.hm707.utils;

import java.text.DecimalFormat;

public class FormatTest {
	public static void main(String[] args) {
		DecimalFormat format = new DecimalFormat("#.##");

		String result = format.format(22.00) + "%";
		System.out.println(result);
	}
}
