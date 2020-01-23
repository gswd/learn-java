package com.hm707.lang.improve;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by LH on 2018/4/28.
 */
public class SampleMost {

	public static void main(String[] args) {

		System.out.println("----- binaryLiteral -----");
		binaryLiteral();

		System.out.println("----- variableParameter -----");
		variableParameter();

		System.out.println("----- closeResourceException -----");
		try {
			closeResourceException();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

		System.out.println("----- autoCloseResource -----");
    autoCloseResource();
	}

	/**
	 * Java7之前支持 十进制 八进制 十六进制， Java7支持二进制字面量
	 */
	private static void binaryLiteral() {
		int a = 10;
		int b = 010;//八进制
		int c = 0X10;
		int d = 0B0001_0000;
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
	}

	/**
	 *
	 * 要消除警告，可以有三种方式
	 * 1.加 annotation @SafeVarargs
	 * 2.加 annotation @SuppressWarnings({"unchecked", "varargs"})
	 * 3.使用编译器参数 –Xlint:varargs;
	 */
	private static void variableParameter() {
		List l = new ArrayList<Number>();
		List<String> ls = l;// unchecked warning
		l.add(0, new Integer(42));// another unchecked warning
		String s = ls.get(0);// ClassCastException is thrown

		List<String> tList = new ArrayList<>();
		addToList(tList, "haha");
	}

	/**
	 * 在可变参数方法中传递非具体化参数,改进编译警告和错误
	 */
	private static <T> void addToList (List<T> listArg, T... elements) {
		//listArg.addAll(Arrays.asList(elements));
		for (T x : elements) {
			listArg.add(x);
		}
	}

	/**
	 * br.readLine()报错 同时 br.close()也报错时，只会抛出finally块中的异常,使用try-with-resources时只会抛出br.readLine()的异常。
	 *
	 */
	private static void closeResourceException() throws RuntimeException{
		BufferedReader br = null;
		try {
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("word.txt");
			Reader reader = new InputStreamReader(is);
			br = new BufferedReader(reader);

			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				br.close();
				throw new IOException();
			}
		} catch (IOException e) {
			throw new RuntimeException("read .. 异常");
		} finally {
			try {
				br.close();
				throw new IOException();
			} catch (IOException e) {
				throw new RuntimeException("close .. 异常");
			}
		}
	}

	/**
	 * 只有实现了java.lang.AutoCloseable接口，或者java.io.Closable（实际上继随自java.lang.AutoCloseable）接口的对象，才会自动调用其close()函数。
	 * 有点不同的是java.io.Closable要求一实现者保证close函数可以被重复调用。而AutoCloseable的close()函数则不要求是幂等的.
	 */
	private static void autoCloseResource() {

		//资源的 close 方法调用顺序与它们的创建顺序相反。但java IO 中关闭外层流时会关闭内层流。
		//try-with-resources 语句也可以有 catch 和 finally 块，但catch和finally块中的代码都是在声明的资源关闭后执行的。
		try(
				InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("word.txt");
				Reader reader = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(reader)
		){

			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				br.close();
				throw new IOException("read .. 异常");
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

	}
}
