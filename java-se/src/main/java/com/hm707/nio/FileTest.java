package com.hm707.nio;

import java.io.File;

import lombok.NonNull;

public class FileTest {
	public static void main(String[] args) {
		File file = new File("c:\\abc");
		System.out.println(file.getParentFile());
		testNull(null);
	}

	public static void testNull(@NonNull String aa) {
		System.out.println(aa);

	}
}
