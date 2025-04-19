package smhi.apis;

import smhi.data.Geo;
import smhi.utils.SMHIService;

public abstract class API {
    private Geo geo;
    private SMHIService service;
    private String url;

    public static final String BASE, ADD_ONS;
    public static final String ANALYS, FORECAST, OBSERVATIONS;

    static {
        BASE = "https://opendata-download-met{type}.smhi.se";
        ADD_ONS = "/api/category/{category}/version/{version}";

        ANALYS = "analys";
        FORECAST = "fcst";
        OBSERVATIONS = "obs";
    }

    public API(SMHIService service) {
        this.service = service;
        update(service.getGeo());
    }

    public void update(Geo geo) {
        this.geo = geo;
        updateURL(service, geo);
    }

    abstract void updateURL(SMHIService service, Geo geo);

    public Geo getGeo() {
        return geo;
    }
    public SMHIService getService() {
        return service;
    }
    public String getGeoUrl() {
        return url;
    }

    public void setGeoUrl(String url) {
        this.url = url;
    }

    public String getBase(String type) {
        return BASE.replace("{type}", type);
    }
    public String getAddOns(String category, String version) {
        return ADD_ONS
            .replace("{category}", category)
            .replace("{version}", version);
    }
}
