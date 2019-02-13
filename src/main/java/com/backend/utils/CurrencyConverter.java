package com.backend.utils;

import com.backend.domain.Amount;
import org.apache.commons.math3.util.Precision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CurrencyConverter {

    private static final Logger log = LoggerFactory.getLogger(CurrencyConverter.class);

    // just to keep it simple we are going to use constants rates
    private static final double USD_EXCHANGE_RATE = 1.00;
    private static final double EUR_EXCHANGE_RATE = 0.87845;
    private static final double AUD_EXCHANGE_RATE = 1.4020;
    private static final double GBP_EXCHANGE_RATE = 0.77125;
    private static final double JPY_EXCHANGE_RATE = 109.78;
    private static final double CNY_EXCHANGE_RATE = 6.7464;

    private static final String[] SUPPORTED_CURRENCIES = {"USD", "EUR", "AUD", "GBP", "JPY", "CNY"};

    private CurrencyConverter() {
    }

    public static Amount convertAmount(Amount originalAmount, String newCurrency) {

        Amount newAmount = null;

        if (isValidAmount(originalAmount) && isValidCurrency(newCurrency)) {
            newAmount = new Amount();
            double newValue = originalAmount.getValue() * calculateExchangeRate(originalAmount.getCurrency(), newCurrency);
            newAmount.setValue(Precision.round(newValue, 5));
            newAmount.setCurrency(newCurrency);
        }
        return newAmount;
    }

    private static double calculateExchangeRate(String originalCurrency, String newCurrency) {
        double exchangeRate;

        switch (originalCurrency) {
            case "USD":
                exchangeRate = 1 / USD_EXCHANGE_RATE;
                break;
            case "EUR":
                exchangeRate = 1 / EUR_EXCHANGE_RATE;
                break;
            case "AUD":
                exchangeRate = 1 / AUD_EXCHANGE_RATE;
                break;
            case "GBP":
                exchangeRate = 1 / GBP_EXCHANGE_RATE;
                break;
            case "JPY":
                exchangeRate = 1 / JPY_EXCHANGE_RATE;
                break;
            case "CNY":
                exchangeRate = 1 / CNY_EXCHANGE_RATE;
                break;
            default:
                throw new IllegalArgumentException("Invalid currency: " + originalCurrency);
        }

        switch (newCurrency) {
            case "USD":
                exchangeRate = exchangeRate * USD_EXCHANGE_RATE;
                break;
            case "EUR":
                exchangeRate = exchangeRate * EUR_EXCHANGE_RATE;
                break;
            case "AUD":
                exchangeRate = exchangeRate * AUD_EXCHANGE_RATE;
                break;
            case "GBP":
                exchangeRate = exchangeRate * GBP_EXCHANGE_RATE;
                break;
            case "JPY":
                exchangeRate = exchangeRate * JPY_EXCHANGE_RATE;
                break;
            case "CNY":
                exchangeRate = exchangeRate * CNY_EXCHANGE_RATE;
                break;
            default:
                throw new IllegalArgumentException("Invalid currency: " + newCurrency);
        }

        return exchangeRate;
    }

    private static boolean isValidAmount(Amount amount) {
        return amount != null && isValidCurrency(amount.getCurrency());
    }

    private static boolean isValidCurrency(String currency) {
        boolean validCurrency = false;
        if (currency != null) {
            for (String c : SUPPORTED_CURRENCIES) {
                if (c.equals(currency)) {
                    validCurrency = true;
                    break;
                }
            }
        }
        if (!validCurrency) {
            log.error("Currency {} is not a valid or supported currency.", currency);
        }
        return validCurrency;
    }
}
