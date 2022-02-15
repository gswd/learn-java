package com.hm707.regex;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LH on 2018/1/15.
 */
public class Test {
	public static void main(String[] args) throws URISyntaxException {

		System.out.println("\\1");
		System.out.println("\\d".matches("^(\\\\d)"));
		Pattern p = Pattern.compile("^(\\\\d)");
		System.out.println(p.pattern());

		//String text = "2017-05-22(戛纳电影节)/2017-10-06(中国大陆)";
		//
		//String patternString1 = "(中国)(大陆|香港|台湾)";
		//String a = "2017-05-22(戛纳电影节)/2017-10-06(美国)";
		//
		//Pattern pattern = Pattern.compile(patternString1);
		//Matcher matcher = pattern.matcher(text);
		//System.out.println("group count ==> " + matcher.groupCount());
		//while (matcher.find()) {
		//	System.out.println(matcher.group());
		//	System.out.println("group(0) ==> " + matcher.group(0));
		//	System.out.println("group(1): " + matcher.group(1));
		//	System.out.println("group(2): " + matcher.group(2));
		//	System.out.println("group: " + matcher.group());
		//}

		//String url = "ed2k://|file|%E5%AF%82%E9%9D%99Z%E7%9A%84%E6%83%8A%E5%A5%87.720p.HD%E4%B8%AD%E8%8B%B1%E5%8F%8C%E5%AD%97[%E6%9C%80%E6%96%B0%E7%94%B5%E5%BD%B1www.6vhao.tv].mp4|1000701283|5BD6E3911339A2AA31BB4E77EFCBB2CB|h=TAJROQCDRACHWVX6E5IAIEK6M6QVMNXI|/";
		//
		//try {
		//	String result = URLDecoder.decode(url, "utf-8");
		//	System.out.println(result);
		//} catch (UnsupportedEncodingException e) {
		//	e.printStackTrace();
		//}

		System.out.println("----------");
		//testURI();
		System.out.println("----------");
		//testParseProtocol();
		System.out.println("----------");

		Pattern EXTRACT_RESOLUTION = Pattern.compile("1080p|720p", Pattern.CASE_INSENSITIVE);
		Matcher matcher = EXTRACT_RESOLUTION.matcher("ed2k://|file|忌RK乐.1080P.BD中英双字[最新电影www.6vhao.tv].mp4|2252109608|BB23847511E4092B9D7E2D11771A1666|h=7PO2TE2XX7F3F3WG4IHMYPFK5656EUYG|/");
		if (matcher.find()) {
			String resolution = matcher.group();
			System.out.println(resolution);
		}
	}

	private static void testParseProtocol() {
		String ed2k = "ed2k://|file|十一月的罪行.720p.BD中英双字[最新电影www.6vhao.tv].mkv|1148634626|7FE118BAC99439B3DD117F165B78D0C6|h=LBGPJN5I36XTIZ25WTWPCRMRPL7IMNG2|/";
		String regex = "(^ed2k://)\\|file\\|([^|]+?)(\\.\\w+)\\|(\\d+)\\|";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(ed2k);

		//System.out.println(matcher.lookingAt());

		if (matcher.find()) {
			System.out.println(matcher.group(0));
			System.out.println("group(1) scheme : " + matcher.group(1));
			System.out.println("group(2): fileName : " + matcher.group(2));
			System.out.println("group(3): ext : " + matcher.group(3));
			System.out.println("group(3): fileSize : " + matcher.group(3));
		}
	}

	public static void testURI() throws URISyntaxException {
		String magnet = "ed2k://|file|十一月的罪行.720p.BD中英双字[最新电影www.6vhao.tv].mkv|1148634626|7FE118BAC99439B3DD117F165B78D0C6|h=LBGPJN5I36XTIZ25WTWPCRMRPL7IMNG2|/";
		URI uri = new URI(magnet);
		System.out.println(uri.getScheme());
		System.out.println(uri.getRawPath());

		System.out.println(uri.toString());
	}

}
