package app.controller;

import app.dto.Location;
import app.repository.LocationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {
    private final LocationRepository locationRepository;

    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @GetMapping
    public List<Location> getAll() {
        return locationRepository.findAll();
    }

    @GetMapping("/{personId}")
    public Location getByPersonId(@PathVariable int personId) {
        return locationRepository.findById(personId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Location create(@RequestBody Location location) {
        return locationRepository.save(location);
    }

    @PutMapping("/{personId}")
    public Location update(@PathVariable int personId, @RequestBody Location location) {
        if (!locationRepository.existsById(personId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        locationRepository.deleteById(personId);
        location.setPersonId(personId);
        return locationRepository.save(location);
    }

    @DeleteMapping("/{personId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int personId) {
        locationRepository.deleteById(personId);
    }
}
