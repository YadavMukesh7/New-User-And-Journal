package com.springbootmongo.serviceImpl;

import com.springbootmongo.api.response.WeatherResponse;
import com.springbootmongo.cache.AppCache;
import com.springbootmongo.constant.Placeholder;
import com.springbootmongo.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceImpl implements WeatherService {
    private final RestTemplate restTemplate;
    @Autowired
    private AppCache appCache;
    @Value("${weather.api.key}")
    private String accessKey;

    public WeatherServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public WeatherResponse getWeather(String city) {
        String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholder.CITY, city).replace(Placeholder.API_KEY, accessKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }
}
