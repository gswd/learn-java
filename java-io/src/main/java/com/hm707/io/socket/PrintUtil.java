package com.hm707.io.socket;

import static com.hm707.io.socket.ConsoleColor.*;

public abstract class PrintUtil {
	public static <T> void printWithRed(T o) {
		System.out.println(ANSI_RED + o + ANSI_RESET);
	}
}
