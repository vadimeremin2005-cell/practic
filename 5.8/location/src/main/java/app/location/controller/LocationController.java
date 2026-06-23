package app.location.controller;

import app.location.model.Geodata;
import app.location.model.Location;
import app.location.model.Weather;
import app.location.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class LocationController {
    private final LocationRepository repository;
    private final RestTemplate restTemplate;
    private final String weatherServiceUrl;

    public LocationController(LocationRepository repository,
                              RestTemplate restTemplate,
                              @Value("${weather.service.url}") String weatherServiceUrl) {
        this.repository = repository;
        this.restTemplate = restTemplate;
        this.weatherServiceUrl = weatherServiceUrl;
    }

    @GetMapping("/location")
    public List<Location> getAll(@RequestParam(required = false) String name) {
        if (name != null) {
            return List.of(repository.findByName(name)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        }
        return repository.findAll();
    }

    @PostMapping("/location")
    @ResponseStatus(HttpStatus.CREATED)
    public Location create(@RequestBody Location location) {
        return repository.save(location);
    }

    @PutMapping("/location")
    public Location update(@RequestParam String name, @RequestBody Location location) {
        if (!repository.existsById(name)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(name);
        location.setName(name);
        return repository.save(location);
    }

    @DeleteMapping("/location")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam String name) {
        repository.deleteById(name);
    }

    @GetMapping("/location/weather")
    public Weather getWeather(@RequestParam String name) {
        Geodata geodata = new Geodata(repository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        RestTemplate restTemplate = this.restTemplate;
        return restTemplate.getForObject(
                weatherServiceUrl + "?lat={lat}&lon={lon}",
                Weather.class,
                geodata.getLatitude(),
                geodata.getLongitude()
        );
    }
}
