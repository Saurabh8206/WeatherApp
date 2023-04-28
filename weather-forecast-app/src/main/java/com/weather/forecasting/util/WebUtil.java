package com.weather.forecasting.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WebUtil {

    public static HttpResponse<String> getRequest(String url, String... headers) throws IOException, InterruptedException, URISyntaxException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .headers(headers)
                .GET()
                .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static HttpResponse<String> postRequest(String url, byte[] byteArray, String... headers) throws IOException, InterruptedException, URISyntaxException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .headers(headers)
                .POST(HttpRequest.BodyPublishers.ofByteArray(byteArray))
                .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    }
}
