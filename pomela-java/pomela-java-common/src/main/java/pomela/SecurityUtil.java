package pomela;

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
	public static String DES = "AES"; // optional value AES/DES/DESede

	public static String CIPHER_ALGORITHM = "AES"; // optional value AES/DES/DESede


	public static Key getSecretKey(String key) throws Exception{
		SecretKey securekey = null;
		if(key == null){
			key = "";
		}
		KeyGenerator keyGenerator = KeyGenerator.getInstance(DES);
		keyGenerator.init(new SecureRandom(key.getBytes()));
		securekey = keyGenerator.generateKey();
		return securekey;
	}

	public static String encrypt(String data,String key) throws Exception {
		SecureRandom sr = new SecureRandom();
		Key securekey = getSecretKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		byte[] bt = cipher.doFinal(data.getBytes());
		String strs = new BASE64Encoder().encode(bt);
		return strs;
	}


	public static String detrypt(String message,String key) throws Exception{
		SecureRandom sr = new SecureRandom();
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		Key securekey = getSecretKey(key);
		cipher.init(Cipher.DECRYPT_MODE, securekey,sr);
		byte[] res = new BASE64Decoder().decodeBuffer(message);
		res = cipher.doFinal(res);
		return new String(res);
	}

	public static void main(String[] args)throws Exception{
		String message = "password";
		String key = "";
		String entryptedMsg = encrypt(message, key);
		System.out.println("encrypted message is below :");
		System.out.println(entryptedMsg);

		String decryptedMsg = detrypt(entryptedMsg, key);
		System.out.println("decrypted message is below :");
		System.out.println(decryptedMsg);

		System.out.println(detryptA("wKLUBKml5ffc46nNYtcMma+NlmtpOCjp4CKZjQ1BOV8=", "sC7DrTal6QIDAQAB"));
		System.out.println(detryptA("OLX/c/iw6mSVIrsCuDHYuTtbYIA5n6XBAM4JvFOFbZc=", "A1m3UI+7KwIDAQAB"));
	}

	public static String EncryptA(String sSrc, String sKey) throws Exception {
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
