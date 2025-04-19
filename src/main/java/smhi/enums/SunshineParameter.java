package smhi.enums;

import smhi.interfaces.Parameter;

public enum SunshineParameter implements Parameter {
    /**
     * 116 CIE UV irradiance [mW/m²] 1999-01-01 - present
     */
    CIE_UV_IRRADIANCE("116", "1999-01-01", "CIE UV irradiance [mW/m²]"),
    /*
     * 117 Global irradiance [W/m²] 1999-01-01 - present
     */
    GLOBAL_IRRADIANCE("117", "1999-01-01", "Global irradiance [W/m²]"),
    /**
     * 118 Direct normal irradiance [W/m²] 1999-01-01 - present
     */
    DIRECT_NORMAL_IRRADIANCE("118", "1999-01-01", "Direct normal irradiance [W/m²]"),
    /*
     * PAR [W/m²] 1999-01-01 - present
     */
    PAR("120", "1999-01-01", "PAR [W/m²]"),
    /**
     * 121 Direct horizontal irradiance [W/m²] 2017-04-18 - present
     */
    DIRECT_HORIZONTAL_IRRADIANCE("121", "2017-04-18", "Direct horizontal irradiance [W/m²]"),
    /**
     * 122 Diffuse irradiance [W/m²] 2017-04-18 - present
     */
    DIFFUSE_IRRADIANCE("122", "2017-04-18", "Diffuse irradiance [W/m²]");

    private final String value, from, desc;

    private SunshineParameter(String value, String from, String desc) {
        this.value = value;
        this.from = from;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }
    public String getFrom() {
        return from;
    }
    public String getDesc() {
        return desc;
    }
}
