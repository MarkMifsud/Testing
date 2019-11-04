package edu.uom.currencymanager.currencies;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;



public class CurrencyTest {

    Currency newCurrency;

    @Before
    public void setup() {
       // Currency newCurrency;
    }

    @After
    public void teardown() {
        newCurrency = null;
    }


    @Test
    public void testCurrencyToString() throws Exception {

        //Exercise
         newCurrency= new Currency("JPY","Japanese Yen", true);
         String Result = newCurrency.toString();


        //Verify
        assertEquals ("JPY - Japanese Yen", Result);
    }



    @Test
    public void testCurrencyCodeFromString() throws Exception {

        //Exercise
        Currency newCurrency2 = Currency.fromString("JPY,Japanese Yen,y");

        String Result = newCurrency2.code;


        //Verify
        assertEquals ("JPY", Result);
    }

    @Test
    public void testCurrencyNameFromString() throws Exception {

        //Exercise
        Currency newCurrency2 = Currency.fromString("JPY,Japanese Yen,y");

        String Result = newCurrency2.name;


        //Verify
        assertEquals ("Japanese Yen", Result);
    }

    @Test
    public void testCurrencyMajorIsTrueFromString() throws Exception {

        //Exercise
        Currency newCurrency2 = Currency.fromString("JPY,Japanese Yen,yes");

        boolean Result = newCurrency2.major;


        //Verify
        assertTrue( Result);
    }

    @Test
    public void testCurrencyMajorIsFalseFromString() throws Exception {

        //Exercise
        Currency newCurrency2 = Currency.fromString("JPY,Japanese Yen,anything");

        boolean Result = newCurrency2.major;


        //Verify
        assertTrue( !Result);
    }

}