//package jsonserializer;
//
//import java.util.Collection;
//import java.util.Iterator;
//
//public class CollectionJsonSerializer extends AbstractJsonSerializer<Collection<?>> {
//
//	private CollectionJsonSerializer() {}
//
//	private static CollectionJsonSerializer instance = new CollectionJsonSerializer();
//
//	public static CollectionJsonSerializer getInstance() {
//		return instance;
//	}
//
//	@Override
//	public boolean support(Object target) {
//		return target instanceof Collection;
//	}
//
//	@Override
//	protected String doSerialize(Collection<?> coll) {
//		if (coll == null || coll.size() == 0) {
//			return "[]";
//		}
//		StringBuilder json = new StringBuilder();
//		IJsonSerializer serializer = JsonSerializers.getJsonSerializer(coll.iterator().next());
//		Iterator<?> itr = coll.iterator();
//		while (itr.hasNext()) {
//			json.append(serializer.serialize(itr.next())).append(',');
//		}
//		json.setLength(json.length() - 1);
//		return '[' + json.toString() + ']';
//	}
//
//}
