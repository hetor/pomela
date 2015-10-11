package jsonserializer;

import com.google.gson.Gson;
import com.travelzen.framework.core.util.TZUtil;
import com.travelzen.tops.front.common.utils.EnumMessageUtils;
import com.travelzen.tops.front.common.utils.FrontUtils;
import com.travelzen.tops.member.authorization.common.TopsSecurityUtils;
import com.travelzen.tops.member.common.MemberConstants;
import com.travelzen.tops.member.common.enums.AccountType;
import com.travelzen.tops.member.common.enums.CustomerType;
import com.travelzen.tops.member.common.enums.VOExtraKey;
import com.travelzen.tops.member.common.vo.Customer;
import com.travelzen.tops.member.common.vo.User;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.travelzen.tops.front.common.jsonserializer.JsonSerializers.nullAsBlank;

public class CustomerJsonSerializer extends AbstractJsonSerializer<Customer> {

	private static Gson gson = new Gson();

	private CustomerJsonSerializer() {}

	private static CustomerJsonSerializer instance = new CustomerJsonSerializer();

	public static CustomerJsonSerializer getInstance() {
		return instance;
	}

	@Override
	public String doSerialize(Customer cus) {
		User curUsr = TopsSecurityUtils.getUserFromSession().getUserData();
		String curUsrId = curUsr == null ? null : curUsr.getKey();

		JSONObject jso = new JSONObject();
		nullAsBlank(jso, "key", cus.getKey());
		nullAsBlank(jso, "fullName", cus.getName());
		nullAsBlank(jso, "shortName", cus.getShortName());
		nullAsBlank(jso, "customerType", EnumMessageUtils.getMessage(cus.getType()));
		nullAsBlank(jso, "companyType", cus.getCompanyType());
		nullAsBlank(jso, "companyTypeLabel", EnumMessageUtils.getMessage(cus.getCompanyType()));
		nullAsBlank(jso, "purchaserType", cus.getPurchaserType());
		nullAsBlank(jso, "purchaserTypeLabel", cus.getExtra("purchaserTypeLabel"));
		nullAsBlank(jso, "status", cus.getStatus());
		nullAsBlank(jso, "statusLabel", EnumMessageUtils.getMessage(cus.getStatus()));
		nullAsBlank(jso, "property", cus.getProperty());
		nullAsBlank(jso, "auditStatus", cus.getAuditStatus());
		nullAsBlank(jso, "auditStatusLabel", EnumMessageUtils.getMessage(cus.getAuditStatus()));
		nullAsBlank(jso, "distributerAuditStatus", cus.getDistributerAuditStatus());
		nullAsBlank(jso, "distributerAuditStatusLabel", EnumMessageUtils.getMessage(cus.getDistributerAuditStatus()));
		nullAsBlank(jso, "createTime", FrontUtils.formatDateTime(cus.getCreateTime()));
		nullAsBlank(jso, "distributerCreateTime", FrontUtils.formatDateTime(cus.getDistributerCreateTime()));
		//TODO
		jso.accumulate("customerGroupKeys", gson.toJson(cus.getCustomerGroups()));
		//添加协调客户组key
		jso.accumulate("coordinateGroupKeys", handleCoordinateGroups(cus));

		nullAsBlank(jso, "customerManager", cus.getCustomerManagerId());
		nullAsBlank(jso, "region", cus.getRegion());
		nullAsBlank(jso, "regionLabel", cus.getExtra("region_label"));
//		jso.accumulate("purchaserAuditable", cus.getAuditorId() != null ? true : false);
//		jso.accumulate("disAuditable", cus.getDistributerAuditorId() != null ? true : false);
		nullAsBlank(jso, "distributeCreateDate", FrontUtils.formatDateTime(cus.getDistributerCreateTime()));
		if ((cus.getType() == CustomerType.COMPANY)
				&& cus.getCompanyInfo() != null && cus.getCompanyInfo().getBusinessContact() != null) {
			nullAsBlank(jso, "contactTel", cus.getCompanyInfo().getBusinessContact().getTel());
			nullAsBlank(jso, "contactMobil", cus.getCompanyInfo().getBusinessContact().getPhone());
			nullAsBlank(jso, "contactName", cus.getCompanyInfo().getBusinessContact().getName());
		} else if (cus.getType() == CustomerType.INDIVIDUAL && cus.getPersonalInfo() != null && cus.getPersonalInfo().getPersonalContact() != null) {
			nullAsBlank(jso, "contactTel", cus.getPersonalInfo().getPersonalContact().getTel());
			nullAsBlank(jso, "contactMobil", cus.getPersonalInfo().getPersonalContact().getPhone());
			nullAsBlank(jso, "contactName", cus.getPersonalInfo().getPersonalContact().getName());
		} else {
			jso.accumulate("contactTel", "");
			jso.accumulate("contactMobil", "");
			jso.accumulate("contactName", "");
		}
		nullAsBlank(jso, "customerGroupNames", serializeCustomerGroupNames(cus, false));
		//添加协调客户组名称
		nullAsBlank(jso, "coordinateGroupNames", serializeCustomerGroupNames(cus, true));

		nullAsBlank(jso, "officeNos", CollectionJsonSerializer.getInstance().serialize(cus.getOfficeNos()));
		nullAsBlank(jso, "contractTimeCeil", FrontUtils.formatDateTime(cus.getCooperatorContractsDeadline()));   // 协议到期日
		nullAsBlank(jso, "cooperatorContractsDeadline", FrontUtils.formatDateTime(cus.getCooperatorContractsDeadline()));   // 协议到期日

		jso.accumulate("hasTripleContract", !TZUtil.isEmpty(cus.getTripleContracts()) ? "有" : "无");  // 是否有三方协议
		jso.accumulate("auditable", (cus.getAuditorId() == null || (cus.getAuditorId().equals(curUsrId))) ? "true" : "false");
		jso.accumulate("purchaserAuditable", (cus.getAuditorId() == null || (cus.getAuditorId().equals(curUsrId))) ? "true" : "false");
		jso.accumulate("disAuditable", (cus.getDistributerAuditorId() == null || (cus.getDistributerAuditorId().equals(curUsrId))) ? "true" : "false");

		// 财务信息
		nullAsBlank(jso, "accountType", cus.getAccountType());
		nullAsBlank(jso, "accountTypeLabel", EnumMessageUtils.getMessage(cus.getAccountType()));
		jso.accumulate("creditPeriod", cus.getAccountType() == AccountType.CREDIT ? cus.getCreditPeriod() : "");
		jso.accumulate("creditPeriodLabel", cus.getAccountType() == AccountType.CREDIT ? cus.getExtra(cus.getCreditPeriod()) : "");
		jso.accumulate("creditAmount", cus.getAccountType() == AccountType.CREDIT ? cus.getCreditAmount() : "");

		// 金额
		String rawAmount = cus.getExtra(VOExtraKey.MONETARY_AMOUNT.key());
		if (rawAmount != null) {
			long amount = Long.parseLong(rawAmount);
			if (amount == 0) {
				jso.accumulate("totalMoney", "0");
			} else if (amount % 100 == 0) {
				jso.accumulate("totalMoney", String.valueOf(amount / 100));
			} else {
				jso.accumulate("totalMoney", String.valueOf(amount / 100.0));
			}
		} else {
			jso.accumulate("totalMoney", "0");
		}

		jso.accumulate("topUserKey", cus.getTopUserKey());
		jso.accumulate("topUserName", cus.getTopUserName());
		jso.accumulate("modifierName", cus.getExtra(cus.getModifier()));
		if (cus.getType() == CustomerType.COMPANY) {
			if (cus.getCompanyInfo() == null) {
				jso.accumulate("responsibleName", "");
			} else if (cus.getCompanyInfo().getResponsibleInfo() == null) {
				jso.accumulate("responsibleName", "");
			} else {
				jso.accumulate("responsibleName", cus.getCompanyInfo().getResponsibleInfo().getName());
			}
		} else if (cus.getType() == CustomerType.INDIVIDUAL) {
			jso.accumulate("responsibleName", "");
		}

		jso.accumulate("creationSourceLabel", EnumMessageUtils.getMessage(cus.getCreationSource()));
//		nullAsBlank(jso, "lastPurchaseTime", FrontUtils.formatDateTime(cus.getLastPurchaseTime()));

		nullAsBlank(jso, "lastPurchaseTime", cus.getExtra("lastPurchaseTime"));
		nullAsBlank(jso, "lastTradeDate", cus.getExtra("lastTradeDate"));
		nullAsBlank(jso, "ticketsCount", cus.getExtra("ticketsCount"));
		nullAsBlank(jso, "transAmount", cus.getExtra("transAmount"));

		nullAsBlank(jso, "salesMan", cus.getSalesMan());
		nullAsBlank(jso, "salesManName", cus.getExtra(cus.getSalesMan()));
		//获取下级分销商数量
		nullAsBlank(jso, "countDistributor", cus.getExtra("countDistributor"));
		//获取上级名称
		nullAsBlank(jso, "parentPurchaserName", cus.getExtra("parentPurchaserName"));
		//获取最近登录时间
		nullAsBlank(jso, "lastLoginTime", cus.getExtra("lastLoginTime"));
		//获取最近登录IP地址
		nullAsBlank(jso, "lastLoginIP", cus.getExtra("lastLoginIP"));
		//获取用户积分值
		nullAsBlank(jso, "point", cus.getExtra("point"));

		return jso.toString();
	}
	//获取协调客户组keys
	private String handleCoordinateGroups(Customer cus) {
		if (cus.getCoordinateGroups() == null || TZUtil.isEmpty(cus.getCoordinateGroups().get(MemberConstants.DOMESTIC_FLIGHT))) {
			return "";
		}else {
			return JsonSerializers.serializeCollection(cus.getCoordinateGroups().get(MemberConstants.DOMESTIC_FLIGHT));
		}
	}

	private String serializeCustomerGroupNames(Customer cus, boolean isCoordinate) {
		StringBuilder cgNames = new StringBuilder("{");
		if (cus.getExtra() != null) {
			List<String> keys = new ArrayList<String>();
			if (isCoordinate) {
				//获取协调客户组key
				if (cus.getCoordinateGroups() != null && !TZUtil.isEmpty(cus.getCoordinateGroups().get(MemberConstants.DOMESTIC_FLIGHT))) {
					keys = cus.getCoordinateGroups().get(MemberConstants.DOMESTIC_FLIGHT);
				}
			}else {
				//获取普通客户组key
				for (String str : cus.getCustomerGroups().values()) {
					keys.add(str);
				}
			}
			//从冗余字段中取值（所有的客户组都放在冗余字段中）
			for (String key : keys) {
				cgNames.append('"').append(key).append('"')
						.append(":")
						.append('"').append(cus.getExtra(key)).append('"')
						.append(",");
			}
			if (cgNames.length() > 1) {
				cgNames.setLength(cgNames.length() - 1);
			}
		}

		cgNames.append("}");
		return cgNames.toString();
	}

	@Override
	public boolean support(Object target) {
		return target instanceof Customer;
	}

}
