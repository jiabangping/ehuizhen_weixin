package com.ehuizhen.weixin.tools;

import java.security.MessageDigest;
import java.util.Arrays;

import org.springframework.stereotype.Component;

@Component
public class SignUtil {

	private static String token = "weixinCourse";

	public static boolean checkSignature(String signature, String timestamp, String nonce) {

		if (signature == null || timestamp == null || nonce == null)
			return false;

		String[] paramArr = new String[] { token, timestamp, nonce };
		Arrays.sort(paramArr);

		String content = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);

		String ciphertext = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(content.toString().getBytes());
			ciphertext = byteToStr(digest);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ciphertext != null ? ciphertext.equals(signature.toUpperCase()) : false;
	}

	private static String byteToStr(byte[] digest) {

		String strDigest = "";
		for (int i = 0; i < digest.length; i++) {
			strDigest = strDigest + byteToHexStr(digest[i]);
		}

		return strDigest;
	}

	private static String byteToHexStr(byte b) {

		char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = digit[(b >>> 4) & 0X0F];
		tempArr[1] = digit[b & 0X0F];

		String s = new String(tempArr);
		return s;
	}
}
