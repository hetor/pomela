package org.pomela.java.common.holders;

/**
 * thread safe
 *
 * StringBuilder÷ÿ”√
 *
 * Created by tao.he on 2015/12/9.
 */
public class StringBuilderHolder {
	private ThreadLocal<StringBuilder> threadLocal = new ThreadLocal();

	public StringBuilderHolder() {
		threadLocal.set(new StringBuilder(0));
	}

	public StringBuilder getStringBuilder() {
		StringBuilder sb = threadLocal.get();
		sb.setLength(0);
		return sb;
	}

	public static void main(String[] args) {
		StringBuilderHolder holder = new StringBuilderHolder();

		StringBuilder sb = holder.getStringBuilder();
		sb.append("test").append("aaa").append("bbb");
		System.out.println(sb.toString());

		StringBuilder sb1 = holder.getStringBuilder();
		sb1.append("test1").append("ccc");
		System.out.println(sb1.toString());
	}
}