package com.hm707.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LH on 2017/12/11.
 */
public class BaseSample {

	public static void main(String[] args) {
		matches();
		System.out.println("-----------------");
		lookingAt();
		System.out.println("-----------------");
		split();
		System.out.println("-----------------");
		find_start_end_reset();
		System.out.println("-----------------");
		group();
		System.out.println("-----------------");
		replace();
		System.out.println("-----------------");
		append();
		System.out.println("-----------------");
		append2();
	}

	/**
	 * matcher.matches() 需要完全匹配
	 * matches() 方法不能用于查找正则表达式多次出现。如果需要，请使用find(), start() 和 end() 方法。
	 */
	public static void matches() {
		Pattern pattern = Pattern.compile("https?://pan\\.baidu\\.com");
		String href = "https://pan.baidu.com/aaa/bbb?ccc";

		Matcher matcher = pattern.matcher(href);
		System.out.println("matches ==> " + matcher.matches());//:>false

		String text =
			"This is the text to be searched " +
				"for occurrences of the http:// pattern.";
		String patternString = ".*http://.*";
		Pattern pattern1 = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
		Matcher matcher1 = pattern1.matcher(text);
		boolean matches = matcher1.matches();
		System.out.println("matches ==> " + matches);//:>true
	}

	/**
	 * matcher.lookingAt() 与matches() 方法类似, 不同的是从字符串开始处搜索，匹配指定模式.
	 * 如果正则表达式匹配文本开头而不匹配整个文本,lookingAt() 返回true,而matches() 返回false.
	 */
	public static void lookingAt() {
		Pattern pattern = Pattern.compile("https?://pan\\.baidu\\.com");
		String href = "http://pan.baidu.com/aaa/bbb?ccc";

		Matcher matcher = pattern.matcher(href);
		System.out.println("lookingAt ==> " + matcher.lookingAt());//:>true

		String href1 = "ahttp://pan.baidu.com/aaa/bbb?ccc";
		Matcher matcher1 = pattern.matcher(href1);
		System.out.println("lookingAt ==> " + matcher1.lookingAt());//:>false

	}

	/**
	 *
	 * find() 尝试查找与该模式匹配的输入序列的下一个子序列。
	 * 此方法从匹配器区域的开头开始，如果该方法的前一次调用成功了并且从那时开始匹配器没有被重置，则从以前匹配操作没有匹配的第一个字符开始。
	 * 如果匹配成功，则可以通过 start、end 和 group 方法获取更多信息。
	 *
	 * start() 和 end() 返回每次匹配的字串在整个文本中的开始和结束位置,
	 * end() 返回的是字符串末尾的后一位，这样，可以在把 start() 和 end() 的返回值直接用在String.substring() 里.
	 * end() 左包右不包
	 *
	 * reset() 重置Matcher 内部的 匹配状态，调用 reset() 会重新从文本开头查找.
	 * 调用 reset(CharSequence).这个方法重置Matcher,同时把一个新的字符串作为参数传入，用于代替创建 Matcher 的原始字符串。
	 */
	public static void find_start_end_reset() {
		String text =
			"This is the text which is to be searched " +
				"for occurrences of the word 'is'.";

		String patternString = "is";

		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(text);

		int count = 0;
		while (matcher.find()) {
			count++;
			System.out.println("found: " + count + " : " + matcher.start() + " - " + matcher.end());
		}
		//:>found: 1 : 2 - 4
		//:>found: 2 : 5 - 7
		//:>found: 3 : 23 - 25
		//:>found: 4 : 70 - 72

		System.out.println("=== reset ===");
		matcher.reset();
		while (matcher.find()) {
			count++;
			System.out.println("found: " + count + " : " + matcher.start() + " - " + matcher.end());
		}
	}

	/**
	 * group(0) 表示整个正则表达式.
	 *
	 * 获得一个有括号标记的分组，分组编号从1开始计算.
	 *
	 */
	public static void group() {
		String text = "John writes about this, and John writes about that," +
			" and John writes about everything. ";

		//当遇到嵌套分组时, 分组编号是由左括号的顺序确定的
		//((John) (.+?) ) ==> group(1) ; (John) ==> group(2) ; (.+?) ==> group(3) ; (everything) ==> group(4)
		String patternString1 = "((John) (.+?) )|(everything)";

		Pattern pattern = Pattern.compile(patternString1);
		Matcher matcher = pattern.matcher(text);
		System.out.println("group count ==> " + matcher.groupCount());
		while (matcher.find()) {
			System.out.println("group(0) ==> " + matcher.group());
			System.out.println("found: " + (matcher.group(1) == null ? matcher.group(4) : matcher.group(2)));
		}
	}

