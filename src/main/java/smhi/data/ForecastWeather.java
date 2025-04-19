package smhi.data;

import java.util.List;

import smhi.apis.Forecast;
import smhi.enums.ForecastParameter;

import java.time.LocalTime;
import java.time.LocalDate;

/**
 * {@code ForecastWeather} is a DTO class that represents forecast weather data for a specific date.
 * <p> The date that the forecast is for is derieved from the list of {@code ForecastTimestamp} objects.
 * <p> The class provides method to get maximum, minimum and average values for a specific parameter aswell
 * as methods to get special values.
 * 
 * @author Axel Lönnby Wesselgren
 * @version 1.0
 * @since 1.0
 * 
 * @see ForecastTimestamp
 */
public class ForecastWeather {
    /**
     * The date that the forecast is for.
     */
    private final LocalDate date;
    /**
     * The list of {@code ForecastTimestamp} the forecast consists of and represents.
     */
    private final List<Timestamp> timeStamps;

    /**
     * Constructs a new {@code ForecastWeather} object with the specified date and list of {@code ForecastTimestamp} objects.
     * 
     * @param date The date that the forecast is for.
     * @param timeStamps A list of {@code ForecastTimestamp} objects.
     */
    public ForecastWeather(LocalDate date, List<Timestamp> timeStamps) {
        this.date = date;
        this.timeStamps = timeStamps;
    }

    /**
     * Returns the minimum value of the specified {@code Forecast.Parameter}.
     * 
     * @param parameter The {@code Forecast.Parameter} to get the minimum value of.
     * @return The minimum value of the specified parameter.
     */
    public double getMin(ForecastParameter parameter) {
        return timeStamps.stream()
            .mapToDouble(t -> t.getParameters().get(parameter))
            .min()
            .orElse(Double.MAX_VALUE);
    }

    /**
     * Returns the maximum value of the specified {@code Forecast.Parameter}.
     * 
     * @param parameter The {@code Forecast.Parameter} to get the maximum value of.
     * @return The maximum value of the specified parameter.
     */
    public double getMax(ForecastParameter parameter) {
        return timeStamps.stream()
            .mapToDouble(t -> t.getParameters().get(parameter))
            .max()
            .orElse(Double.NEGATIVE_INFINITY);
    }

    /**
     * Returns the average value of the specified {@code Forecast.Parameter}.
     * 
     * @param parameter The {@code Forecast.Parameter} to get the average value of.
     * @return The average value of the specified parameter.
     */
    public double getAverage(ForecastParameter parameter) {
        return timeStamps.stream()
            .mapToDouble(t -> t.getParameters().get(parameter))
            .average()
            .orElse(-1);
    }

    /**
     * Returns the weather type of the forecast.
     * <p> Checks for a timestamp at "14:00" first. If not found, checks for "12:00". 
     * <p> If neither is found, returns the weather type from the first {@code Timestamp} in the list.
     * 
     * @return The weather type of the forecast as an integer, between {@code 1} and {@code 27}.
     */
    public int getWeatherType() {
        for (Timestamp t : timeStamps) {
            if (t.getTime().equals("14:00")) {
                return t.getParameters().get(ForecastParameter.WEATHER_SYMBOL).intValue();
            }
        }
        for (Timestamp t : timeStamps) {
            if (t.getTime().equals("12:00")) {
                return t.getParameters().get(ForecastParameter.WEATHER_SYMBOL).intValue();
            }
        }
        return timeStamps
                .get(0)
                .getParameters()
                .get(ForecastParameter.WEATHER_SYMBOL)
                .intValue();
    }

    /**
     * Returns the first {@code Timestamp} in the list's percipitation category.
     * 
     * @return The percipiation category, between {@code 0} and {@code 6}.
     */
    public int getPcat() {
        return timeStamps
                .get(0)
                .getParameters()
                .get(ForecastParameter.PERCIPITATION_CATEGORY)
                .intValue();
    }

    /**
     * Calculates the average wind direction from the list of timestamps.
     * <p> Computes the average direction considering the circular nature of wind directions.
     * 
     * @return The average wind direction in degrees, between {@code 0} and {@code 360}.
     */
    public double getAvgWindDirection() {
        double sumX = 0;
        double sumY = 0;
  
        for (Timestamp t : timeStamps) {
            double radians = Math.toRadians(t.getParameters().get(ForecastParameter.WIND_DIRECTION));
            sumX += Math.cos(radians);
            sumY += Math.sin(radians);
        }

        double averageX = sumX / timeStamps.size();
        double averageY = sumY / timeStamps.size();

        double averageRadians = Math.atan2(averageY, averageX);
        double averageDegrees = Math.toDegrees(averageRadians);

        if (averageDegrees < 0) {
            averageDegrees += 360;
        }

        return averageDegrees;
    }

    /**
     * Returns the {@code ForecastTimestamp} object with the specified {@code LocalTime}.
     * 
     * @param time The time to get the {@code ForecastTimestamp} object for.
     * @return The {@code ForecastTimestamp} object with the specified time, or {@code null} if not found.
     */
    public Timestamp getTimestamp(LocalTime time) {
        return timeStamps.stream()
                .filter(t -> t.getTime().equals(time.toString()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns the date that the forecast is for.
     * 
     * @return The @code LocalDate} that the forecast is for.
     */
    public LocalDate getDate() {
        return date;
    }
    
    @Override
    public String toString() {
        return String.format(
            "Minimum temperature: %.1f\n" +
            "Maximum temperature: %.1f\n" +
            "Average wind speed: %.1f\n" +
            "Maximum gust: %.1f\n" +
            "Weather type: %d\n" +
            "Percipitation category: %d\n" +
            "Average wind direction: %f\n" +
            "Date: %s\n"
            ,
            getMin(ForecastParameter.AIR_TEMPERATURE),
            getMax(ForecastParameter.AIR_TEMPERATURE),
            getAverage(ForecastParameter.WIND_SPEED),
            getMax(ForecastParameter.GUST),
            getWeatherType(),
            getPcat(),
            getAvgWindDirection(),
            date.toString()
        );
    }
}
