package pomela.java.common.date;

/**
 * Created by hetor on 15/10/18.
 */
public enum DatePattern {
    PATTERN_1("dd/MM/yyyy:HH:mm:ss"),
    PATTERN_2("dd/MMM/yyyy:HH:mm:ss"),
    PATTERN_3("dd/MM/yyyy"),
    PATTERN_4("yyyyMMdd"),
    PATTERN_5("yyyyMMddHHmmss"),
    PATTERN_6("yyyy-MM-dd");

    private String desc;

    DatePattern(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
