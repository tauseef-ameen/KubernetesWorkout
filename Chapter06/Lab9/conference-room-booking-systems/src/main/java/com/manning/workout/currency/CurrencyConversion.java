package com.manning.workout.currency;

public record CurrencyConversion(String fromCurrency,
                                 String toCurrency,
                                 String conversionResult) {
}
