//package jsonserializer;
//
//import com.travelzen.tops.member.common.vo.OfficeNo;
//
//public class OfficeNoJsonSerializer extends AbstractJsonSerializer<OfficeNo> {
//
//	private OfficeNoJsonSerializer() {}
//
//	private static OfficeNoJsonSerializer instance = new OfficeNoJsonSerializer();
//
//	public static OfficeNoJsonSerializer getInstance() {
//		return instance;
//	}
//
//	@Override
//	public String doSerialize(OfficeNo on) {
//		StringBuilder sbd = new StringBuilder();
//		appendField(sbd, "airline", on.getAirline());
//		appendField(sbd, "memo", on.getMemo());
//		appendField(sbd, "no", on.getNo());
//		appendField(sbd, "agentCode", on.getAgentCode());
//		appendField(sbd, "issuedBy", on.getIssuedBy());
//		if (sbd.length() > 0) {
//			sbd.setLength(sbd.length() - 1);
//		}
//		return sbd.insert(0, '{').append("}").toString();
//	}
//
//	private void appendField(StringBuilder sbd, String field, Object value) {
//		sbd.append("\"").append(field).append("\":\"")
//			.append(value == null ? "" : value).append("\"")
//			.append(",");
//	}
//
//	@Override
//	public boolean support(Object target) {
//		return target instanceof OfficeNo;
//	}
//
//	public static void main(String[] args) {
//		OfficeNo on = new OfficeNo();
//		on.setAirline("aaaa");
//		on.setMemo("mmmmmm");
//		on.setNo("nnnnnn");
//		System.out.println(new OfficeNoJsonSerializer().serialize(on));
//	}
//
//}
