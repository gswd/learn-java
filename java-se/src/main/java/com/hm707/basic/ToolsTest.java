package com.hm707.basic;

import java.util.concurrent.atomic.AtomicInteger;

public class ToolsTest {

	private static final int[] intArr1 = new int[]{1, 2, 3, 4};
	private static final int[] intArr2 = new int[]{1, 2, 3, 4, 5};

	public static void main(String[] args) {
		System.out.println(isPowerOfTwo(intArr1.length));//true
		System.out.println(isPowerOfTwo(intArr2.length));//false
		System.out.println(4 & -4); //4 == 4
		System.out.println(5 & -5); //1 != 5

		System.out.println("======");

		Chooser1 c1 = new Chooser1();
		for(int i = 0; i < 6; i ++){
			System.out.println(c1.next1());
		}

		System.out.println("====");
		Chooser2 c2 = new Chooser2();
		for(int i = 0; i < 6; i ++){
			System.out.println(c2.next2());
		}
	}

	/**
	 * 判断是否是2的次幂, 目标数必须是正数.
	 * 2的n次方二进制形式都为： 0001  0010 0100 1000 ...
	 * 4 & -4 = 4 -->  0100 & 1100 = 0100
	 * 负数是正数的取反+1
	 */
	private static boolean isPowerOfTwo(int val) {
		return (val & -val) == val;
	}

	/**
	 * 选择下一个元素, 当是2的n次幂时，使用移位计算效率较高
	 */
	private static final class Chooser1 {
		AtomicInteger idx = new AtomicInteger();
		public int next1() {
			int count = idx.getAndIncrement();
			return intArr1[count & (intArr1.length - 1)];
		}

	}

	/**
	 *  选择下一个元素, 通过模运算
	 */
	private static final class Chooser2 {
		AtomicInteger idx = new AtomicInteger();
		public int next2() {
			int count = idx.getAndIncrement();
			return intArr2[Math.abs(count % intArr2.length)];
		}

	}

}
