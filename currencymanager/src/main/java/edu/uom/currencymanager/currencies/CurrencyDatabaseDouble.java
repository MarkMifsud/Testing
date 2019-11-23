package edu.uom.currencymanager.currencies;

import edu.uom.currencymanager.currencyserver.CurrencyServer;
import edu.uom.currencymanager.currencyserver.ServerDouble;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CurrencyDatabaseDouble implements IDatabase {

    CurrencyServer currencyServer;
    public List<Currency> currencies;
    HashMap<String, ExchangeRate> exchangeRates = new HashMap<String, ExchangeRate>();

    String currenciesFile = "target" + File.separator + "classes" + File.separator + "currencies.txt";

    public CurrencyDatabaseDouble() throws Exception {
        this.currencies =new ArrayList<Currency>();
        init();
        //SInce init(0 accesses the filing system and the DefaultServer it was decoupled
        // it is still called explicitly in the CurrencyManager.main()

    }

    public void init() throws Exception {

       currencyServer=new ServerDouble();

        Currency a = new Currency("AAA", "Another Alternative Asset", true);
        Currency b = new Currency("BBB", "Belgian Big Bullion", true);
        Currency c = new Currency("CCC", "Cool Crypto Coin", false);
        Currency d= new Currency("DDD", "Dubious Denomination", false);

        currencies.add(a);
        currencies.add(b);
        currencies.add(c);
        currencies.add(d);

    }

    public Currency getCurrencyByCode(String code) {

        for (Currency currency : currencies) {
            if (currency.code.equalsIgnoreCase(code)) {
                return currency;
            }
        }

        return null;
    }

    public boolean currencyExists(String code) {
        return getCurrencyByCode(code) != null;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public List<Currency> getMajorCurrencies() {
        List<Currency> result = new ArrayList<Currency>();

        for (Currency currency : currencies) {
            if (currency.major==true) {
                result.add(currency);
            }
        }

        return result;
    }

    public ExchangeRate getExchangeRate(String sourceCurrencyCode, String destinationCurrencyCode) throws  Exception {
        long FIVE_MINUTES_IN_MILLIS = 300000;  //5*60*100

        ExchangeRate result = null;

        Currency sourceCurrency = getCurrencyByCode(sourceCurrencyCode);
        if (sourceCurrency == null) {
            throw new Exception("Unknown currency: " + sourceCurrencyCode);
        }

        Currency destinationCurrency = getCurrencyByCode(destinationCurrencyCode);
        if (destinationCurrency == null) {
            throw new Exception("Unknown currency: " + destinationCurrencyCode);
        }

        //Check if exchange rate exists in database
        String key = sourceCurrencyCode + destinationCurrencyCode;
        if (exchangeRates.containsKey(key)) {
            result = exchangeRates.get(key);
            if (System.currentTimeMillis() - result.timeLastChecked > FIVE_MINUTES_IN_MILLIS) {
                result = null;
            }
        }

        if (result == null) {
            double rate = currencyServer.getExchangeRate(sourceCurrencyCode, destinationCurrencyCode);
            result = new ExchangeRate(sourceCurrency,destinationCurrency, rate);

            //Cache exchange rate
            exchangeRates.put(key, result);

            //Cache inverse exchange rate
            String inverseKey = destinationCurrencyCode+sourceCurrencyCode;
            exchangeRates.put(inverseKey, new ExchangeRate(destinationCurrency, sourceCurrency, 1/rate));
        }

        return result;
    }

    public void addCurrency(Currency currency) throws Exception {

        //Save to list
        currencies.add(currency);

        //Persist is decoupled to make testing easier.
        // it is still called explicitly in the CurrencyManager.main()
        //persist();
    }

    public void deleteCurrency(String code) throws Exception {

        //Save to list
        currencies.remove(getCurrencyByCode(code));

        //Persist is decoupled to make testing easier.
        // it is still called explicitly in the CurrencyManager.main()
        // persist();
    }

    public void persist() throws Exception {}



}
