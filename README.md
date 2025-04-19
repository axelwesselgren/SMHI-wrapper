<a id="readme-top"></a>

<div align="center">
<h3 align="center">SMHI Wrapper</h3>

  <p align="center">
    A Java wrapper for the Swedish Meteorological and Hydrological Institute API
  </p>
</div>

## About The Project

A Java-based API wrapper for SMHI. Allows for gathering and collection of data through an abstracted layer.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

# Usage Example

This example demonstrates how to use different APIs available in the `apis` folder in the `src`. The APIs in the example are:

- **Forecast**
- **Sunshine**
- **Observation**

### Example Code

```java
public static void main(String[] args) {
    try {
        // Define the current date and geographic location (Stockholm in this case)
        LocalDate date = LocalDate.now();
        Geo geo = Geo.STOCKHOLM;

        // Initialize the client and SMHI service
        Client client = ClientBuilder.newClient();
        SMHIService service = new SMHIService(geo, client);

        // Create instances of the Forecast, Sunshine, and Observation services
        Forecast forecast = new Forecast(service);
        Sunshine sunshine = new Sunshine(service);
        Observation observation = new Observation(service);
        
        // Fetch daily UV irradiance data for the past month
        List<Timestamp> sunstamps = sunshine.getData(
            SunshineParameter.CIE_UV_IRRADIANCE,
            Interval.DAILY, 
            LocalDate.now().minusMonths(1),
            LocalDate.now()
        );

        // Fetch the weather forecast
        forecast.fetchForecast();

        // Print each timestamp of sunshine data
        sunstamps.forEach(s -> System.out.println(s.toString()));

        // Get the weather data for the defined date
        ForecastWeather weather = forecast.getWeather(date);
        System.out.println(weather.toString());

        // Close the client connection
        client.close();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (NoDataException e) {
        e.printStackTrace();
    } catch (InvalidDateException e) {
        e.printStackTrace();
    }
}

```

## Roadmap

- [ ] Ensure project works
- [ ] Add proper documentation

See the [open issues](https://github.com/axelwesselgren/smhi-wrapper/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## ⚠️ Disclaimer

**This project may not be up to date with the latest SMHI API version, but it should work with older ones if so.**

## License

Distributed under the MIT license. See `LICENSE` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>
