package app.service;

import app.model.Location;
import app.repository.LocationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    public Location findByPersonId(int personId) {
        return locationRepository.findById(personId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Location create(Location location) {
        return locationRepository.save(location);
    }

    public Location update(int personId, Location location) {
        if (!locationRepository.existsById(personId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        locationRepository.deleteById(personId);
        location.setPersonId(personId);
        return locationRepository.save(location);
    }

    public void delete(int personId) {
        locationRepository.deleteById(personId);
    }
}
