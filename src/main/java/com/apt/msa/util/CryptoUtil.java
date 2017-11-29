package com.apt.msa.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class CryptoUtil {

	public static String decrypt(String text) throws Exception {
		String key = "aptApplication$";
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		byte[] keyBytes = new byte[16];
		byte[] b = key.getBytes("UTF-8");
		int len = b.length;
		if (len > keyBytes.length)
			len = keyBytes.length;
		System.arraycopy(b, 0, keyBytes, 0, len);
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
		IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

		Base64 decoder = new Base64();
		byte[] results = cipher.doFinal(decoder.decode(text));
		return new String(results, "UTF-8");
	}

	public static String encrypt(String text) throws Exception {
		String key = "aptApplication$";
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		byte[] keyBytes = new byte[16];
		byte[] b = key.getBytes("UTF-8");
		int len = b.length;
		if (len > keyBytes.length)
			len = keyBytes.length;
		System.arraycopy(b, 0, keyBytes, 0, len);
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
		IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

		byte[] results = cipher.doFinal(text.getBytes("UTF-8"));
		Base64 encoder = new Base64();
		return encoder.encodeAsString(results);
	}
	
	public static void main(String[] args) throws Exception {
		String actualString = "Abcd1234$";
		System.out.println("actualString:" + actualString);
		String encrypted = CryptoUtil.encrypt(actualString);
		System.out.println("Encrypted:" + encrypted);//fOlQuqqMbDElyt+RSk4J8w==
		System.out.println("Decrypted:" + CryptoUtil.decrypt("fOlQuqqMbDElyt+RSk4J8w=="));
	}

}
