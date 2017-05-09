package org.norkamal.reporting.utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Currency;

/**
 * The CurrencyUtilTest class implements the unit tests for CurrencyUtil API.
 *
 * @author Norkamal Mimun
 * @version 1.0, May 2017
 */
public class CurrencyUtilTest {

    private Currency currencyAED, currencySAR, currencyUSD, currencyGBP;


    @Before
    public void setUp() {

        currencyAED = Currency.getInstance("AED");
        currencySAR = Currency.getInstance("SAR");
        currencyUSD = Currency.getInstance("GBP");
        currencyGBP = Currency.getInstance("USD");
    }

    @Test
    public void Should_Return_True_When_TheCurrencyIsTradingSundayToThursday() {

        Assert.assertEquals(true, CurrencyUtil.isTradingFromSundayToThursday(currencyAED));
        Assert.assertEquals(true, CurrencyUtil.isTradingFromSundayToThursday(currencySAR));
    }

    @Test
    public void Should_Return_False_When_TheCurrencyIsNotTradingSundayToThursday() {

        Assert.assertEquals(false, CurrencyUtil.isTradingFromSundayToThursday(currencyGBP));
        Assert.assertEquals(false, CurrencyUtil.isTradingFromSundayToThursday(currencyUSD));
    }

    @After
    public void tearDown() {
    }
}
