package app.weather.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WeatherConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public String openWeatherApiUrl(@Value("${openweathermap.api.url}") String url) {
        return url;
    }

    @Bean
    public String openWeatherApiKey(@Value("${openweathermap.api.key}") String key) {
        return key;
    }
}
