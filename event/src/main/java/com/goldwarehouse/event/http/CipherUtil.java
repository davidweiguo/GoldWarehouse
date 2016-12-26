package com.goldwarehouse.event.http;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;

public class CipherUtil {
	public final byte[] s = {0x0D, 0x0A};
	private static volatile CipherUtil cu;
	private static Cipher en;
	private static Cipher de;
	private CipherUtil() {		
	}
	
	public static CipherUtil getInstance() throws Exception{
		if (cu == null) {
			synchronized(CipherUtil.class) {
				if (cu == null) {
					cu = new CipherUtil();
					KeyGenerator kg = KeyGenerator.getInstance("DES");
					SecretKey sk = kg.generateKey();
					en = Cipher.getInstance("DES/CBC/PKCS5Padding");
					en.init(Cipher.ENCRYPT_MODE, sk);
					byte[] iv = en.getIV();
					de = Cipher.getInstance("DES/CBC/PKCS5Padding");
					de.init(Cipher.DECRYPT_MODE, sk, new IvParameterSpec(iv));
				}
			}
		}
		return cu;
	}
	
	public byte[] encrypt(String d1, String d2) throws IllegalBlockSizeException, BadPaddingException {
		byte[] enData = new byte[d1.length() + d2.length() + s.length];
		System.arraycopy(d1.getBytes(), 0, enData, 0, d1.length());
		System.arraycopy(s, 0, enData, d1.length(), s.length);
		System.arraycopy(d2.getBytes(), 0, enData, d1.length() + s.length, d2.length());
		return en.doFinal(enData);
	}
	
	public byte[] encrypt(String ...strings) throws IllegalBlockSizeException, BadPaddingException {
		int total = (strings.length -1)*2;
		for (String str : strings) {
			total += str.length();
		}
		byte[] enData = new byte[total];
		
		int tmp = 0;
		for (String str : strings) {
			System.arraycopy(str.getBytes(), 0, enData, tmp, str.length());
			tmp += str.length();
			if (tmp >= total)
				break;
			System.arraycopy(s, 0, enData, tmp, s.length);
			tmp += s.length;
		}
		return en.doFinal(enData);
	}
	
	public String[] decrypt(byte[] data) throws IllegalBlockSizeException, BadPaddingException {
		byte[] deData = de.doFinal(data);
		String ret = new String(deData);
		return ret.split(new String(s));
	}
	
	public static void main(String[] args) throws Exception {
		byte[] enc = CipherUtil.getInstance().encrypt("userName", "pwd", "created", String.valueOf(System.currentTimeMillis()));
		System.out.println(enc);
		
		String[] de = CipherUtil.getInstance().decrypt(enc);
		for(String d : de) {
			System.out.println(d);
		}
	}
}
