package com.manning.workout.controller;

import com.manning.workout.config.ConfigMapConfiguration;
import com.manning.workout.currency.CurrencyConversion;
import com.manning.workout.currency.ExchangeRateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CurrencyConversionController {
    private final RestTemplate restTemplate = new RestTemplate();
    private final ConfigMapConfiguration configuration;

    @GetMapping("/convert/{fromCurrency}/{toCurrency}")
    public ResponseEntity<CurrencyConversion> currencyConverter(
            @PathVariable("fromCurrency") String fromCurrency,
            @PathVariable("toCurrency") String toCurrency) {

        final String token = configuration.getToken();
        log.info("converting {} to {}", fromCurrency, toCurrency);
        String url = String.format(configuration.getUrl(),token, fromCurrency.toUpperCase());
        log.info("url of currency exchange API is {}", url);

        try {
            final ResponseEntity<ExchangeRateResponse> response = restTemplate.getForEntity(url, ExchangeRateResponse.class);
            if (response.getStatusCode() != OK || response.getBody() == null) {
                return ResponseEntity.status(BAD_GATEWAY).build();
            }
            final ExchangeRateResponse body = response.getBody();
            final Map<String, Double> rates = body.conversion_rates();

            final Double rate = rates.get(toCurrency.toUpperCase());

            if (rate == null) {
                return ResponseEntity.status(NOT_FOUND).build();
            }
            final String conversionResult = "Value of 1 "+fromCurrency.toUpperCase()+ "="+rate+" "+toCurrency.toUpperCase();
            final CurrencyConversion conversion = new CurrencyConversion(
                    fromCurrency.toUpperCase(),
                    toCurrency.toUpperCase(),
                    conversionResult,
                    token);
            return ResponseEntity.ok(conversion);
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
