package edu.uom.currencymanager.currencies;

public class CurrencyDatabaseFactory {
    public IDatabase getCurrencyDatabase() throws Exception {
        return new CurrencyDatabase();
    }


}