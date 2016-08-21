package org.pomela.common.base.signature;

/**
 * Created by hetor on 16/8/18.
 */
public class HexUtils {
	private static final char[] HEX_LOOKUP_STRING = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c',
			'd', 'e', 'f' };

	/**
	 * 将字节数组转换为16进制字符串的形式.
	 */
	public static String bytesToHexStr(byte[] bcd) {
		StringBuilder s = new StringBuilder(bcd.length * 2);

		for (byte aBcd : bcd) {
			s.append(HEX_LOOKUP_STRING[(aBcd >>> 4) & 0x0f]);
			s.append(HEX_LOOKUP_STRING[aBcd & 0x0f]);
		}

		return s.toString();
	}

	/**
	 * 将16进制字符串还原为字节数组.
	 */
	public static byte[] hexStrToBytes(String s) {
		byte[] bytes;

		bytes = new byte[s.length() / 2];

		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2), 16);
		}

		return bytes;
	}
}
