package app.repository;

import app.dto.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
    Optional<Weather> findByLatitudeAndLongitude(double latitude, double longitude);
}
