package app.weather.service;

import app.weather.model.Root;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class WeatherCacheService {
    private static final long CACHE_TTL_MS = 60_000;

    private final RestTemplate restTemplate;
    private final String apiUrl;
    private final String apiKey;
    private final Map<String, CacheEntry> cache = new ConcurrentHashMap<>();

    public WeatherCacheService(RestTemplate restTemplate,
                               @Value("${openweathermap.api.url}") String apiUrl,
                               @Value("${openweathermap.api.key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
    }

    public Root getWeather(double lat, double lon) {
        String key = lat + "," + lon;
        long now = System.currentTimeMillis();

        CacheEntry entry = cache.get(key);
        if (entry != null && now - entry.timestamp < CACHE_TTL_MS) {
            return entry.data;
        }

        Root root = restTemplate.getForObject(
                apiUrl + "?lat={lat}&lon={lon}&appid={key}&units=metric",
                Root.class,
                lat,
                lon,
                apiKey
        );

        cache.put(key, new CacheEntry(root, now));
        return root;
    }

    private static class CacheEntry {
        private final Root data;
        private final long timestamp;

        private CacheEntry(Root data, long timestamp) {
            this.data = data;
            this.timestamp = timestamp;
        }
    }
}
