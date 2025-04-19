package smhi.apis;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import smhi.data.ForecastWeather;
import smhi.data.Geo;
import smhi.data.Timestamp;
import smhi.enums.ForecastParameter;
import smhi.exceptions.InvalidDateException;
import smhi.exceptions.NoDataException;
import smhi.utils.SMHIService;

public class Forecast extends API {
    private List<Timestamp> timestamps;

    public static final String FORECAST;

    static {
        FORECAST = "https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/{lon}/lat/{lat}/data.json";
    }

    public Forecast(SMHIService service) {
        super(service);
    }

    @Override
    public void updateURL(SMHIService service, Geo geo) {
        setGeoUrl(
            FORECAST
                .replace("{lon}", String.valueOf(geo.getLongitude()))
                .replace("{lat}", String.valueOf(geo.getLatitude())
        ));
    }

    public void fetchForecast() throws IOException {
        timestamps = new ArrayList<>();
        JSONArray timeSeries = new JSONObject(getService().getString(getGeoUrl())).getJSONArray("timeSeries");

        for (int i = 0; i < timeSeries.length(); i++) {
            JSONObject object = timeSeries.getJSONObject(i);
            String validTime = object.getString("validTime").replace("Z", "");
            LocalDateTime date = LocalDateTime.parse(validTime);

            Timestamp timestamp = new Timestamp(date);

            JSONArray parameters = object.getJSONArray("parameters");
            for (int j = 0; j < parameters.length(); j++) {
                JSONObject parameter = parameters.getJSONObject(j);
                String name = parameter.getString("name");

                double value = parameter.getJSONArray("values").getDouble(0);

                timestamp.addParameter(ForecastParameter.fromString(name), value);
            }

            timestamps.add(timestamp);
        }
    }

    public ForecastWeather getWeather(LocalDate date) throws NoDataException, InvalidDateException {
        if (timestamps == null) {
            throw new NoDataException("Data hasn't been fetched yet.");
        }

        List<Timestamp> timetampsFiltered = timestamps.stream()
                .filter(t -> t.getDate().equals(date.toString()))
                .toList();
        
        if (timetampsFiltered.isEmpty()) {
            throw new InvalidDateException("No data for the date " + date);
        }
        
        return new ForecastWeather(date, timetampsFiltered);
    }
}
