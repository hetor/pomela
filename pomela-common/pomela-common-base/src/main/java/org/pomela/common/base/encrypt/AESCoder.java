package org.pomela.common.base.encrypt;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * Created by tao.he on 2016/2/23.
 */
public class AESCoder {

	/**
	 * 密钥算法
	 */
	private static final String KEY_ALGORITHM = "AES";

	private static final String CIPHER = "AES";

	private static final String CIPHER_ECB = "AES/ECB/PKCS5Padding"; // "AES/ECB/ISO10126Padding"

	private static final String CIPHER_CBC = "AES/CBC/PKCS5Padding"; // "AES/CBC/ISO10126Padding"

	private static final String e = "9238513401340235";

	private static final String UTF_8 = "UTF-8";


	public static String encryptCbcToHex(String eSrc, String key) throws Exception {
		return HexUtils.bytesToHexStr(encryptCbc(eSrc, key));
	}

	public static String encryptCbcToBase64(String eSrc, String key) throws Exception {
		return Base64.encodeBase64String(encryptCbc(eSrc, key));
	}

	public static byte[] encryptCbc(String eSrc, String key) throws Exception {
		SecretKey secretKey = getSecretKey(key); //CBC工作模式,若直接使用密钥,需要保证密钥的为16字节(128位)
		SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
		Cipher cipher = Cipher.getInstance(CIPHER_CBC);
		IvParameterSpec iv = new IvParameterSpec(e.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
		return cipher.doFinal(eSrc.getBytes(UTF_8));
	}

	public static String decryptCbcFromHex(String dSrc, String key) throws Exception{
		return decryptCbc(HexUtils.hexStrToBytes(dSrc), key);
	}

	public static String decryptCbcFromBase64(String dSrc, String key) throws Exception{
		return decryptCbc(Base64.decodeBase64(dSrc), key);
	}

	public static String decryptCbc(byte[] dSrc, String key) throws Exception {
		SecretKey secretKey = getSecretKey(key); //CBC工作模式,若直接使用密钥,需要保证密钥的为16字节(128位)
		SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
		Cipher cipher = Cipher.getInstance(CIPHER_CBC);
		IvParameterSpec iv = new IvParameterSpec(e.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
		byte[] result = cipher.doFinal(dSrc);
		return new String(result, "UTF-8");
	}

	public static String encryptToHex(String eSrc, String key) throws Exception {
		return HexUtils.bytesToHexStr(encrypt(eSrc, key));
	}

	public static String encryptToBase64(String eSrc, String key) throws Exception {
		return Base64.encodeBase64String(encrypt(eSrc, key));
	}

	public static byte[] encrypt(String eSrc, String key) throws Exception {
		SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), KEY_ALGORITHM);
		Cipher cipher = Cipher.getInstance(CIPHER);
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
		cipher.update(eSrc.getBytes("UTF-8"));
		return cipher.doFinal();
	}

	public static String decryptFromHex(String dSrc, String key) throws Exception {
		return decrypt(HexUtils.hexStrToBytes(dSrc), key);
	}

	public static String decryptFromBase64(String dSrc, String key) throws Exception {
		return decrypt(Base64.decodeBase64(dSrc), key);
	}

	public static String decrypt(byte[] dSrc, String key) throws Exception {
		SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), KEY_ALGORITHM);
		Cipher cipher = Cipher.getInstance(CIPHER);
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
		cipher.update(dSrc);
		byte[] result = cipher.doFinal();
		return new String(result, "UTF-8");
	}

	public static String encryptEcbToHex(String eSrc, String key) throws Exception {
		return HexUtils.bytesToHexStr(encryptEcb(eSrc, key));
	}

	public static String encryptEcbToBase64(String eSrc, String key) throws Exception {
		return Base64.encodeBase64String(encryptEcb(eSrc, key));
	}

	public static byte[] encryptEcb(String eSrc, String key) throws Exception {
		SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), KEY_ALGORITHM);
		Cipher cipher = Cipher.getInstance(CIPHER_ECB);
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
		cipher.update(eSrc.getBytes("UTF-8"));
		return cipher.doFinal();
	}

	public static String decryptEcbToHex(String dSrc, String key) throws Exception {
		return decryptEcb(HexUtils.hexStrToBytes(dSrc), key);
	}

	public static String decryptEcbToBase64(String dSrc, String key) throws Exception {
		return decryptEcb(Base64.decodeBase64(dSrc), key);
	}

	public static String decryptEcb(byte[] dSrc, String key) throws Exception {
		SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), KEY_ALGORITHM);
		Cipher cipher = Cipher.getInstance(CIPHER_ECB);
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
		cipher.update(dSrc);
		byte[] result = cipher.doFinal();
		return new String(result, "UTF-8");
	}

	/**
	 * 获取秘密密钥
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static SecretKey getSecretKey(String key) throws Exception {
		KeyGenerator kGen = KeyGenerator.getInstance(KEY_ALGORITHM); // 创建AES的Key生产者

		//SecureRandom是生成安全随机数序列，key.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有key就行
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		secureRandom.setSeed(key.getBytes());
		kGen.init(16*8, secureRandom); // 利用key作为随机数初始化, AES: must be equal to 128, 192 or 256
//		kgen.init(128, new SecureRandom(key.getBytes())); // 利用key作为随机数初始化128位的key生产者

		return kGen.generateKey(); // 根据key，生成一个密钥
	}

	public static void main(String[] args)throws Exception {
		String ciphertext = encryptCbcToHex("hetao", "EyLsrYevSwIDAQAB");
		System.out.println("ciphertext: " + ciphertext);
		String plaintext = decryptCbcFromHex(ciphertext, "EyLsrYevSwIDAQAB");
		System.out.println("plaintext: " + plaintext);
	}

}
