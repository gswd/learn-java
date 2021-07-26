package com.hm707.encryptionSecurity;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

public class Hmac01 {
	public static void main(String[] args) throws Exception {

		KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
		SecretKey key = keyGen.generateKey();
		// 打印随机生成的key:
		byte[] skey = key.getEncoded();
		System.out.println(new BigInteger(1, skey).toString(16));

		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(key);
		mac.update("HelloWorld".getBytes(StandardCharsets.UTF_8));
		byte[] result = mac.doFinal();
		System.out.println(new BigInteger(1, result).toString(16));


	}
}
