package smhi.enums;

import smhi.interfaces.Parameter;

public enum ForecastParameter implements Parameter {
    AIR_PRESSURE("msl", "Air pressure"),
    AIR_TEMPERATURE("t", "Air temperature"),
    HORIZONTAL_VISIBILITY("vis", "Horizontal visibility"),
    WIND_DIRECTION("wd", "Wind direction"),
    WIND_SPEED("ws", "Wind speed"),
    RELATIVE_HUMIDITY("r", "Relative humidity"),
    THUNDER_PROBAILITY("tstm", "Thunder probability"),
    TOT_CLOUD_COVER("tcc_mean", "Total cloud cover"),
    LOW_CLOUD_COVER("lcc_mean", "Low cloud cover"),
    MEDIUM_CLOUD_COVER("mcc_mean", "Medium cloud cover"),
    HIGH_CLOUD_COVER("hcc_mean", "High cloud cover"),
    GUST("gust", "Gust"),
    MIN_PRECIPITATION("pmin", "Minimum precipitation"),
    MAX_PRECIPITATION("pmax", "Maximum precipitation"),
    PERCENT_OF_PRECIPITATION_IN_FROZEN_FORM("spp", "Percent of precipitation in frozen form"),
    PERCIPITATION_CATEGORY("pcat", "Precipitation category"),
    MEAN_PRECIPITATION("pmean", "Mean precipitation"),
    MEDIAN_PRECIPITATION("pmedian", "Median precipitation"),
    WEATHER_SYMBOL("Wsymb2", "Weather symbol");

    private final String value, desc;

    private ForecastParameter(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }
    public String getDesc() {
        return desc;
    }

    public static ForecastParameter fromString(String value) {
        for (ForecastParameter parameter : ForecastParameter.values()) {
            if (parameter.value.equals(value)) {
                return parameter;
            }
        }
        return null;
    }
}
