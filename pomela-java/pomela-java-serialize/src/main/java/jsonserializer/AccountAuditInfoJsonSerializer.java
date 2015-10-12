package jsonserializer;

import com.travelzen.framework.core.util.TZUtil;
import com.travelzen.tops.front.common.utils.EnumMessageUtils;
import com.travelzen.tops.member.common.vo.AccountAuditInfo;
import net.sf.json.JSONObject;

import static com.travelzen.tops.front.common.jsonserializer.JsonSerializers.nullAsBlank;

public class AccountAuditInfoJsonSerializer extends AbstractJsonSerializer<AccountAuditInfo> {

	private AccountAuditInfoJsonSerializer() {
	}

	private static AccountAuditInfoJsonSerializer instance = new AccountAuditInfoJsonSerializer();

	public static AccountAuditInfoJsonSerializer getInstance() {
		return instance;
	}

	@Override
	public boolean support(Object target) {
		return target instanceof AccountAuditInfo;
	}

	@Override
	protected String doSerialize(AccountAuditInfo info) {
		JSONObject jso = new JSONObject();
		nullAsBlank(jso, "key", info.getKey());
		nullAsBlank(jso, "netpayAccountId", info.getNetpayAccountId());
		nullAsBlank(jso, "customerId", info.getCustomerId());
		nullAsBlank(jso, "customerFullName", info.getCustomerFullName());
		nullAsBlank(jso, "customerShortName", info.getCustomerShortName());
		nullAsBlank(jso, "paymentOrg", info.getPaymentOrg());
		nullAsBlank(jso, "partnerAccountName", info.getPartnerAccountName());
		nullAsBlank(jso, "partnerAccount", info.getPartnerAccount());
		nullAsBlank(jso, "isSignPayment", info.isSignPayment() ? "是" : "否");
		nullAsBlank(jso, "isSignRefund", info.isSignRefund() ? "是" : "否");
		nullAsBlank(jso, "summary", info.getSummary());
		nullAsBlank(jso, "accountAuditStatus", info.getAccountAuditStatus());
		nullAsBlank(jso, "accountAuditStatusLabel", EnumMessageUtils.getMessage(info.getAccountAuditStatus()));
		nullAsBlank(jso, "submitTime", TZUtil.isEmpty(info.getSubmitTime()) ? "" : info.getSubmitTime().toString("yyyy-MM-dd HH:mm:ss"));
		nullAsBlank(jso, "auditTime", TZUtil.isEmpty(info.getAuditTime()) ? "" : info.getAuditTime().toString("yyyy-MM-dd HH:mm:ss"));
		nullAsBlank(jso, "auditorId", info.getAuditorId());
		nullAsBlank(jso, "auditorName", info.getAuditorName());
		return jso.toString();
	}

}