	/**
	 * replaceAll()  replaceFirst()  执行替换方法时,matcher会先被重置(reset), 所以匹配的表达式会从头开始
	 *
	 */
	public static void replace() {
		String text =
			"John writes about this, and John Doe writes about that," +
				" and John Wayne writes about everything.";

		String patternString1 = "((John) (.+?)) ";

		Pattern pattern = Pattern.compile(patternString1);
		Matcher matcher = pattern.matcher(text);

		String replaceAll = matcher.replaceAll("Joe Blocks ");
		System.out.println("replaceAll   ==> " + replaceAll);

		String replaceFirst = matcher.replaceFirst("Joe Blocks ");
		System.out.println("replaceFirst ==> " + replaceFirst);

		//使用$n可以引用某个分组的值
		String text1 = ("产　　地　<a href=\"http://www.hao6v.com/s/hanguodianying/\">美国</a><br> "
			+ "◎类　　别　悬疑/<a href=\"http://www.hao6v.com/s/jingsong/\" target=\"_blank\" >惊悚</a>片")
			.replaceAll("<a.*?>(.+?)</a>", "$1");

		System.out.println(text1);

		//Pattern pattern1 = Pattern.compile("<a.*?>(.+?)</a>");
		//Matcher matcher1 = pattern1.matcher(text1);
		//
		//System.out.println(matcher1.replaceAll("$1"));

		//while (matcher1.find()) {
		//	System.out.println(matcher1.group(1));
		//}
	}

	/**
	 * appendReplacement()对StringBuffer进行逐次替换，而不是像replaceFirst()或replaceAll()那样，只替换第一个或全部子串。
	 * 这是个非常重要的方法，因为它可以调用方法来生成replacement 而 replaceFirst()和replaceAll()只允许用固定的字符串来充当replacement.
	 * 使用这个方法，就可以编程区分group，从而实现更强大的替换功能。
	 *
	 * 调用完appendReplacement( )之后，为了把剩余的字符串拷贝回去，必须调用appendTail()
	 *
	 *
	 */
	public static void append() {
		String text =
			"John writes about this, and John Doe writes about that," +
				" and John Wayne writes about everything.";

		String patternString1 = "((John) (.+?)) ";

		Pattern pattern = Pattern.compile(patternString1);
		Matcher matcher = pattern.matcher(text);

		StringBuffer stringBuffer = new StringBuffer();

		while (matcher.find()) {
			matcher.appendReplacement(stringBuffer, "Joe Blocks ");
			System.out.println(stringBuffer.toString());
		}

		matcher.appendTail(stringBuffer);
		System.out.println(stringBuffer.toString());
	}

	/**
	 * 使用appendReplacement() 可以针对不同的组做不同的替换。
	 */
	public static void append2() {
		String text1 = ("产　　地　<a href=\"http://www.hao6v.com/s/hanguodianying/\">美国</a><br> "
			+ "◎类　　别　悬疑/<a href=\"http://www.hao6v.com/s/jingsong/\" target=\"_blank\" >惊悚</a>片");

		Pattern pattern1 = Pattern.compile("<a.*?>(.+?)</a>");
		Matcher matcher1 = pattern1.matcher(text1);

		//下面代码与matcher1.replaceAll("$1")实现的功能相同
		StringBuffer sb = new StringBuffer();
		while (matcher1.find()) {
			//此处也可以使用$1代替matcher1.group(1), 如果想替换为$符号，则需要转义
			matcher1.appendReplacement(sb, matcher1.group(1));
		}
		matcher1.appendTail(sb);

		System.out.println("append2 ==> " + sb.toString());
	}

	/**
	 * pattern.split()
	 */
	public static void split() {
		String text = "A sep Text sep With sep Many sep Separators";
		String patternString = "sep";

		Pattern pattern = Pattern.compile(patternString);

		String[] split = pattern.split(text);

		System.out.println("split.length ==> " + split.length);//:>5

		for (String element : split) {
			System.out.println("element ==> " + element);
		}
	}
}
