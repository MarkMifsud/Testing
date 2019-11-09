package edu.uom.currencymanager.currencies;

import edu.uom.currencymanager.currencyserver.CurrencyServer;

import java.util.ArrayList;
import java.util.List;

public interface IDatabase {

    public List<Currency> currencies = new ArrayList<Currency>();


    public void init() throws Exception ;

    public Currency getCurrencyByCode(String code) ;

    public boolean currencyExists(String code) ;

    public List<Currency> getCurrencies() ;

    public List<Currency> getMajorCurrencies() ;

    public ExchangeRate getExchangeRate(String sourceCurrencyCode, String destinationCurrencyCode)throws  Exception;

    public void addCurrency(Currency currency) throws Exception;

    public void deleteCurrency(String code) throws Exception ;

    public void persist() throws Exception ;




}
