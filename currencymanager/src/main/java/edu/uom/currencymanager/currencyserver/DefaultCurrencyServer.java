package edu.uom.currencymanager.currencyserver;

import java.util.Random;

public class DefaultCurrencyServer implements CurrencyServer {

    //  In a real life situation this would not be a standalone Unit but it is testable in this case
    public double getExchangeRate(String sourceCurrency, String destinationCurrency) {
        Random random = new Random();
        return random.nextDouble()*1.5; //Generate a random number between 0 and 1.5;
    }

}
