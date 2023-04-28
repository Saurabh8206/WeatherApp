package com.weather.forecasting.service;

import com.weather.forecasting.util.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;

import static com.weather.forecasting.constants.ApiConstants.*;


@Service
@Slf4j
public class WeatherService {

    public String rapidApiGetHourlyForecastByLocationName(String location) {

        String url = String.format(GET_HOURLY_FORECAST_BY_LOCATION_NAME_URL, location);

        try {

            HttpResponse<String> response = WebUtil.getRequest(url,
                    "X-RapidAPI-Key", API_KEY,
                    "X-RapidAPI-Host", API_HOST,
                    "Content-Type", "application/json");

            if (response.statusCode() == 200) {
                log.info("Successfully fetched the response from RapidApi : " + response.statusCode());
                return response.body();
            } else if (response.statusCode() == 429) {
                log.info("Exceeded the DAILY quota for Requests : " + response.statusCode());
                return GET_HOURLY_FORECAST_BY_LOCATION_NAME;
            } else {
                return " Error while calling Rest call : " + response.statusCode() + response.body();
            }
        } catch (Exception e) {
            return "[{WeatherService.rapidApiGetHourlyForecastByLocationName}] Exception while fetching hourly forecast for location : { " + location + " } with exception ->>>" + e.getMessage();
        }

    }

    public String rapidApiGetForecastSummaryByLocationName(String location) {

        String url = String.format(GET_FORECAST_SUMMARY_BY_LOCATION_NAME_URL, location);

        try {

            HttpResponse<String> response = WebUtil.getRequest(url,
                    "X-RapidAPI-Key", API_KEY,
                    "X-RapidAPI-Host", API_HOST,
                    "Content-Type", "application/json");

            if (response.statusCode() == 200) {
                log.info("Successfully fetched the response from RapidApi : " + response.statusCode());
                return response.body();
            } else if (response.statusCode() == 429) {
                log.info("Exceeded the DAILY quota for Requests : " + response.statusCode());
                return GET_FORECAST_SUMMARY_BY_LOCATION_NAME;
            } else {
                return " Error while calling Rest call : " + response.statusCode() + response.body();
            }
        } catch (Exception e) {
            return "[{WeatherService.rapidApiGetHourlyForecastByLocationName}] Exception while fetching forecast summary for location : { " + location + " } with exception ->>>" + e.getMessage();
        }
    }
}
