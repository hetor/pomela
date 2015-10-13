package pomela.java.common.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by tao.he on 2015/10/13.
 */
public class DateUtil {
	public enum DatePattern {
		PATTERN_1("dd/MM/yyyy:HH:mm:ss"),
		PATTERN_2("dd/MMM/yyyy:HH:mm:ss"),
		PATTERN_3("dd/MM/yyyy"),
		PATTERN_4("yyyyMMdd"),
		PATTERN_5("yyyyMMddHHmmss");

		private String desc;

		DatePattern(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}
	}

	public static String toStr(Date date) {
		return toStr(date, null);
	}

	public static String toStr(Date date, DatePattern dp) {
		return toStr(date, dp, null);
	}

	public static String toStr(Date date, DatePattern dp, Locale l) {
		return createDateFormat(dp, l).format(date);
	}

	public static Date fromStr(String dateStr) {
		return fromStr(dateStr, null);
	}

	public static Date fromStr(String dateStr, DatePattern dp) {
		return fromStr(dateStr, dp, null);
	}

	public static Date fromStr(String dateStr, DatePattern dp, Locale l) {
		try {
			return createDateFormat(dp, l).parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}


	private static DateFormat createDateFormat(DatePattern dp, Locale l) {
		if (null != dp) {
			return (null == l) ? new SimpleDateFormat(dp.getDesc()) : new SimpleDateFormat(dp.getDesc(), l);
		}
		return new SimpleDateFormat();
	}
}
