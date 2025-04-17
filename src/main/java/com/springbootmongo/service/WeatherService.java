package com.springbootmongo.service;

import com.springbootmongo.api.response.WeatherResponse;

public interface WeatherService {
    public WeatherResponse getWeather(String city);
}
