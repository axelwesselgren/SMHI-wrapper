package smhi.apis;

import smhi.data.Geo;
import smhi.utils.SMHIService;

public class Lightning extends API {
    public static final String LIGHTNING;
    
    static {
        LIGHTNING = "/api/version/{version}/lightning.json";
    }
    
    public Lightning(SMHIService service) {
        super(service);
    }

    @Override
    void updateURL(SMHIService service, Geo geo) {
        setGeoUrl(
            LIGHTNING
                .replace("{version}", "latest")
        );
    }

}
