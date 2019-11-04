package edu.uom.currencymanager.currencies;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;



public class ExchangeRateTest {

    ExchangeRate myExchangeRate;
    Currency sourceCurrency= new Currency("JPY","Japanese Yen", true);
    Currency destinationCurrency= new Currency("NOK","Norweigian Krone", true);

    @Before
    public void setup() {
        //Setup

        myExchangeRate = new ExchangeRate(sourceCurrency,destinationCurrency,0.5432);
    }

    @After
    public void teardown() {
        myExchangeRate = null;
    }

    @Test
    public void testExchangeRateSourceCurrency()  throws Exception {

        //Verify
        assertSame (sourceCurrency, myExchangeRate.sourceCurrency);

    }

    @Test
    public void testExchangeRateDestinationCurrency()  throws Exception {

        //Verify
        assertSame (destinationCurrency, myExchangeRate.destinationCurrency);

    }

    @Test
    public void testExchangeRateRate()  throws Exception {

        //Verify
        assertEquals (0.5432, myExchangeRate.rate,0);

    }


    @Test
    public void testToString()  throws Exception {

        String Result= myExchangeRate.toString();
        //Verify
        assertEquals ("JPY 1 = NOK 0.54", Result);

    }










}