package app.controller;

import app.dto.Weather;
import app.repository.WeatherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherRepository weatherRepository;

    public WeatherController(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @GetMapping
    public Weather getByCoordinates(@RequestParam double lat, @RequestParam double lon) {
        return weatherRepository.findByLatitudeAndLongitude(lat, lon)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    public List<Weather> getAll() {
        return weatherRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Weather create(@RequestBody Weather weather) {
        return weatherRepository.save(weather);
    }

    @PutMapping("/{id}")
    public Weather update(@PathVariable Long id, @RequestBody Weather weather) {
        if (!weatherRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        weatherRepository.deleteById(id);
        weather.setId(id);
        return weatherRepository.save(weather);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        weatherRepository.deleteById(id);
    }
}
