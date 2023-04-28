package com.weather.forecasting.service;

import com.weather.forecasting.util.WebUtil;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherService {
    private  static final String API_KEY = "3c8a9121a1msh0456ae7c567e070p1db45ejsn55a1c90c3526";
    private  static final String API_HOST = "forecast9.p.rapidapi.com";


    public String rapidApiGetHourlyForecastByLocationName( String location )  {

        String url = "https://example.com/api/hourly-forecast";

        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("x-api-key", API_KEY);
            headers.put("X-RapidAPI-Host", API_HOST);
            headers.put("Content-Type", "application/json");

            HttpResponse<String> response = WebUtil.getRequest(url, String.valueOf(headers));

            if (response.statusCode() == 200) {
                return response.body();
            } else {
                return "Error while calling Rest call : " + response.statusCode();
            }
        } catch (Exception e) {
            return "[{WeatherService.rapidApiGetHourlyForecastByLocationName}] Exception while fetching hourly forecast for location : { "+location+" } with exception ->>>"  + e.getMessage();
        }


    }
}
