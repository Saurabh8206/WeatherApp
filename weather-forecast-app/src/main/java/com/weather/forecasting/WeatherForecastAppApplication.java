package com.weather.forecasting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootApplication
public class WeatherForecastAppApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(WeatherForecastAppApplication.class, args);

	}

}
