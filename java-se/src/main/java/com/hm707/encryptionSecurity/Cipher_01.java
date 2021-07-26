package com.hm707.encryptionSecurity;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Cipher_01 {
	public static void main(String[] args) throws Exception {
//		Key key = genKey();
		Key key = genKeyWithSpec();

//		SecretKey key1 = new SecretKeySpec()

		String str = "一二三四五六七八九十一二";
		//System.out.println("明文二进制：" + binary(str.getBytes(StandardCharsets.UTF_8)));
		String hexStr = hex(str.getBytes(StandardCharsets.UTF_8));
		System.out.println("明文十六进制：" + hexStr + " --- 长度(字节):" + hexStr.length() / 2);

		byte[] cipherText = encryption(str.getBytes(StandardCharsets.UTF_8), key);

		String text = decrypt(cipherText, key);

		encryptionAndDecryptWithIV(str.getBytes(StandardCharsets.UTF_8), key);

	}

	private static String decrypt(byte[] cipherText, Key key) throws Exception {
		System.out.println("=========开始解密=========");
		Cipher cipher = Cipher.getInstance("AES/ECB/NOPadding");
		cipher.init(Cipher.DECRYPT_MODE, key);

		byte[] result = new byte[48];
		int destPos = 0;
		for (int i = 0; i < cipherText.length; i += 1) {
			byte[] r = cipher.update(cipherText, i, 1);
			if (r.length > 0) {
				System.arraycopy(r, 0, result, destPos, r.length);
				destPos += r.length;
				System.out.println("i : " + i + "  r.length : " + r.length);
			}
		}
		byte[] r1 = cipher.doFinal();
		System.arraycopy(r1, 0, result, destPos, r1.length);

		String text = new String(result, StandardCharsets.UTF_8);
		System.out.println("解密后的结果:" + text + " --- 字节数:" + result.length);
		System.out.println("解密后的字节数组:" + hex(result));
		return text;

	}

	private static byte[] encryption(byte[] bytes, Key key) throws Exception {
		System.out.println("=========开始加密=========");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] result = new byte[48];
		int destPos = 0;
		for (int i = 0; i < bytes.length; i ++) {
			byte[] r = cipher.update(bytes, i, 1);
			if (r.length > 0) {
				System.arraycopy(r, 0, result, destPos, r.length);
				destPos += r.length;
				System.out.println("i : " + i + "  r.length : " + r.length);
			}
		}

		byte[] r1 = cipher.doFinal();
		System.arraycopy(r1, 0, result, destPos, r1.length);
		System.out.println("加密后的结果:" + hex(result) + " --- 长度(字节):" + hex(result).length()/2);
//		System.out.println("加密后的结果:" + binary(result) + " --- 长度(字节):" + hex(result).length()/2);
		return result;
	}

	private static void encryptionAndDecryptWithIV(byte[] bytes, Key key) throws Exception {
		System.out.println("=========开始加密=========");

		SecureRandom secureRandom = SecureRandom.getInstanceStrong();
		byte[] iv = secureRandom.generateSeed(16);
		IvParameterSpec ivps = new IvParameterSpec(iv);

		Cipher cipher = Cipher.getInstance("AES/OFB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, ivps);
		byte[] cipherText = cipher.doFinal(bytes);
		System.out.println("加密后的结果:" + hex(cipherText) + " --- 长度(字节):" + hex(cipherText).length()/2);


		System.out.println("=========开始解密=========");

		cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
		byte[] plaintext = cipher.doFinal(cipherText);
		String text = new String(plaintext, StandardCharsets.UTF_8);
		System.out.println("解密后的结果:" + text + " --- 字节数:" + plaintext.length);
		System.out.println("解密后的字节数组:" + hex(plaintext));
	}

	private static Key genKey() throws NoSuchAlgorithmException {

		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(256);//32字节
		SecretKey secretKey = keyGenerator.generateKey();
		String hexKey = hex(secretKey.getEncoded());
		System.out.println("生成密钥：" + hexKey + " --- 密钥长度(字节)：" + hexKey.length()/2);
		return secretKey;
	}

	private static Key genKeyWithSpec() throws NoSuchAlgorithmException {
		byte[] key = "0123456789abcdef".getBytes(StandardCharsets.UTF_8);//16字节128位
		SecretKey secretKey = new SecretKeySpec(key, "AES");
		String hexKey = hex(secretKey.getEncoded());
		System.out.println("生成密钥：" + hexKey + " --- 密钥长度(字节)：" + hexKey.length()/2);
		return secretKey;
	}

	private static String hex(byte[] code) {
		return new BigInteger(1, code).toString(16);
	}

	private static String binary(byte[] code) {
		return new BigInteger(1, code).toString(2);
	}
}
