package smhi.apis;

import smhi.data.Geo;
import smhi.utils.SMHIService;

public class Algae extends API {
    public static final String ALGAE;
    
    static {
        ALGAE = "/api/version/{version}/parameter/{parameter}/station/{station}.json";
    }
    
    public Algae(SMHIService service) {
        super(service);
    }

    @Override
    void updateURL(SMHIService service, Geo geo) {
        setGeoUrl(
            ALGAE
                .replace("{version}", "latest")
        );
    }
}