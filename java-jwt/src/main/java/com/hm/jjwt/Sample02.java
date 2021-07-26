package com.hm.jjwt;

import java.security.PrivateKey;
import java.util.Base64;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class Sample02 {


	public static void main(String[] args) throws Exception {
		Date now = new Date();
		Date exp = new Date(now.getTime() + 30000);
		PrivateKey key = Keys.keyPairFor(SignatureAlgorithm.RS256).getPrivate();
		System.out.println(Base64.getEncoder().encodeToString(key.getEncoded()));
		String jws = Jwts.builder()
			.setIssuer("d267e1bcbca847e990caec7d1a1071f6")
			.setIssuedAt(now)
			.setExpiration(exp)
			.signWith(key)
			.compact();

		System.out.println(jws);
	}
}
