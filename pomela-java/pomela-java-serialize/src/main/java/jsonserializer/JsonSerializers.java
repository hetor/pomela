//package jsonserializer;
//
//import org.apache.commons.lang3.StringUtils;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class JsonSerializers {
//
//	private static Map<Class<?>, IJsonSerializer> serializers = new HashMap<>();
//	private static IJsonSerializer defaultSerializer = GenericJsonSerializer.getInstance();
//	private static IJsonSerializer collectionSerializer = CollectionJsonSerializer.getInstance();
//	private static IJsonSerializer mapSerializer = MapJsonSerializer.getInstance();
//
//	static {
//		serializers.put(OfficeNo.class, OfficeNoJsonSerializer.getInstance());
//		serializers.put(Customer.class, CustomerJsonSerializer.getInstance());
//		serializers.put(User.class, UserJsonSerializer.getInstance());
//		serializers.put(Traveller.class, TravellerJsonSerializer.getInstance());
//		serializers.put(Region.class, RegionJsonSerializer.getInstance());
//		serializers.put(Province.class, ProvinceJsonSerializer.getInstance());
//		serializers.put(City.class, CityJsonSerializer.getInstance());
//		serializers.put(District.class, DistrictJsonSerializer.getInstance());
//		serializers.put(AccountAuditInfo.class, AccountAuditInfoJsonSerializer.getInstance());
//	}
//
//	public static IJsonSerializer getJsonSerializer(Object target) {
//		if (target == null) {
//			return null;
//		}
//		if (collectionSerializer.support(target)) {
//			return collectionSerializer;
//		}
//		if (mapSerializer.support(target)) {
//			return mapSerializer;
//		}
//		IJsonSerializer result = serializers.get(target.getClass());
//		if (result == null) {
//			for (IJsonSerializer js : serializers.values()) {
//				if (js.support(target)) {
//					result = js;
//					break;
//				}
//			}
//		}
//		if (result == null) {
//			result = defaultSerializer;
//		}
//		return result;
//	}
//
//	public static String serialize(Object target) {
//		if (target == null) {
//			return "";
//		}
//		IJsonSerializer serializer = getJsonSerializer(target);
//		if (serializer != null) {
//			return serializer.serialize(target);
//		} else {
//			return defaultSerializer.serialize(target);
//		}
//	}
//
//	public static String serializeCollection(Object target) {
//		if (target == null) {
//			return "[]";
//		}
//		return serialize(target);
//	}
//
//	public static String serializeMap(Object target) {
//		if (target == null) {
//			return "{}";
//		}
//		return serialize(target);
//	}
//
//	public static String wrapPageData(String data, long totalCount, String callback) {
//		String result = "{ \"pageData\": " + data + ",\"totalCount\": " + totalCount	+ "}";
//		if (StringUtils.isBlank(callback)) {
//			return result;
//		} else {
//			return callback + "(" + result + ")";
//		}
//	}
//
//	static void nullAsBlank(JSONObject jso, String key, Object value) {
//		jso.accumulate(key, value == null ? "" : value);
//	}
//
//}
