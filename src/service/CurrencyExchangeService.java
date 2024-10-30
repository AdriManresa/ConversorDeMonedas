package service;

import com.google.gson.Gson;
import models.ExchangeRateResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrencyExchangeService {
    private final String apiKey;
    private final HttpClient httpClient;

    public CurrencyExchangeService(String apiKey) {
        this.apiKey = apiKey;
        this.httpClient = HttpClient.newHttpClient();
    }

    public ExchangeRateResponse getExchangeRates(String baseCurrency) throws IOException, InterruptedException {
        String url = String.format("https://v6.exchangerate-api.com/v6/%s/latest/%s", apiKey, baseCurrency);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        //System.out.println("Respuesta de la API: " + response.body());
        if (response.statusCode() == 200) {
            Gson gson = new Gson();
            return gson.fromJson(response.body(), ExchangeRateResponse.class);
        } else {
            throw new RuntimeException("Error: Código de respuesta " + response.statusCode());
        }
    }

}


