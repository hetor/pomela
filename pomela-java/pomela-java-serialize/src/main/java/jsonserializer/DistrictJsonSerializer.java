//package jsonserializer;
//
//import com.travelzen.tops.front.common.utils.FrontUtils;
//import com.travelzen.tops.member.common.vo.District;
//import net.sf.json.JSONObject;
//
//import static com.travelzen.tops.front.common.jsonserializer.JsonSerializers.nullAsBlank;
//
//public class DistrictJsonSerializer extends AbstractJsonSerializer<District> {
//
//	private DistrictJsonSerializer() {}
//
//	private static DistrictJsonSerializer instance = new DistrictJsonSerializer();
//
//	public static DistrictJsonSerializer getInstance() {
//		return instance;
//	}
//
//	@Override
//	public String doSerialize(District dis) {
//
//		JSONObject jso = new JSONObject();
//		nullAsBlank(jso, "key", dis.getKey());
//		nullAsBlank(jso, "cityIsoCode", dis.getCityIsoCode());
//		nullAsBlank(jso, "createTime", FrontUtils.formatDateTime(dis.getCreateTime()));
//		nullAsBlank(jso, "creator", dis.getCreator());
//		nullAsBlank(jso, "modifier", dis.getModifier());
//		nullAsBlank(jso, "cityName", dis.getCityName());
//		nullAsBlank(jso, "nameCn", dis.getNameCn());
//		nullAsBlank(jso, "updateTime", FrontUtils.formatDateTime(dis.getUpdateTime()));
//
//		return jso.toString();
//	}
//
//	@Override
//	public boolean support(Object target) {
//		return target instanceof District;
//	}
//
//}
