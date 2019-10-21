package com.goodcub.common;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;

public class DesEncryption {
	
	/**
	 * 加密算法
	 */
	public static String encode(byte[] data,String key) throws Exception {
		
		DESKeySpec dks = new DESKeySpec(key.getBytes());
		
		SecretKeyFactory keyFactory =SecretKeyFactory.getInstance("DES");
		
		//key的长度不能够小于8位字节
		Key secretKey = keyFactory.generateSecret(dks);
		
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		
		IvParameterSpec iv=new IvParameterSpec("BEBE_PAY".getBytes()); //向量
		
		AlgorithmParameterSpec paramSpec = iv;
		
		cipher.init(Cipher.ENCRYPT_MODE, secretKey,paramSpec);
		
		byte[] bytes = cipher.doFinal(data);
		
		return Base64.encodeBase64String(bytes);

	}

	/**
	 * 解密算法
	 */
	public static byte[] decode(byte[] data,String key) throws Exception {
		try {
			// 创建一个DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(key.getBytes());
			
			// 创建一个密匙工厂
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			
			// key 的长度不能够小于 8 位字节
			Key secretKey = keyFactory.generateSecret(dks);
			
			// Cipher对象实际完成解密操作
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding"); 
			
			IvParameterSpec iv = new IvParameterSpec("BEBE_PAY".getBytes());
			
			AlgorithmParameterSpec paramSpec = iv;
			
			cipher.init(Cipher.DECRYPT_MODE, secretKey, paramSpec);
			
			return cipher.doFinal(Base64.decodeBase64(data));
		
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	
}
