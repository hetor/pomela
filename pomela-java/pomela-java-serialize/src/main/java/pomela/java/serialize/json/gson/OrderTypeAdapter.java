package pomela.java.serialize.json.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import pomela.java.common.entities.Order;

import java.io.IOException;

/**
 * Created by tao.he on 2015/10/23.
 *
 * http://google.github.io/gson/apidocs/com/google/gson/TypeAdapter.html
 */
public class OrderTypeAdapter extends TypeAdapter<Order> {
	@Override
	public void write(JsonWriter writer, Order value) throws IOException {
		if (value == null) {
			writer.nullValue();
			return;
		}
//		String xy = value.getX() + "," + value.getY();
//		writer.value(xy);
	}

	@Override
	public Order read(JsonReader reader) throws IOException {
		if (reader.peek() == JsonToken.NULL) {
			reader.nextNull();
			return null;
		}
		String xy = reader.nextString();
		String[] parts = xy.split(",");
		int x = Integer.parseInt(parts[0]);
		int y = Integer.parseInt(parts[1]);
		return new Order();
	}
}
