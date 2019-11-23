package edu.uom.currencymanager.currencies;

public class CurrencyDatabaseFactory {
    public IDatabase getCurrencyDatabase() throws Exception {
         return new CurrencyDatabase();
    }

    public IDatabase getCurrencyDatabase(boolean TestingMode) throws Exception {
        if (TestingMode) return new CurrencyDatabaseDouble();
        else return new CurrencyDatabase();
    }


}