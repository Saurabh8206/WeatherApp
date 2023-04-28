package com.weather.forecasting.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WebUtil {

    public static HttpResponse<String> getRequest(String url, String... headers) {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .headers(headers)
                    .GET()
                    .build();
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error sending HTTP request: " + e.getMessage(), e);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid URL syntax: " + url, e);
        }
    }

    public static HttpResponse<String> postRequest(String url, byte[] byteArray, String... headers) {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .headers(headers)
                    .POST(HttpRequest.BodyPublishers.ofByteArray(byteArray))
                    .build();
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error sending HTTP request: " + e.getMessage(), e);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid URL syntax: " + url, e);
        }

    }
}
