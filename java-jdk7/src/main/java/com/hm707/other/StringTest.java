package com.hm707.other;

/**
 * Created by LH on 2018/5/14.
 */
public class StringTest {

	public static void main(String[] args) {
		String names = "22, ";
		int position = names.lastIndexOf(", ");

		System.out.println(position);
		if (position >= 0) {
			names = names.substring(0, position);
		}
		System.out.println(names);

	}
}
