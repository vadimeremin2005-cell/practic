package app.location.service;

import app.location.model.Geodata;
import app.location.model.Location;
import app.location.model.Weather;
import app.location.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LocationService {
    private final LocationRepository repository;
    private final RestTemplate restTemplate;
    private final String weatherServiceUrl;

    public LocationService(LocationRepository repository,
                           RestTemplate restTemplate,
                           @Value("${weather.service.url}") String weatherServiceUrl) {
        this.repository = repository;
        this.restTemplate = restTemplate;
        this.weatherServiceUrl = weatherServiceUrl;
    }

    public List<Location> findAll() {
        return repository.findAll();
    }

    public Location findByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Location save(Location location) {
        return repository.save(location);
    }

    public Location update(String name, Location location) {
        if (!repository.existsById(name)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(name);
        location.setName(name);
        return repository.save(location);
    }

    public void delete(String name) {
        repository.deleteById(name);
    }

    public Weather getWeather(String name) {
        Geodata geodata = new Geodata(repository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        return restTemplate.getForObject(
                weatherServiceUrl + "?lat={lat}&lon={lon}",
                Weather.class,
                geodata.getLatitude(),
                geodata.getLongitude()
        );
    }
}
