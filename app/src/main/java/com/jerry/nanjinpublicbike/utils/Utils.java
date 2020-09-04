package com.jerry.nanjinpublicbike.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Utils
{
	public static final String DEFAULT_CHARSET_NAME = "UTF-8";
	private static final char[] a = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
	private static final char[] b = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70};

	public static String encodeMd5(String paramString)
	{
		StringBuffer localStringBuffer = new StringBuffer();
		try
		{
			MessageDigest localObject = MessageDigest.getInstance("MD5");
			localObject.update(paramString.getBytes(StandardCharsets.UTF_8));
			byte[] bytes = ((MessageDigest) localObject).digest();
			int i = 0;
			String hexStr = null;
			while (i < bytes.length)
			{
				hexStr = Integer.toHexString(bytes[i] & 0xFF);
				if (hexStr.length() == 1)
				{
					localStringBuffer.append('0');
				}
				localStringBuffer.append(hexStr);
				i += 1;
			}
			return localStringBuffer.toString();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public static byte[]md5(String params)
	{
		byte [] data=null;
		try
		{
			MessageDigest paramString= MessageDigest.getInstance("MD5");
			data=paramString.digest(params.getBytes(StandardCharsets.UTF_8));

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return data;

	}

	public static byte[] decryptMode(String key, byte[] paramArrayOfByte)
	{
		byte[] decryKey =hex(key);

		SecretKeySpec secretKeySpec=new SecretKeySpec(decryKey,"DESede");
		try
		{
			Cipher localCipher = Cipher.getInstance("DESede");
			localCipher.init(2, secretKeySpec);
			decryKey = localCipher.doFinal(paramArrayOfByte);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return decryKey;
	}

	public static byte[] hex(String params)
	{

		String str = new String(encodeHex(md5(params+"HANGNanjing")));
		byte[] bytes=str.getBytes();
		byte[] arrayOfByte = new byte[24];
		int i = 0;
		while (i < 24)
		{
			arrayOfByte[i] = bytes[i];
			i += 1;
		}
		return arrayOfByte;
	}


	public static char[] encodeHex(byte[] paramArrayOfByte)
	{
		return encodeHex(paramArrayOfByte, true);
	}

	public static char[] encodeHex(byte[] paramArrayOfByte, boolean paramBoolean)
	{
//		if (paramBoolean) {}
		for (char[] arrayOfChar = a; ; arrayOfChar = b)
		{
			return encodeHex(paramArrayOfByte, arrayOfChar);
		}
	}

	protected static char[] encodeHex(byte[] paramArrayOfByte, char[] paramArrayOfChar)
	{
		int k = paramArrayOfByte.length;
		char[] arrayOfChar = new char[k << 1];
		int j = 0;
		int i = 0;
		while (i < k)
		{
			int m = j + 1;
			arrayOfChar[j] = paramArrayOfChar[((paramArrayOfByte[i] & 0xF0) >>> 4)];
			j = m + 1;
			arrayOfChar[m] = paramArrayOfChar[(paramArrayOfByte[i] & 0xF)];
			i += 1;
		}
		return arrayOfChar;
	}

}
