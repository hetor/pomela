package pomela.java.serialize.json.gson;

import com.google.gson.annotations.JsonAdapter;
import pomela.java.common.entities.Order;

/**
 * Created by tao.he on 2015/10/24.
 *
 * An annotation that indicates the Gson TypeAdapter to use with a class or field.
 *
 * Since User class specified UserJsonAdapter.class in @JsonAdapter annotation,
 * it will automatically be invoked to serialize/deserialize User instances.
 *
 * It's possible to specify different type adapters on a field, that field's type, and in the GsonBuilder.
 * Field annotations take precedence over GsonBuilder-registered type adapters, which in turn take precedence over annotated types.
 *
 * The class referenced by this annotation must be either a TypeAdapter or a TypeAdapterFactory.
 * Using the factory interface makes it possible to delegate to the enclosing Gson instance.
 */
public class AnnotationJsonAdapterDemo {
	@JsonAdapter(OrderTypeAdapter.class)
	public class User {

		public final String firstName;
		public final String lastName;

		@JsonAdapter(OrderTypeAdapter.class)
		public final Order order;

		private User(String firstName, String lastName, Order order) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.order = order;
		}
	}
}
