package app.location.controller;

import app.location.model.Location;
import app.location.model.Weather;
import app.location.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/location")
    public List<Location> getAll(@RequestParam(required = false) String name) {
        return locationService.findAll(name);
    }

    @PostMapping("/location")
    @ResponseStatus(HttpStatus.CREATED)
    public Location create(@RequestBody Location location) {
        return locationService.create(location);
    }

    @PutMapping("/location")
    public Location update(@RequestParam String name, @RequestBody Location location) {
        return locationService.update(name, location);
    }

    @DeleteMapping("/location")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam String name) {
        locationService.delete(name);
    }

    @GetMapping("/location/weather")
    public Weather getWeather(@RequestParam String name) {
        return locationService.getWeather(name);
    }
}
