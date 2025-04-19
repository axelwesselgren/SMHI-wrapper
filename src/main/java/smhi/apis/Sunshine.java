package smhi.apis;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.json.JSONArray;
import org.json.JSONObject;

import smhi.data.Geo;
import smhi.data.Timestamp;
import smhi.enums.Interval;
import smhi.enums.SunshineParameter;
import smhi.exceptions.InvalidDateException;
import smhi.utils.SMHIService;

public class Sunshine extends API {
    private static final String STRANG;

    static {
        STRANG = "https://opendata-download-metanalys.smhi.se/api/category/strang1g/version/1/geotype/point/lon/{longitude}/lat/{latitude}/parameter/{parameter}/data.json?from={from}&to={to}&interval={interval}";
    }

    public Sunshine(SMHIService service) {
        super(service);
    }

    @Override
    public void updateURL(SMHIService service, Geo geo) {
        setGeoUrl(
            STRANG
                .replace("{longitude}", String.valueOf(geo.getLongitude()))
                .replace("{latitude}", String.valueOf(geo.getLatitude())
        ));
    }
    

    public List<Timestamp> getData(SunshineParameter parameter, Interval interval, LocalDate from, LocalDate to) throws IOException, InvalidDateException {
        setGeoUrl(
            getGeoUrl()
                .replace("{parameter}", parameter.getValue())
                .replace("{from}", from.toString())
                .replace("{to}", to.toString())
                .replace("{interval}", interval.getValue()
        ));

        JSONArray json = new JSONArray(getService().getString(getGeoUrl()));
        List<Timestamp> data = new ArrayList<>();

        for (int i = 0; i < json.length(); i++) {
            JSONObject object = json.getJSONObject(i);

            String dateTimeStr = object.getString("date_time").replace("Z", "");
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr);

            double value = object.getDouble("value");
            Timestamp timestamp = new Timestamp(dateTime);
            timestamp.addParameter(parameter, value);

            data.add(timestamp);
        }

        return data;
    }
}
