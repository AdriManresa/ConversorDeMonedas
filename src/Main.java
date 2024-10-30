import models.ExchangeRateResponse;
import service.ConversionHistory;
import service.CurrencyExchangeService;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static ConversionHistory conversionHistory = new ConversionHistory();
    public static void main(String[] args) throws IOException, InterruptedException {
        String apiKey = "6dd83b8156cda59e7ea8bcba";

        CurrencyExchangeService exchangeService = new CurrencyExchangeService(apiKey);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Selecciona una opción:");
            System.out.println("1. Convertir USD a moneda (ARS, BOB, BRL, CLP, COP)");
            System.out.println("2. Convertir moneda a USD");
            System.out.println("3. Mostrar historial de conversiones");
            System.out.println("4. Salir");

            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.print("Ingrese la cantidad en USD: ");
                    double amountInUSD = scanner.nextDouble();
                    System.out.print("Ingrese la moneda destino (ARS, BOB, BRL, CLP, COP): ");
                    String targetCurrency = scanner.next().toUpperCase();

                    // Obtener la tasa de cambio desde la API
                    ExchangeRateResponse response = exchangeService.getExchangeRates("USD");
                    Map<String, Double> rates = response.getRates();

                    // Calcular el monto convertido
                    if (rates != null && rates.containsKey(targetCurrency)) {
                        double conversionRate = rates.get(targetCurrency);
                        double convertedAmount = amountInUSD * conversionRate;
                        String conversionRecord = String.format("%.2f USD son %.2f %s", amountInUSD, convertedAmount, targetCurrency);
                        conversionHistory.addConversion(conversionRecord);
                        System.out.println(conversionRecord);
                    } else {
                        System.out.println("La moneda ingresada no es válida.");
                    }
                    break;

                case 2:
                    System.out.print("Ingrese la cantidad en la moneda (ARS, BOB, BRL, CLP, COP): ");
                    double amountInOtherCurrency = scanner.nextDouble();
                    System.out.print("Ingrese la moneda origen: ");
                    String sourceCurrency = scanner.next().toUpperCase();

                    // Obtener la tasa de cambio desde la API
                    response = exchangeService.getExchangeRates(sourceCurrency);
                    rates = response.getRates();

                    // Calcular el monto convertido a USD
                    if (rates != null && rates.containsKey("USD")) {
                        double conversionRate = rates.get("USD");
                        double convertedToUSD = amountInOtherCurrency / conversionRate;
                        String conversionRecord = String.format("%.2f %s son %.2f USD", amountInOtherCurrency, sourceCurrency, convertedToUSD);
                        conversionHistory.addConversion(conversionRecord);
                        System.out.println(conversionRecord);
                    } else {
                        System.out.println("La moneda ingresada no es válida.");
                    }
                    break;

                case 3:
                    conversionHistory.showHistory();
                    break;

                case 4:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}