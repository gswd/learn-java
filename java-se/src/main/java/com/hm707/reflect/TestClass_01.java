package com.hm707.reflect;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.util.Arrays;

public class TestClass_01 {
	public static void main(String[] args) {
		System.out.println("=: " + Closeable.class.getMethods()[0].getDeclaringClass());
		Arrays.stream(ByteArrayInputStream.class.getMethods()).forEach(System.out::println);
		System.out.println(Object.class.equals(Closeable.class.getMethods()[0].getDeclaringClass()));
		System.out.println(Object.class.equals(ByteArrayInputStream.class.getMethods()[13].getDeclaringClass()));
	}
}
