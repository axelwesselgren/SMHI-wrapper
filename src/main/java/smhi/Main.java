package smhi;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;

import smhi.apis.Forecast;
import smhi.apis.Observation;
import smhi.apis.Sunshine;
import smhi.data.ForecastWeather;
import smhi.data.Geo;
import smhi.data.Timestamp;
import smhi.enums.Interval;
import smhi.enums.SunshineParameter;
import smhi.exceptions.InvalidDateException;
import smhi.exceptions.NoDataException;
import smhi.utils.SMHIService;

public class Main {
    public static void main(String[] args) {
        try {
            LocalDate date = LocalDate.now();
            Geo geo = Geo.STOCKHOLM;

            Client client = ClientBuilder.newClient();
            SMHIService service = new SMHIService(geo, client);

            Forecast forecast = new Forecast(service);
            Sunshine sunshine = new Sunshine(service);
            Observation observation = new Observation(service);
            
            List<Timestamp> sunstamps = sunshine.getData(
                SunshineParameter.CIE_UV_IRRADIANCE,
                Interval.DAILY, 
                LocalDate.now().minusMonths(1),
                LocalDate.now()
            );

            forecast.fetchForecast();
            sunstamps.forEach(s -> System.out.println(s.toString()));

            ForecastWeather weather = forecast.getWeather(date);
            System.out.println(weather.toString());

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoDataException e) {
            e.printStackTrace();
        } catch (InvalidDateException e) {
            e.printStackTrace();
        }
    }
}