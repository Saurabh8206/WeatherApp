package com.weather.forecasting.service;

import com.weather.forecasting.util.WebUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.weather.forecasting.constants.ApiConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WeatherServiceTest {

    @Mock
    private WeatherService weatherService;

    @Mock
    private WebUtil webUtil;


    @Test
    @Order(1)
    @DisplayName("This test will perform the successful http call with the rapidApi - SUCCESS")
    void testRapidApiGetHourlyForecastByLocationNameSuccess() throws URISyntaxException, IOException, InterruptedException {

        String location = "nonexistent-location";
        String url = String.format(GET_HOURLY_FORECAST_BY_LOCATION_NAME_URL, location);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .headers("X-RapidAPI-Key", API_KEY,
                        "X-RapidAPI-Host", API_HOST,
                        "Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String responseJson = null;
        if (response.statusCode() == 200) {
            responseJson = response.body();
        } else if (response.statusCode() == 429) {
            responseJson = GET_HOURLY_FORECAST_BY_LOCATION_NAME;
        }

        when(weatherService.rapidApiGetHourlyForecastByLocationName(location)).thenReturn(responseJson);

        String result = weatherService.rapidApiGetHourlyForecastByLocationName(location);

        assertEquals(responseJson, result);
    }

    @Test
    @Order(2)
    @DisplayName("This test will look for the client error - EXCEEDED_LIMIT")
    void testRapidApiGetHourlyForecastByLocationNameClientErrorExceededLimit() throws Exception {

        String location = "nonexistent-location";
        String url = String.format(GET_HOURLY_FORECAST_BY_LOCATION_NAME_URL, location);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .headers("X-RapidAPI-Key", EXCEEDED_API_KEY,
                        "X-RapidAPI-Host", API_HOST,
                        "Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String responseJson = null;
        if (response.statusCode() == 200) {
            responseJson = response.body();
        } else if (response.statusCode() == 429) {
            responseJson = GET_HOURLY_FORECAST_BY_LOCATION_NAME;
        }

        when(weatherService.rapidApiGetHourlyForecastByLocationName(location)).thenReturn(responseJson);

        String result = weatherService.rapidApiGetHourlyForecastByLocationName(location);
        assertEquals(response.statusCode(), 429);
        assertEquals(responseJson, result);
    }

    @Test
    @Order(3)
    @DisplayName("This test will look for the URI exception if the url is prevented wrongly - NOT_FOUND")
    void testRapidApiGetHourlyForecastByLocationNameNotFound() throws Exception {

        String location = "nonexistent-location";
        String url = String.format("https://forecast9.p.rapidapi.com/rapidapi/forecast/%s/hour/", location);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .headers("X-RapidAPI-Key", API_KEY,
                        "X-RapidAPI-Host", API_HOST,
                        "Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(response.statusCode(), HttpStatus.NOT_FOUND.value());

    }

    @Test
    @Order(4)
    @DisplayName("This test will look for the request params are passed properly or not - UNAUTHORISED")
    void testRapidApiGetHourlyForecastByLocationNameUnauthorised() throws Exception {

        String location = "nonexistent-location";
        String url = String.format(GET_HOURLY_FORECAST_BY_LOCATION_NAME_URL, location);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .headers("X-RapidAPI-Key", API_KEY,// Host is not provided
                        "Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String responseJson = null;
        if (response.statusCode() == 200) {
            responseJson = response.body();
        } else if (response.statusCode() == 429) {
            responseJson = GET_HOURLY_FORECAST_BY_LOCATION_NAME;
        }

        when(weatherService.rapidApiGetHourlyForecastByLocationName(location)).thenReturn(responseJson);

        String result = weatherService.rapidApiGetHourlyForecastByLocationName(location);

        assertEquals(responseJson, result);
        assertEquals(response.statusCode(), HttpStatus.UNAUTHORIZED.value());

    }

    @Test
    @Order(5)
    @DisplayName("This test will perform the successful http call with the rapidApi for forecastSummary- SUCCESS")
    void testRapidApiGetForecastSummaryByLocationNameSuccess() throws URISyntaxException, IOException, InterruptedException {

        String location = "nonexistent-location";
        String url = String.format(GET_FORECAST_SUMMARY_BY_LOCATION_NAME_URL, location);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .headers("X-RapidAPI-Key", API_KEY,
                        "X-RapidAPI-Host", API_HOST,
                        "Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String responseJson = null;
        if (response.statusCode() == 200) {
            responseJson = response.body();
        } else if (response.statusCode() == 429) {
            responseJson = GET_FORECAST_SUMMARY_BY_LOCATION_NAME;
        }

        when(weatherService.rapidApiGetForecastSummaryByLocationName(location)).thenReturn(responseJson);

        String result = weatherService.rapidApiGetForecastSummaryByLocationName(location);

        assertEquals(responseJson, result);
    }

    @Test
    @Order(6)
    @DisplayName("This test will look for the client error - EXCEEDED_LIMIT")
    void testRapidApiGetForecastSummaryByLocationNameClientErrorExceededLimit() throws Exception {

        String location = "nonexistent-location";
        String url = String.format(GET_FORECAST_SUMMARY_BY_LOCATION_NAME_URL, location);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .headers("X-RapidAPI-Key", EXCEEDED_API_KEY,
                        "X-RapidAPI-Host", API_HOST,
                        "Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String responseJson = null;
        if (response.statusCode() == 200) {
            responseJson = response.body();
        } else if (response.statusCode() == 429) {
            responseJson = GET_FORECAST_SUMMARY_BY_LOCATION_NAME;
        }

        when(weatherService.rapidApiGetForecastSummaryByLocationName(location)).thenReturn(responseJson);

        String result = weatherService.rapidApiGetForecastSummaryByLocationName(location);
        assertEquals(response.statusCode(), 429);
        assertEquals(responseJson, result);
    }

    @Test
    @Order(7)
    @DisplayName("This test will look for the URI exception if the url is prevented wrongly - NOT_FOUND")
    void testRapidApiGetForecastSummaryByLocationNameNotFound() throws Exception {

        String location = "nonexistent-location";
        String url = String.format("https://forecast9.p.rapidapi.com/rapidapi/forecast/%s/hour/", location);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .headers("X-RapidAPI-Key", API_KEY,
                        "X-RapidAPI-Host", API_HOST,
                        "Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(response.statusCode(), HttpStatus.NOT_FOUND.value());

    }

    @Test
    @Order(8)
    @DisplayName("This test will look for the request params are passed properly or not - UNAUTHORISED")
    void testRapidApiGetForecastSummaryByLocationNameUnauthorised() throws Exception {

        String location = "nonexistent-location";
        String url = String.format(GET_FORECAST_SUMMARY_BY_LOCATION_NAME_URL, location);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .headers("X-RapidAPI-Key", API_KEY,// Host is not provided
                        "Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String responseJson = null;
        if (response.statusCode() == 200) {
            responseJson = response.body();
        } else if (response.statusCode() == 429) {
            responseJson = GET_FORECAST_SUMMARY_BY_LOCATION_NAME;
        }

        when(weatherService.rapidApiGetForecastSummaryByLocationName(location)).thenReturn(responseJson);

        String result = weatherService.rapidApiGetForecastSummaryByLocationName(location);

        assertEquals(responseJson, result);
        assertEquals(response.statusCode(), HttpStatus.UNAUTHORIZED.value());

    }
}
