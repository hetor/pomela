package jsonserializer;

import com.travelzen.tops.front.common.utils.FrontUtils;
import com.travelzen.tops.member.common.vo.Province;
import net.sf.json.JSONObject;

import static com.travelzen.tops.front.common.jsonserializer.JsonSerializers.nullAsBlank;

public class ProvinceJsonSerializer extends AbstractJsonSerializer<Province> {

	private ProvinceJsonSerializer() {}

	private static ProvinceJsonSerializer instance = new ProvinceJsonSerializer();

	public static ProvinceJsonSerializer getInstance() {
		return instance;
	}

	@Override
	public String doSerialize(Province pro) {

		JSONObject jso = new JSONObject();
		nullAsBlank(jso, "key", pro.getKey());
		nullAsBlank(jso, "code", pro.getCode());
		nullAsBlank(jso, "countryIsoCode", pro.getCountryIsoCode());
		nullAsBlank(jso, "createTime", FrontUtils.formatDateTime(pro.getCreateTime()));
		nullAsBlank(jso, "creator", pro.getCreator());
		nullAsBlank(jso, "modifier", pro.getModifier());
		nullAsBlank(jso, "name", pro.getName());
		nullAsBlank(jso, "nameCn", pro.getNameCn());
		nullAsBlank(jso, "regionCode", pro.getRegionCode());
		nullAsBlank(jso, "updateTime", FrontUtils.formatDateTime(pro.getUpdateTime()));

		return jso.toString();
	}

	@Override
	public boolean support(Object target) {
		return target instanceof Province;
	}

}
