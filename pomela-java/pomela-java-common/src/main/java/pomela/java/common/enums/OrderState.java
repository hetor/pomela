package pomela.java.common.enums;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tao.he on 2015/9/30.
 */
public enum OrderState {
	INIT {
		@Override
		List<OrderState> pre() {
			return Arrays.asList();
		}

		@Override
		List<OrderState> next() {
			return Arrays.asList(PAY_SUCC);
		}
	},

	PAY_SUCC {
		@Override
		List<OrderState> pre() {
			return Arrays.asList(INIT);
		}

		@Override
		List<OrderState> next() {
			return Arrays.asList();
		}
	};

	abstract List<OrderState> pre();
	abstract List<OrderState> next();


	public boolean canChangeTo(OrderState state) {
		return next().contains(state);
	}
}
