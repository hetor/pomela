//package jsonserializer;
//
//import com.travelzen.framework.core.util.TZUtil;
//import com.travelzen.tops.front.common.utils.EnumMessageUtils;
//import com.travelzen.tops.front.common.utils.FrontUtils;
//import com.travelzen.tops.member.common.vo.User;
//import net.sf.json.JSONObject;
//import org.apache.commons.lang3.StringUtils;
//
//import java.util.Collection;
//
//import static com.travelzen.tops.front.common.jsonserializer.JsonSerializers.nullAsBlank;
//
//public class UserJsonSerializer extends AbstractJsonSerializer<User> {
//
//	private UserJsonSerializer() {}
//
//	private static UserJsonSerializer instance = new UserJsonSerializer();
//
//	public static UserJsonSerializer getInstance() {
//		return instance;
//	}
//
//	@Override
//	public boolean support(Object target) {
//		return target instanceof User;
//	}
//
//	@Override
//	protected String doSerialize(User target) {
//		JSONObject jso = new JSONObject();
//		nullAsBlank(jso, "key", target.getKey());
//		nullAsBlank(jso, "username", target.getUsername());
//		nullAsBlank(jso, "realName", target.getRealName());
//		nullAsBlank(jso, "type", EnumMessageUtils.getMessage(target.getOperatorUserType()));
//		nullAsBlank(jso, "operatorUserType", target.getOperatorUserType());
//		nullAsBlank(jso, "operatorUserTypeLabel", EnumMessageUtils.getMessage(target.getOperatorUserType()));
//		nullAsBlank(jso, "status", target.getStatus());
//		nullAsBlank(jso, "statusLabel", EnumMessageUtils.getMessage(target.getStatus()));
//		nullAsBlank(jso, "deadline", FrontUtils.formatDateTime(target.getDeadline()));
//		nullAsBlank(jso, "employeeCode", target.getEmployeeCode());
//		nullAsBlank(jso, "hasAdditionalPermission", TZUtil.isEmpty(target.getAdditionalPermissions()) ? "否" : "是");
//		nullAsBlank(jso, "additionalPermissions", handleNameCollection(target.getAdditionalPermissions()));
//		nullAsBlank(jso, "jobPosition", target.getJobPosition());
//		nullAsBlank(jso, "email", target.getEmail());
//		nullAsBlank(jso, "qq", target.getQq());
//		nullAsBlank(jso, "signInTime", FrontUtils.formatDateTime(target.getSignInTime()));
//		nullAsBlank(jso, "depKey", TZUtil.isEmpty(target.getDepartmentsKey()) ? null : target.getDepartmentsKey().get(0));
//		nullAsBlank(jso, "departments", handleNameCollection(target.getDepartmentsName()));
//		nullAsBlank(jso, "all_departments", target.getExtra("all_departments"));
//		if(target.getExtra() != null && target.getExtra("companyAndDepartment") != null) {
//			nullAsBlank(jso, "companyAndDepartment", target.getExtra("companyAndDepartment"));
//		}else {
//			nullAsBlank(jso, "companyAndDepartment", "");
//		}
//		nullAsBlank(jso, "roles", handleRoles(target));
//		nullAsBlank(jso, "rolesKey", JsonSerializers.serializeCollection(target.getRolesKey()));
//		nullAsBlank(jso, "mobilePhone", target.getMobilePhone());
//		nullAsBlank(jso, "fixedPhoneZipCode", target.getFixedPhoneZipCode());
//		nullAsBlank(jso, "fixedPhoneNumber", target.getFixedPhoneNumber());
//		nullAsBlank(jso, "fixedPhoneExtension", target.getFixedPhoneExtension());
//		String fixedPhone = "";
//		if (StringUtils.isNotBlank(target.getFixedPhoneZipCode())) {
//			fixedPhone += target.getFixedPhoneZipCode() + '-';
//		}
//		if (StringUtils.isNotBlank(target.getFixedPhoneNumber())) {
//			fixedPhone += target.getFixedPhoneNumber();
//		}
//		if (StringUtils.isNotBlank(target.getFixedPhoneExtension())) {
//			fixedPhone += '-' + target.getFixedPhoneExtension();
//		}
//		jso.accumulate("fixedPhone", fixedPhone);
//		// 联系方式
//		if (StringUtils.isNotBlank(target.getMobilePhone())) {
//			nullAsBlank(jso, "contact", target.getMobilePhone());
//		} else if (StringUtils.isNotBlank(fixedPhone)) {
//			jso.accumulate("contact", fixedPhone);
//		} else {
//			nullAsBlank(jso, "contact", "");
//		}
//		nullAsBlank(jso, "faxZipCode", target.getFaxZipCode());
//		nullAsBlank(jso, "faxNumber", target.getFaxNumber());
//		nullAsBlank(jso, "remark", target.getRemark());
//		if(target.getExtra() != null && target.getExtra("officeNos") != null) {
//			nullAsBlank(jso, "officeNos", target.getExtra("officeNos"));
//		}else {
//			nullAsBlank(jso, "officeNos", "");
//		}
//		return jso.toString();
//	}
//
//	private String handleNameCollection(Collection<String> names) {
//		if (TZUtil.isEmpty(names)) {
//			return "";
//		}
//		StringBuilder sbd = new StringBuilder();
//		for (String name : names) {
//			sbd.append(name).append(',');
//		}
//		sbd.setLength(sbd.length() - 1);
//		return sbd.toString();
//	}
//	private String handleRoles(User target) {
//		if (TZUtil.isEmpty(target.getRolesKey())) {
//			return "";
//		}
//		StringBuilder sbd = new StringBuilder();
//		for (String rolekey : target.getRolesKey()) {
//			sbd.append(target.getExtra(rolekey)).append(',');
//		}
//		sbd.setLength(sbd.length() - 1);
//		return sbd.toString();
//	}
//
//}
