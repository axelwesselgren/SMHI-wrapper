package smhi.enums;

public enum Interval {
    HOURLY("hourly"),
    DAILY("daily"),
    MONTHLY("monthly");

    private final String value;

    private Interval(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
