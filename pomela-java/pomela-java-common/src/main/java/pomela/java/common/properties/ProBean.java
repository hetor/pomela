package pomela.java.common.properties;

import java.math.BigDecimal;

/**
 * Created by tao.he on 2015/9/25.
 */
public class ProBean {
	private BigDecimal bigDec;
	private Long lon;
	private Double dou;
	private Float flo;
	private Integer inte;
	private String str;
	private String stri;

	public BigDecimal getBigDec() {
		return bigDec;
	}

	public void setBigDec(BigDecimal bigDec) {
		this.bigDec = bigDec;
	}

	public Long getLon() {
		return lon;
	}

	public void setLon(Long lon) {
		this.lon = lon;
	}

	public Double getDou() {
		return dou;
	}

	public void setDou(Double dou) {
		this.dou = dou;
	}

	public Float getFlo() {
		return flo;
	}

	public void setFlo(Float flo) {
		this.flo = flo;
	}

	public Integer getInte() {
		return inte;
	}

	public void setInte(Integer inte) {
		this.inte = inte;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getStri() {
		return stri;
	}

	public void setStri(String stri) {
		this.stri = stri;
	}
}