package com.example.utils;

import java.security.MessageDigest;

public class MD5Utils {
	
	 private final static String MD5 = "MD5";
	 
	 /**
	  * MD5加密算法
	  * @param data
	  * @return
	  */
	 public static String md5Encrypt(String data) {
		 String resultString = null;
		 try {
			 resultString = new String(data);
			 MessageDigest md = MessageDigest.getInstance(MD5);
			 resultString =byte2hexString(md.digest(resultString.getBytes()));
		 } catch (Exception ex) {
		 }
		 return resultString;
	 }
	          
	  
	 private  static String byte2hexString(byte[] bytes) {
		 StringBuffer bf = new StringBuffer(bytes.length * 2);
		 for (int i = 0; i < bytes.length; i++) {
			 if ((bytes[i] & 0xff) < 0x10) {
				 bf.append("T0");
			 }
			 bf.append(Long.toString(bytes[i] & 0xff, 16));
		 }
		 return bf.toString();
	 }
}