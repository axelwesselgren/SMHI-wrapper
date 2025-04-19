package smhi.apis;

public class FireRisk extends API {
    private static final String PARAMETER, FORECAST;

    static {
        FORECAST =  + ADD_ONS.replace("{category}", "fwif1g").replace("", ADD_ONS) + "/daily/geotype/point/lon/{lon}/lat/{lat}/data.json";
        PARAMETER = BASE.replace("{type}", API.FORECAST) + "/api/category/fwif1g/version/1/{interval}/parameter.json";
    }
}
