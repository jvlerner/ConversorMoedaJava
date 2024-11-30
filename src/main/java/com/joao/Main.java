package com.joao;

import java.util.Scanner;

import static com.joao.service.ExchangeRateAPI.convertCurrency;
import static com.joao.service.ExchangeRateAPI.roundToTwoDecimalPlaces;

public class Main {
    public static void main(String[] args) {
        String[] moedas = {"BRL", "USD", "EUR", "GBP", "JPY", "AUD", "CAD", "CHF", "INR", "CNY"};

        Scanner scanner = new Scanner(System.in);

        System.out.println("Escolha a moeda base (digite o número correspondente):");
        for (int i = 0; i < moedas.length; i++) {
            System.out.println((i + 1) + ". " + moedas[i]);
        }

        int moedaBaseIndex = scanner.nextInt() - 1;
        if (moedaBaseIndex < 0 || moedaBaseIndex >= moedas.length) {
            System.out.println("Opção inválida!");
            return;
        }

        String moedaBase = moedas[moedaBaseIndex];
        System.out.println("Você escolheu " + moedaBase + " como moeda base.");

        System.out.println("Escolha a moeda para a qual deseja converter (digite o número correspondente):");
        for (int i = 0; i < moedas.length; i++) {
            if (i != moedaBaseIndex) {
                System.out.println((i + 1) + ". " + moedas[i]);
            }
        }

        int moedaDestinoIndex = scanner.nextInt() - 1;
        if (moedaDestinoIndex < 0 || moedaDestinoIndex >= moedas.length || moedaDestinoIndex == moedaBaseIndex) {
            System.out.println("Opção inválida!");
            return;
        }

        String moedaDestino = moedas[moedaDestinoIndex];
        System.out.println("Você escolheu " + moedaDestino + " como moeda de destino.");

        System.out.print("Digite o valor a ser convertido de " + moedaBase + " para " + moedaDestino + ": ");
        double valor = scanner.nextDouble();

        double valorConvertido = convertCurrency(moedaBase, moedaDestino, valor);

        if (valorConvertido != -1) {
            double valorConvertidoBigDecimal = roundToTwoDecimalPlaces(valorConvertido);
            System.out.println("O valor de " + valor + " " + moedaBase + " é equivalente a " + valorConvertidoBigDecimal + " " + moedaDestino);
        } else {
            System.out.println("Falha na conversão.");
        }
    }

}
