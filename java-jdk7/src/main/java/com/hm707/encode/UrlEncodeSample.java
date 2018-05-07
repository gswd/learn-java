package com.hm707.encode;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by LH on 2018/5/7.
 */
public class UrlEncodeSample {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String encodeStr = URLEncoder.encode("http://10.105.185.127:8080/admin/image/v1/statistics?to=2018-05-01&timeZone=+08:00&projectId=TLEs7VttYT&from=2018-03-01&memberNo=7289", "utf-8");
		System.out.println(encodeStr);

	}
}
