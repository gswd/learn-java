package com.hm707.jedis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LH on 2017/12/11.
 */
public class Test {
	public static void main(String[] args) {
		String text = ("产　　地　<a href=\"http://www.hao6v.com/s/hanguodianying/\">美国</a><br> "
			+ "◎类　　别　悬疑/<a href=\"http://www.hao6v.com/s/jingsong/\" target=\"_blank\" >惊悚</a>片")
			;

		Pattern pattern = Pattern.compile("<a.*?>(.+?)</a>");
		Matcher matcher = pattern.matcher(text);

		//System.out.println(matcher.replaceAll("$1"));

		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, "$1");
			System.out.println("====> " + sb.toString());
		}
		matcher.appendTail(sb);

		System.out.println(sb);

	}
}
