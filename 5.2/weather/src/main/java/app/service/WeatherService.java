package app.service;

import app.model.Weather;
import app.repository.WeatherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class WeatherService {
    private final WeatherRepository weatherRepository;

    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public Weather getByCoordinates(double lat, double lon) {
        return weatherRepository.findByLatitudeAndLongitude(lat, lon)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Weather> findAll() {
        return weatherRepository.findAll();
    }

    public Weather create(Weather weather) {
        return weatherRepository.save(weather);
    }

    public Weather update(Long id, Weather weather) {
        if (!weatherRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        weatherRepository.deleteById(id);
        weather.setId(id);
        return weatherRepository.save(weather);
    }

    public void delete(Long id) {
        weatherRepository.deleteById(id);
    }
}
