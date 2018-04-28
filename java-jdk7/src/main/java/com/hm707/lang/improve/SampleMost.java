package com.hm707.lang.improve;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by LH on 2018/4/28.
 */
public class SampleMost {

	public static void main(String[] args) {

		System.out.println("----- binaryLiteral -----");
		binaryLiteral();

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
