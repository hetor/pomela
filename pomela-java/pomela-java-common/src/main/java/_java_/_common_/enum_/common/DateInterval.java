package _java_._common_.enum_.common;

public enum DateInterval {
	
	OPEN {
		@Override
		public boolean isIn(int d, int left, int right) {
			return d > left && d < right;
		}
	},
	
	CLOSED_LEFT {
		@Override
		public boolean isIn(int d, int left, int right) {
			return d >= left && d < right;
		}
	},
	
	CLOSED_RIGHT {
		@Override
		public boolean isIn(int d, int left, int right) {
			return d > left && d <= right;
		}
	},
	
	CLOSED {
		@Override
		public boolean isIn(int d, int left, int right) {
			return d >= left && d <= right;
		}
	};

	public abstract boolean isIn(int d, int left, int right);
}
