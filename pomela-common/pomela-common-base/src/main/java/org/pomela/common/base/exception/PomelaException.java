package org.pomela.common.base.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hetor on 16/9/22.
 * 评论服务统一出口异常基类
 * @since 20160922
 */
public class PomelaException extends Exception {

	private static final long serialVersionUID = -5128724730338168139L;

	private Code code;

	public PomelaException(Code code) {
		super();
		this.code = code;
	}

	public PomelaException(Code code, String msg) {
		super(msg);
		this.code = code;
	}

	public PomelaException(Code code, Throwable thr) {
		super(thr);
		this.code = code;
	}

	public PomelaException(Code code, String msg, Throwable thr) {
		super(msg, thr);
		this.code = code;
	}


	/**
	 * 错误码
	 */
	public enum Code {
		BASE_ERROR(-1),
		BAD_ARGUMENTS(-2);

		private final int code;

		private static final Map<Integer,Code> lookup
				= new HashMap<Integer,Code>();

		static {
			for(Code c : Code.values())
				lookup.put(c.code, c);
		}

		private Code(int code) {
			this.code = code;
		}

		public int getCode() {
			return this.code;
		}
	}

	public static class BasePomelaException extends PomelaException {
		public BasePomelaException() {
			super(Code.BASE_ERROR);
		}

		public BasePomelaException(String msg) {
			super(Code.BASE_ERROR, msg);
		}

		public BasePomelaException(Throwable thr) {
			super(Code.BASE_ERROR, thr);
		}

		public BasePomelaException(String msg, Throwable thr) {
			super(Code.BASE_ERROR, msg, thr);
		}
	}

	/**
	 * 非法参数异常
	 */
	public static class BadArgumentsPomelaException extends PomelaException {
		public BadArgumentsPomelaException() {
			super(Code.BAD_ARGUMENTS);
		}

		public BadArgumentsPomelaException(String msg) {
			super(Code.BAD_ARGUMENTS, msg);
		}

		public BadArgumentsPomelaException(Throwable thr) {
			super(Code.BAD_ARGUMENTS, thr);
		}

		public BadArgumentsPomelaException(String msg, Throwable thr) {
			super(Code.BAD_ARGUMENTS, msg, thr);
		}
	}
}
