package com.joao.service;

import com.google.gson.Gson;
import com.joao.models.ExchangeRateResponse;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ExchangeRateAPI {

    private static class CreateURI {
        public static String createURL(String baseCurrency) {
            String apiKey = "995006f943271eaab49dd848";
            return "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + baseCurrency;
        }
    }

    public static String httpRequest(String baseCurrency) {
        String urlStr = CreateURI.createURL(baseCurrency);

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(60))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlStr))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body();
            } else {
                return "Erro: " + response.statusCode();
            }
        } catch (Exception e) {
            return "Falha ao fazer requisição: " + e.getMessage();
        }
    }

    public static double convertCurrency(String fromCurrency, String toCurrency, double amount) {
        String response = httpRequest(fromCurrency);
        Gson gson = new Gson();
        ExchangeRateResponse exchangeRateResponse = gson.fromJson(response, ExchangeRateResponse.class);

        if (exchangeRateResponse != null && exchangeRateResponse.getConversionRates() != null) {
            if (exchangeRateResponse.getConversionRates().containsKey(toCurrency)) {
                double conversionRate = exchangeRateResponse.getConversionRates().get(toCurrency);
                return amount * conversionRate;
            } else {
                System.out.println("Moeda " + toCurrency + " não encontrada na resposta.");
                return -1;
            }
        } else {
            System.out.println("Falha ao obter as taxas de conversão.");
            return -1;
        }
    }

    public static double roundToTwoDecimalPlaces(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
