package pomela.java.serialize.json.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * Created by tao.he on 2015/10/23.
 */
public class AnnotationDeserializeExclusionStrategy implements ExclusionStrategy{
	public boolean shouldSkipClass(Class<?> clazz) {
		return clazz.getAnnotation(SerializeIgnore.class) != null;
	}

	public boolean shouldSkipField(FieldAttributes f) {
		return f.getAnnotation(SerializeIgnore.class) != null;
	}
}
