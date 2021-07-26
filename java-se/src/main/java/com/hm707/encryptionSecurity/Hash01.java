package com.hm707.encryptionSecurity;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash01 {
	public static void main(String[] args) throws Exception {
		// 创建一个MessageDigest实例:
		MessageDigest md = MessageDigest.getInstance("MD5");
		// 反复调用update输入数据:
		md.update("Hello".getBytes(StandardCharsets.UTF_8));
		md.update("World".getBytes(StandardCharsets.UTF_8));
		byte[] result = md.digest(); // 16 bytes: 68e109f0f40ca72a15e05cc22786f8e6
		System.out.println(new BigInteger(1, result).toString(16));
	}
}
