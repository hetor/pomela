package org.pomela.common.base.encrypt;

import java.security.Provider;
import java.security.Security;
import java.util.Map;

/**
 * Created by hetor on 16/8/30.
 */
public class SecurityDemo {

	/**
	 * 遍历目前环境中的安全提供者
	 */
	public static void printAllProviders() {
		for (Provider provider : Security.getProviders()) {
			// 打印当前提供者信息
			System.out.println(provider);
			// 遍历提供者Set实体
			for (Map.Entry<Object, Object> entry : provider.entrySet()) {
				// 打印提供者键值
				System.out.println("\t" + entry.getKey());
			}
		}
	}

	public static void main(String[] args) {
		SecurityDemo.printAllProviders();
	}
}

