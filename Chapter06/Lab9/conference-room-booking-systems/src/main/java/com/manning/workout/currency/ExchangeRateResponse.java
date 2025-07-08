package com.manning.workout.currency;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ExchangeRateResponse(
        String result,
        String base_code,
        Map<String, Double> conversion_rates
) {
}

