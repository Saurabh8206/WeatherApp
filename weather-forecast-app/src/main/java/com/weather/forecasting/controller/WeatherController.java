package com.weather.forecasting.controller;

import com.weather.forecasting.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weathers")
public class WeatherController {

    WeatherService weatherService;

    public ResponseEntity<String> weather(){
        String forecast = weatherService.rapidApiGetHourlyForecastByLocationName("");
        return ResponseEntity.ok(forecast);
    }
}
