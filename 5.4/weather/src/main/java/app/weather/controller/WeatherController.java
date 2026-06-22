package app.weather.controller;

import app.weather.model.Root;
import app.weather.service.WeatherCacheService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {
    private final WeatherCacheService weatherCacheService;

    public WeatherController(WeatherCacheService weatherCacheService) {
        this.weatherCacheService = weatherCacheService;
    }

    @GetMapping("/weather")
    public Root getWeather(@RequestParam double lat, @RequestParam double lon) {
        return weatherCacheService.getWeather(lat, lon);
    }
}
