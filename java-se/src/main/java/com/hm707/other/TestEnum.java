package com.hm707.other;

public class TestEnum {
	public static void main(String[] args) {
		SizeEnum size = Enum.valueOf(SizeEnum.class, "SMALL");
		System.out.println(SizeEnum.valueOf("LARGE"));
		System.out.println(size.name());
	}
}
