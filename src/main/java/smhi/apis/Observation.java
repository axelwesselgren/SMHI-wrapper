package smhi.apis;

import smhi.data.Geo;
import smhi.utils.SMHIService;

public class Observation extends API {
    public static final String OBSERVATIONS;

    static {
        OBSERVATIONS = "/api/version/{version}/parameter/{parameter}/station/{station}.json";
    }
    
    public Observation(SMHIService service) {
        super(service);
    }

    @Override
    void updateURL(SMHIService service, Geo geo) {
        setGeoUrl(
            OBSERVATIONS
                .replace("{version}", "latest")
        );
    }
}
