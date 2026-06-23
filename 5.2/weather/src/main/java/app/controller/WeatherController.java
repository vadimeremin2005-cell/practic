package app.controller;

import app.model.Weather;
import app.service.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public Weather getByCoordinates(@RequestParam double lat, @RequestParam double lon) {
        return weatherService.getByCoordinates(lat, lon);
    }

    @GetMapping("/all")
    public List<Weather> getAll() {
        return weatherService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Weather create(@RequestBody Weather weather) {
        return weatherService.create(weather);
    }

    @PutMapping("/{id}")
    public Weather update(@PathVariable Long id, @RequestBody Weather weather) {
        return weatherService.update(id, weather);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        weatherService.delete(id);
    }
}
