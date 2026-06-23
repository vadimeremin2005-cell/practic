package app.controller;

import app.model.Location;
import app.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public List<Location> getAll() {
        return locationService.findAll();
    }

    @GetMapping("/{personId}")
    public Location getByPersonId(@PathVariable int personId) {
        return locationService.findByPersonId(personId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Location create(@RequestBody Location location) {
        return locationService.create(location);
    }

    @PutMapping("/{personId}")
    public Location update(@PathVariable int personId, @RequestBody Location location) {
        return locationService.update(personId, location);
    }

    @DeleteMapping("/{personId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int personId) {
        locationService.delete(personId);
    }
}
