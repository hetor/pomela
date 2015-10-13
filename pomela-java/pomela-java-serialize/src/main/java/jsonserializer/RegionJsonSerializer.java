//package jsonserializer;
//
//import com.travelzen.tops.front.common.utils.FrontUtils;
//import com.travelzen.tops.member.common.vo.Region;
//import net.sf.json.JSONObject;
//
//import static com.travelzen.tops.front.common.jsonserializer.JsonSerializers.nullAsBlank;
//
//public class RegionJsonSerializer extends AbstractJsonSerializer<Region> {
//
//	private RegionJsonSerializer() {}
//
//	private static RegionJsonSerializer instance = new RegionJsonSerializer();
//
//	public static RegionJsonSerializer getInstance() {
//		return instance;
//	}
//
//	@Override
//	public String doSerialize(Region reg) {
//
//		JSONObject jso = new JSONObject();
//		nullAsBlank(jso, "key", reg.getKey());
//		nullAsBlank(jso, "code", reg.getCode());
//		nullAsBlank(jso, "countryIsoCode", reg.getCountryIsoCode());
//		nullAsBlank(jso, "companyCode", reg.getCompanyCode());
//		nullAsBlank(jso, "createTime", FrontUtils.formatDateTime(reg.getCreateTime()));
//		nullAsBlank(jso, "creator", reg.getCreator());
//		nullAsBlank(jso, "modifier", reg.getModifier());
//		nullAsBlank(jso, "name", reg.getName());
//		nullAsBlank(jso, "nameCn", reg.getNameCn());
//		nullAsBlank(jso, "updateTime", FrontUtils.formatDateTime(reg.getUpdateTime()));
//
//		return jso.toString();
//	}
//
//	@Override
//	public boolean support(Object target) {
//		return target instanceof Region;
//	}
//
//}
