package pomela.java.serialize.json.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * Created by tao.he on 2015/10/23.
 */
public class SpecificClassExclusionStrategy implements ExclusionStrategy {
	private final Class<?> excludedThisClass;

	public SpecificClassExclusionStrategy(Class<?> excludedThisClass) {
		this.excludedThisClass = excludedThisClass;
	}

	public boolean shouldSkipClass(Class<?> clazz) {
		return excludedThisClass.equals(clazz);
	}

	public boolean shouldSkipField(FieldAttributes f) {
		return excludedThisClass.equals(f.getDeclaredClass());
	}
}
