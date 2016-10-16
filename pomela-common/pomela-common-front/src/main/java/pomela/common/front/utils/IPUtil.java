package pomela.common.front.utils;

import java.security.SecureRandom;


import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class IPUtil {
	/**
	 * 获取请求包里的真实IP地址(跳过前端代理)
	 *
	 * @param request
	 *            请求包对象
	 * @return ip地址
	 */
	public static String getIpAddr(HttpServletRequest request) {
		if (request == null) {
			return "";
		}

		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-From-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		// 全部都需要split。sony手机调试发现x-forwarded-for通过代理也带了两个以逗号隔开的ip
		// 如果通过了多级反向代理的话
		// 取X-Forwarded-For中第一个非unknown的有效IP字符串
		// 发现用户会伪造 X-Forwarded-For 的头，取ngnix拿到的ip
		String[] subIp = ip.split(",");
		if (subIp.length > 0) {
			ArrayUtils.reverse(subIp);
			for (String s : subIp) {
				if (!"unknown".equalsIgnoreCase(s)) {
					ip = s;
					break;
				}
			}
		}

		if (ip != null) {
			ip = ip.trim();
			return ip;
		} else {
			return "";
		}
	}

	/**
	 * Convert String Ip to Long
	 *
	 * @param ip
	 * @return
	 */
	public static long toLongIP(String ip) {
		if (StringUtils.isBlank(ip))
			return -1;

		String[] p = ip.split("\\.");
		if (p.length != 4)
			return -1;
		long[] d = new long[4];
		for (int i = 0; i < 4; i++) {
			p[i] = p[i].trim();
			if (isInteger(p[i]))
				d[i] = Long.parseLong(p[i]);
			else
				return -1;
		}
		long result = 0;
		for (int i = 0; i < 4; i++) {
			result += d[3 - i] << (8 * i);
		}
		return result;
	}

	/**
	 * Convert Long Ip to String
	 *
	 * @param ip
	 * @return
	 */
	public static String toStringIP(long ip) {
		long p1 = (ip & 4278190080L) >> 24;
		long p2 = (ip & 16711680L) >> 16;
		long p3 = (ip & 65280L) >> 8;
		long p4 = ip & 255;
		return p1 + "." + p2 + "." + p3 + "." + p4;
	}

	/**
	 * 随机一个ip
	 *
	 * @return
	 */
	public static String randomIp() {
		SecureRandom sr1 = new SecureRandom();
		return sr1.nextInt(255) + "." + sr1.nextInt(255) + "." + sr1.nextInt(255) + "." + sr1.nextInt(255);
	}

	/**
	 * 判断是否全部数字
	 *
	 * @param intStr
	 *            字符串
	 * @return 是或否
	 */
	private static boolean isInteger(String intStr) {
		return (intStr != null) && intStr.matches("\\d+");
	}

	/**
	 * convert long ip to c class
	 *
	 * @param ip
	 * @return
	 */
	public static long toClassCIP(long ip) {
		// 4294967040是 255.255.255.0的整数值
		return ip & 4294967040L;
	}

	/**
	 * convert String ip to c class
	 *
	 * @param ip
	 * @return
	 */
	public static long toClassCLongIP(String ip) {
		long longIP = IPUtil.toLongIP(ip);
		if (longIP == -1)
			return -1;
		return IPUtil.toClassCIP(longIP);
	}

	/**
	 * convert String ip to c class string Ip
	 *
	 * @param ip
	 * @return
	 */
	public static String toClassCStringIP(long ip) {
		long p1 = (ip & 4278190080L) >> 24;
		long p2 = (ip & 16711680L) >> 16;
		long p3 = (ip & 65280L) >> 8;
		return p1 + "." + p2 + "." + p3 + ".0";
	}


}
