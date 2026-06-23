package app.weather.controller;

import app.weather.model.Root;
import app.weather.service.WeatherCacheService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WeatherController {
    private final WeatherCacheService weatherCacheService;
    private final RestTemplate restTemplate;

    public WeatherController(WeatherCacheService weatherCacheService, RestTemplate restTemplate) {
        this.weatherCacheService = weatherCacheService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/weather")
    public Root getWeather(@RequestParam double lat, @RequestParam double lon) {
        RestTemplate restTemplate = this.restTemplate;
        return weatherCacheService.getWeather(lat, lon);
    }
}
