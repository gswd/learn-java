package com.hm707.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 量词
 *
 * 当正则表达式中包含能接受重复的限定符时，通常的行为是（在使整个表达式能得到匹配的前提下）匹配尽可能多的字符，也就是默认是`贪婪匹配`
 *
 * `懒惰匹配`，也就是匹配尽可能少的字符。前面给出的限定符都可以被转化为懒惰匹配模式，只要在量词后面加上一个问号?
 *
 * *?	重复任意次，但尽可能少重复
 * +?	重复1次或更多次，但尽可能少重复
 * ??	重复0次或1次，但尽可能少重复
 * {n,m}?	重复n到m次，但尽可能少重复
 * {n,}?	重复n次以上，但尽可能少重复
 *
 */
public class MeasureWord {
	public static void main(String[] args) {
		eager();
		System.out.println("----------");
		lazy();
	}

	/**
	 * 贪婪匹配
	 */
	public static void eager() {
		String text = "abbcccbd";

		Pattern pattern = Pattern.compile("a.*b");

		Matcher matcher = pattern.matcher(text);
		matcher.find();

		System.out.println(matcher.group());
	}

	/**
	 * 懒惰匹配
	 */
	public static void lazy() {
		String text = "abbcccbd";

		Pattern pattern = Pattern.compile("a.*?b");

		Matcher matcher = pattern.matcher(text);
		matcher.find();

		System.out.println(matcher.group());
	}
}
