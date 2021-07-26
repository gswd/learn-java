package com.hm.jjwt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class Sample01 {

	private static String secretString = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCAcpDabqGpoBA7WGKXZ1Ba1Qnmi8FG4I6gHa4qYg/jdrWYuoc/hpzOb63gnsQ4DBgTcjb+zmI/6OInuU4uhmZmXANeQDW8BLRSG2m521WC9gWiug/6EfbwcTeL4ZfQjXF8ZtvVaMYjFDRi98Klzvz7koL/jcowvfiJs+YlAtyGmU8LObfM6kOR63FizvMmH5niHJxsIoNx5s7DsDYKIebq+OF7gx2BiD5xGh7ZGX5Ufzlm3GziELgCDjdQNVRJp0dt3E7MfmgzOalZQVNVOi2TEb7pMyTFM8VZ4Bz//dCpGAlVqUbtT0uEZVgWI6pHAtC00fs/Yxbb8N9oBdnrgl7PAgMBAAECggEAE3dJVJEnltlXHGdMqb1ShDF9Cp9rbx5g/wsAdhPebI50/uxlys7PvMlYBI6LFiNfErNgekU0xpK4k2SV4J8lraWMLSB2BESCP19AKpZ+4xX9zKd9/Fv0nIPL8zsBEu4coMnFzxXN0UsilU0PBqnT1RxjFe+bEL/5hoTo//rrCMrDYz8gKXmGTJ6aeGw+gffxpTMoro2ZmzhlOHNfUGf05ianbsX3KptsKgy2aPO9TyzdzI5kOm/lEVRbAeeV5eICO/5qnnE8jb2AIsWmVfE5vyagf0WL/mKhW/QR7oW0R1Vlrt3XZUfaiRFL7uk45XhB2mIWzuecxnOxkVbb1By0gQKBgQDWEIw2KmP5o3fw0JlAZphMVz0W3jJNyj+bRecZLKLUD201yL+etsHz2bsKBjyL75jluKvVD6nnVB3AkqVQ1yUc7K1luyAzSZA90sUSzr9DdOKm79G9QV/MtCJuXbAEqFHvHJZNjjoKBRZwoDfH7ROHYv2HSZjgvhfC1sq++jn50QKBgQCZnEaGDYABZWgU2whCGtAaKyyyJzDZkIHDmthGUUw2EFhJGMw1ePLc2pIawrlRlZxGcmLi261zwPnwwQBLru2YJw5dwZJMcjfBF5sM/DueVDMFQ4+CbRQA7pi5L8SZqbacbs1Sk5Sxi2AYom5qRbJ5Zlk3agaIpbkmVdVq31RWnwKBgFcxwFsj4ztBac6uuW3xHGIkOt8AvQ7Qrh4KWDr54o1shzicP+lfWhuA+d34clsxyl7wXRXZXoS6z6+sTChkqlpg0bSZh2E8usNkAiBKFMBecSA8mXt0kJgqW8gG43bSNSWnyFVo9J02O1C/AlmuaQEBwnGD+gC5jaYwpb8q38ixAoGAPtdy9oZxVqDgup98umZzujDtgRiNYIRAwgAH+IRr1DZNl+HgMQQA/x8f5HjDDcBSBXPoRnyucguBdN/QOiYfJz7bHroBGusjKgh5Mw/BaB10HfQssqPjKhpyOlhzapXGkiq2LtEXZKbrMkjJOQq4X/fiCcyDTcXWf/tfavBgdWsCgYEAv10UF9Q1k7zkZ+YcOEtK9YxNOsQeKBqHPgp1Kben8wjpsIQ29Is1kuaPs9svv4pbeE03QHrKr+ZVGf+tEN2l/F3zASaYU7bDLpSR4tIewfbKlNXGdSf4TwOPiCEcRNw0ifnACUrjGuIdNPLeCEvw/5bMLr2x4fqGUWP0L5qxIlU=";

	public static void main(String[] args) throws Exception {

//		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(
//			"works_dev_private.key");
//		ByteArrayOutputStream output = new ByteArrayOutputStream();
//		byte[] buffer = new byte[1024*4];
//		int n = 0;
//		while (-1 != (n = is.read(buffer))) {
//			output.write(buffer, 0, n);
//		}
//		output.toByteArray()

		byte[] byteKey = Base64.getDecoder().decode(secretString);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(byteKey);
		RSAPrivateKey privateKey = (RSAPrivateKey)keyFactory.generatePrivate(privateKeySpec);
		long time = 30*60*1000;//30分钟
		Date now = new Date();
		Date exp = new Date(now.getTime() + 30000);
//		Key key = Keys.secretKeyFor(SignatureAlgorithm.RS256);
//		String jws = Jwts.builder().setIssuer("d267e1bcbca847e990caec7d1a1071f6").setIssuedAt(now).setExpiration(exp).signWith(key).compact();
//		System.out.println(jws);
//		Key key1 = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));

		PrivateKey key = Keys.keyPairFor(SignatureAlgorithm.RS256).getPrivate();
		String jws = Jwts.builder()
			.setIssuer("d267e1bcbca847e990caec7d1a1071f6").setIssuedAt(now).setExpiration(exp)
			.signWith(privateKey)
			.compact();

		System.out.println(jws);
	}
}
