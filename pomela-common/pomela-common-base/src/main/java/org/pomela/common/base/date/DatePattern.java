package org.pomela.common.base.date;

/**
 * Created by hetor on 15/10/18.
 */
public enum DatePattern {
    PATTERN_1("dd/MM/yyyy:HH:mm:ss"),
    PATTERN_2("dd/MMM/yyyy:HH:mm:ss"),
    PATTERN_3("dd/MM/yyyy"),
    PATTERN_4("yyyyMMdd"),
    PATTERN_5("yyyyMMddHHmmss"),
    PATTERN_6("yyyy-MM-dd"),
    PATTERN_7("yyyy-MM-dd HH:mm:ss"),

    ;

    private String desc;

    DatePattern(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
