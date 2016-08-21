package org.pomela.common.base.signature;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Created by tao.he on 2016/2/23.
 */
public class SecurityUtil {

	/**
	 * 密钥算法
	 */
	private static final String KEY_ALGORITHM = "AES";

	private static final String CIPHER_ALGORITHM = "AES"; // optional value AES/DES/DESede


	public static String encrypt(String eSrc, String key) throws Exception {
		SecretKey secretKey = getSecretKey(key);
		SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
		byte[] result = cipher.doFinal(eSrc.getBytes("UTF-8"));
		return HexUtils.bytesToHexStr(result);
	}

	public static String decrypt(String dSrc, String key) throws Exception{
		SecretKey secretKey = getSecretKey(key);
		SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
		byte[] result = cipher.doFinal(HexUtils.hexStrToBytes(dSrc));
		return new String(result, "UTF-8");
	}

	private static SecretKey getSecretKey(String key) throws Exception {
		KeyGenerator kGen = KeyGenerator.getInstance(KEY_ALGORITHM); // 创建AES的Key生产者

		//SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		secureRandom.setSeed(key.getBytes());
		kGen.init(secureRandom); // 利用key作为随机数初始化
//		kgen.init(128, new SecureRandom(key.getBytes())); // 利用key作为随机数初始化128位的key生产者

		return kGen.generateKey(); // 根据key，生成一个密钥
	}

	public static void main(String[] args)throws Exception{

		String encrypt = encrypt("hetao", "EyLsrYevSwIDAQAB");
		System.out.println("encrypt: " + encrypt);
		String detrypt = decrypt(encrypt, "EyLsrYevSwIDAQAB");
		System.out.println("decrypt: " + detrypt);

//		String message = "password";
//		String key = "";
//		String entryptedMsg = encrypt(message, key);
//		System.out.println("encrypted message is below :");
//		System.out.println(entryptedMsg);
//
//		String decryptedMsg = decrypt(entryptedMsg, key);
//		System.out.println("decrypted message is below :");
//		System.out.println(decryptedMsg);
//
//		System.out.println(detryptA("wKLUBKml5ffc46nNYtcMma+NlmtpOCjp4CKZjQ1BOV8=", "sC7DrTal6QIDAQAB"));
//		System.out.println(detryptA("OLX/c/iw6mSVIrsCuDHYuTtbYIA5n6XBAM4JvFOFbZc=", "A1m3UI+7KwIDAQAB"));
	}

	public static String encryptA(String sSrc, String sKey) throws Exception {
		if (sKey == null) {
			System.out.print("Key为空null");
			return null;
		}
		// 判断Key是否为16位
		if (sKey.length() != 16) {
			System.out.print("Key长度不是16位");
			return null;
		}
		byte[] raw = sKey.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"
		IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes());

		return new BASE64Encoder().encode(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
	}

	public static String detryptA(String sSrc, String sKey) throws Exception {
		if (sKey == null) {
			System.out.print("Key为空null");
			return null;
		}
		// 判断Key是否为16位
		if (sKey.length() != 16) {
			System.out.print("Key长度不是16位");
			return null;
		}
		byte[] raw = sKey.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"
		IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
		byte[] res = new BASE64Decoder().decodeBuffer(sSrc);
		byte[] encrypted = cipher.doFinal(res);

		return new String(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
	}
}
