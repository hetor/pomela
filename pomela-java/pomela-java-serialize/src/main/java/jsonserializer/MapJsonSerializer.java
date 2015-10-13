//package jsonserializer;
//
//import com.travelzen.tops.member.common.vo.OfficeNo;
//import net.sf.json.JSONObject;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Map.Entry;
//
//public class MapJsonSerializer extends AbstractJsonSerializer<Map<?, ?>> {
//
//	private MapJsonSerializer() {}
//
//	private static MapJsonSerializer instance = new MapJsonSerializer();
//
//	public static MapJsonSerializer getInstance() {
//		return instance;
//	}
//
//	@Override
//	public boolean support(Object target) {
//		return target instanceof Map;
//	}
//
//	@Override
//	protected String doSerialize(Map<?, ?> target) {
//		if (target == null || target.size() == 0) {
//			return "{}";
//		}
//		JSONObject jso = new JSONObject();
//		for (Entry<?, ?> ety : target.entrySet()) {
//			jso.accumulate(
//					ety.getKey() == null ? "null" : ety.getKey().toString(),
//					JsonSerializers.serialize(ety.getValue()));
//		}
//		return jso.toString();
//	}
//
//	public static void main(String[] args) {
//		Map<String, OfficeNo> map = new HashMap<String, OfficeNo>();
//		OfficeNo on = new OfficeNo();
//		on.setAirline("aaa");
//		on.setNo("tyui");
//		map.put(null, on);
//		System.out.println(new MapJsonSerializer().serialize(map));
//	}
//}
