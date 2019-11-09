package edu.uom.currencymanager.currencies;

import edu.uom.currencymanager.currencyserver.CurrencyServer;

public class ServerDouble implements CurrencyServer {

    //  Let's pretend this is a fake server just for the test ok? ;)
    public double getExchangeRate(String sourceCurrency, String destinationCurrency) {

        return 1.00;
    }
}
