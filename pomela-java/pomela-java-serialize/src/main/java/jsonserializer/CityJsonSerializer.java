package jsonserializer;

import com.travelzen.tops.front.common.utils.FrontUtils;
import com.travelzen.tops.member.common.vo.City;
import net.sf.json.JSONObject;

import static com.travelzen.tops.front.common.jsonserializer.JsonSerializers.nullAsBlank;

public class CityJsonSerializer extends AbstractJsonSerializer<City> {

	private CityJsonSerializer() {}

	private static CityJsonSerializer instance = new CityJsonSerializer();

	public static CityJsonSerializer getInstance() {
		return instance;
	}

	@Override
	public String doSerialize(City city) {

		JSONObject jso = new JSONObject();
		nullAsBlank(jso, "key", city.getKey());
		nullAsBlank(jso, "code", city.getCode());
		nullAsBlank(jso, "countryIsoCode", city.getCountryIsoCode());
		nullAsBlank(jso, "provinceCode", city.getProvinceCode());
		nullAsBlank(jso, "createTime", FrontUtils.formatDateTime(city.getCreateTime()));
		nullAsBlank(jso, "creator", city.getCreator());
		nullAsBlank(jso, "modifier", city.getModifier());
		nullAsBlank(jso, "name", city.getName());
		nullAsBlank(jso, "nameCn", city.getNameCn());
		nullAsBlank(jso, "updateTime", FrontUtils.formatDateTime(city.getUpdateTime()));
		nullAsBlank(jso, "regionCode", city.getRegionCode());

		return jso.toString();
	}

	@Override
	public boolean support(Object target) {
		return target instanceof City;
	}

}
