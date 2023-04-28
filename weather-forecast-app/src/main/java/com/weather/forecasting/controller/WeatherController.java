package com.weather.forecasting.controller;

import com.weather.forecasting.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weathers")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weatherservice/hourly-forecast/{locationName}")
    public ResponseEntity<String> getWeatherForecastHourlyByLocationName(@PathVariable String locationName) {
        String forecastHourly = weatherService.rapidApiGetHourlyForecastByLocationName(locationName);
        return ResponseEntity.status(HttpStatus.OK).body(forecastHourly);
    }

    @GetMapping("/weatherservice/summary-forecast/{locationName}")
    public ResponseEntity<String> getWeatherForecastSummaryByLocationName(@PathVariable String locationName) {
        String forecastSummary = weatherService.rapidApiGetForecastSummaryByLocationName(locationName);
        return ResponseEntity.status(HttpStatus.OK).body(forecastSummary);
    }
}
