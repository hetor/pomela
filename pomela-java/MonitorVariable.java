package com.netease.haitao.statistics.pricing.costEstimate;

/**
 * Created by tao.he on 2016/3/3.
 */
public enum MonitorVariable implements LogAllowable {
	TRADE_CHECK_SYSTEM_PAID_INFO_EMPTY {
		@Override
		public String content() {
			return name();
		}
	}
}
