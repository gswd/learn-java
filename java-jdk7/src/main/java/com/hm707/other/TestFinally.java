package com.hm707.other;

public class TestFinally {
	public static void main(String[] args) {
		int result1 = test1();
		System.out.println("test1 -> " + result1);

		System.out.println("test2 -> " + test2());

		System.out.println("test3 -> ");
		test3();
	}

	/**
	 * 如果在try或者catch语句内有return语句，则return语句在finally语句执行结束后才执行，但finally并不能改变返回值,
	 *
	 * 这个函数的返回值是0，而不是2，实际执行过程是，在执行到try内的return ret;语句前，会先将返回值ret保存在一个临时变量中，
	 * 然后才执行finally语句，最后try再返回那个临时变量，finally中对ret的修改不会被返回。
	 */
	public static int test1(){
		int ret = 0;
		try{
			return ret;
		}finally{
			ret = 2;
		}
	}

	/**
	 * 如果在finally中也有return语句,try和catch内的return会丢失，实际会返回finally中的返回值。
	 * finally中有return不仅会覆盖try和catch内的返回值，还会掩盖try和catch内的异常，就像异常没有发生一样
	 */
	public static int test2(){
		int ret = 0;
		try{
			// 5/0会触发ArithmeticException，但是finally中有return语句，这个方法就会返回2，而不再向上传递异常了。
			int a = 5/0;
			return ret;
		}finally{
			return 2;
		}
	}

	/**
	 * finally中不仅return语句会掩盖异常，如果finally中抛出了异常，则原异常就会被掩盖,
	 * finally中抛出了RuntimeException，则原异常ArithmeticException就丢失了。
	 */
	public static void test3(){
		try{
			int a = 5/0;
		}finally{
			throw new RuntimeException("hello");
		}
	}

}
