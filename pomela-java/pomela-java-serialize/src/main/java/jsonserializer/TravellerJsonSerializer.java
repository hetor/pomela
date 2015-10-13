//package jsonserializer;
//
//import com.travelzen.framework.core.util.TZUtil;
//import com.travelzen.tops.front.common.utils.EnumMessageUtils;
//import com.travelzen.tops.front.common.utils.FrontUtils;
//import com.travelzen.tops.member.common.vo.IdentityPaper;
//import com.travelzen.tops.member.common.vo.Traveller;
//import net.sf.json.JSONObject;
//
//import static com.travelzen.tops.front.common.jsonserializer.JsonSerializers.nullAsBlank;
//
//public class TravellerJsonSerializer extends AbstractJsonSerializer<Traveller> {
//
//	@Override
//	public boolean support(Object target) {
//		return target instanceof Traveller;
//	}
//
//	@Override
//	protected String doSerialize(Traveller target) {
//		JSONObject jso = new JSONObject();
//		nullAsBlank(jso, "key", target.getKey() == null ? "" : target.getKey());
//		nullAsBlank(jso, "name", target.getName() == null ? "" : target.getName());
//		nullAsBlank(jso, "enName", target.getEnName() == null ? "" : target.getEnName());
//		nullAsBlank(jso, "type", target.getType() == null ? "" : target.getType());
//		nullAsBlank(jso, "typeLabel", EnumMessageUtils.getMessage(target.getType()));
//		nullAsBlank(jso, "birthday", FrontUtils.formatDateTime(target.getBirthday()));
//		nullAsBlank(jso, "contact", target.getContact() == null ? "" : target.getContact());
//		nullAsBlank(jso, "customerKey", target.getCustomerKey() == null ? "" : target.getCustomerKey());
//		nullAsBlank(jso, "deleted", target.getDeleted());
//		nullAsBlank(jso, "gender", target.getGender() == null ? "" : target.getGender());
//		nullAsBlank(jso, "genderLabel", EnumMessageUtils.getMessage(target.getGender()));
//		nullAsBlank(jso, "nationality", target.getNationality() == null ? "" : target.getNationality());
//		nullAsBlank(jso, "remark", target.getRemark() == null ? "" : target.getRemark());
//		nullAsBlank(jso, "papers", handleIdentityPapers(target));
//		return jso.toString();
//	}
//
//	private String handleIdentityPapers(Traveller target) {
//		StringBuilder json = new StringBuilder("[");
//		if (!TZUtil.isEmpty(target.getPapers())) {
//			for (IdentityPaper ip : target.getPapers()) {
//				json.append("{");
//				json.append("\"type\":\"").append(ip.getType()).append("\"").append(',');
//				json.append("\"typeLabel\":\"").append(EnumMessageUtils.getMessage(ip.getType())).append("\"").append(',');
//				json.append("\"number\":\"").append(ip.getNumber()).append("\"").append(',');
//				json.append("\"deadline\":\"").append(FrontUtils.formatDateTime(ip.getDeadline())).append("\"").append(',');
//				json.append("},");
//			}
//			json.setLength(json.length() - 1);
//		}
//		return json.append("]").toString();
//	}
//
//	private static TravellerJsonSerializer instance = new TravellerJsonSerializer();
//
//	public static IJsonSerializer getInstance() {
//		return instance;
//	}
//
//}
