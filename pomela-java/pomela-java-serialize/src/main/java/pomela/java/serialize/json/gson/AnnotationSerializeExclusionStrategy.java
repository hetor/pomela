package pomela.java.serialize.json.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * Created by tao.he on 2015/10/23.
 */
public class AnnotationSerializeExclusionStrategy implements ExclusionStrategy {
	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		return f.getAnnotation(DeserializeIgnore.class) != null;
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		return clazz.getAnnotation(DeserializeIgnore.class) != null;
	}
}
