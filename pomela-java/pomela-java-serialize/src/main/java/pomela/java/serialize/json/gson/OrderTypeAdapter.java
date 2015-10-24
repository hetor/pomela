package pomela.java.serialize.json.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import pomela.java.common.entities.Order;

import java.io.IOException;

/**
 * Created by tao.he on 2015/10/23.
 */
public class OrderTypeAdapter extends TypeAdapter<Order> {
	@Override
	public void write(JsonWriter out, Order value) throws IOException {

	}

	@Override
	public Order read(JsonReader in) throws IOException {
		return null;
	}
}
