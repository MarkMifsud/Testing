package edu.uom.currencymanager.currencies;

import javax.swing.text.NumberFormatter;

public class ExchangeRate {

    public Currency sourceCurrency;
    public Currency destinationCurrency;
    public double rate;
    public long timeLastChecked;

    public ExchangeRate(Currency sourceCurrency, Currency destinationCurrency, double rate) {
        this.sourceCurrency =sourceCurrency;
        this.destinationCurrency = destinationCurrency;
        this.rate =rate;
        timeLastChecked = (long) 1.00; //System.currentTimeMillis();
            //this is a temporary substitute for a more decent getTime() method that will come later
    }

    public String toString() {
        return sourceCurrency.code + " 1 = " + destinationCurrency.code + " " + Util.formatAmount(rate);
    }

}
