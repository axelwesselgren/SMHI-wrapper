package smhi.apis;

import smhi.utils.SMHIService;
import smhi.data.Geo;

public class Ice extends API {
    public static final String ICE;
    
    static {
        ICE = "/api/version/{version}/parameter/{parameter}/station/{station}.json";
    }
    
    public Ice(SMHIService service) {
        super(service);
    }

    @Override
    void updateURL(SMHIService service, Geo geo) {
        setGeoUrl(
            ICE
                .replace("{version}", "latest")
        );
    }
}
