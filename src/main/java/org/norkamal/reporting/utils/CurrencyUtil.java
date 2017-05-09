package org.norkamal.reporting.utils;

import java.util.Currency;

/**
 * The CurrencyUtil class represents the interface to produce the different reports.
 *
 * @author Norkamal Mimun
 * @version 1.0, May 2017
 */
public class CurrencyUtil {

    public static final String AED = "AED";
    public static final String SAR = "SAR";
    public static final String USD = "USD";


    /**
     * Checks if a given currency trades from Sunday to Thursday
     *
     * @param currency currency to check trading days
     * @return true if trading for currency is from Sunday to Thursday, false otherwise
     */
    public static boolean isTradingFromSundayToThursday(Currency currency) {
        return (currency.equals(Currency.getInstance(AED)) || currency.equals(Currency.getInstance(SAR)));
    }


}
