package com.backend.utils;

import com.backend.domain.Amount;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

public class CurrencyConverterTest {

    @Test
    public void shouldReturnNullWhenOriginalAmountIsNull() {

        Amount newAmount = CurrencyConverter.convertAmount(null, "GBP");

        assertNull(newAmount);
    }

    @Test
    public void shouldReturnNullWhenNewCurrencyIsNull() {
        Amount originalAmount = new Amount();
        originalAmount.setValue(1000);
        originalAmount.setCurrency("AUD");

        Amount newAmount = CurrencyConverter.convertAmount(originalAmount, null);

        assertNull(newAmount);
    }

    @Test
    public void shouldReturnNullWhenNewCurrencyIsEmpty() {
        Amount originalAmount = new Amount();
        originalAmount.setValue(1000);
        originalAmount.setCurrency("AUD");

        Amount newAmount = CurrencyConverter.convertAmount(originalAmount, "");

        assertNull(newAmount);
    }

    @Test
    public void shouldReturnNullWhenNewCurrencyIsNotSupported() {
        Amount originalAmount = new Amount();
        originalAmount.setValue(1000);
        originalAmount.setCurrency("AUD");

        Amount newAmount = CurrencyConverter.convertAmount(originalAmount, "ARS");

        assertNull(newAmount);
    }

    @Test
    public void shouldConvertAmountInUSDToGBP() {
        Amount originalAmount = new Amount();
        originalAmount.setValue(1000);
        originalAmount.setCurrency("USD");

        Amount newAmount = CurrencyConverter.convertAmount(originalAmount, "GBP");

        assertThat(newAmount.getValue(), is(771.25));
        assertThat(newAmount.getCurrency(), is("GBP"));
    }

    @Test
    public void shouldConvertAmountInCNYToUSD() {
        Amount originalAmount = new Amount();
        originalAmount.setValue(1000);
        originalAmount.setCurrency("CNY");

        Amount newAmount = CurrencyConverter.convertAmount(originalAmount, "USD");

        assertThat(newAmount.getValue(), is(148.2272));
        assertThat(newAmount.getCurrency(), is("USD"));
    }

    @Test
    public void shouldConvertAmountInAUDToGBP() {
        Amount originalAmount = new Amount();
        originalAmount.setValue(1000);
        originalAmount.setCurrency("AUD");

        Amount newAmount = CurrencyConverter.convertAmount(originalAmount, "GBP");

        assertThat(newAmount.getValue(), is(550.10699));
        assertThat(newAmount.getCurrency(), is("GBP"));
    }

}