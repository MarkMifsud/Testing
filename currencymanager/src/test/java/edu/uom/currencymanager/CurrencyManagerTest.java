
package edu.uom.currencymanager;
// testing the main program is not UNIT TESTING but this file is created in case any units in this class prove testable


import edu.uom.currencymanager.currencies.Currency;
import edu.uom.currencymanager.currencies.CurrencyDatabaseDouble;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import edu.uom.currencymanager.currencies.ExchangeRate;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class  CurrencyManagerTest {

  // No unit test can be done to currencyManager without refactoring the code because it calls the database
  CurrencyManager myManager;


    @Before
    public void setup() throws Exception{
        myManager= new CurrencyManager();
        myManager.currencyDatabase = new CurrencyDatabaseDouble();
        myManager.currencyDatabase.init();

    }

    @After
    public void teardown( ) {
        myManager.currencyDatabase=null;
        myManager=null;
    }

   //  The following test is not suitable without refactoring the code to call a double that mimics the db
    @Test
    public void testAddCurrencyLongCode() throws Exception {
        // Throws exception if currency code larger than 3 characters
       // myManager= new CurrencyManager();

        try {
            myManager.addCurrency("LONGCODE", "LONG Description", true);
        } catch (Exception e) {
            assertThat(e.getMessage(), is("A currency code should have 3 characters."));
        }
    }

    //  The following test is not suitable without refactoring the code to call a double that mimics the db
    @Test
    public void testAddCurrencyShortName() throws Exception {
        // Throws exception if currency name smaller than 4 characters
        //myManager= new CurrencyManager();

        try {
            myManager.addCurrency("AAA", "its", true);
        } catch (Exception e) {
            assertThat(e.getMessage(), is("A currency's name should be at least 4 characters long."));
        }
    }

     @Test
    public void testAddCurrencyThatExist() throws Exception {
        // Throws exception if currency code larger than 3 characters

            try {
                myManager.addCurrency("DDD", "Dubious Denomination", false);
            } catch (Exception exc) {
                assertThat(exc.getMessage(), is("The currency DDD already exists."));
            }
    }



    @Test
    public void testAddCurrency() throws Exception {


      long sizeOfListBeforeAdding = myManager.currencyDatabase.currencies. size();
        sizeOfListBeforeAdding++;

        //List<Currency> temp =myManager.currencyDatabase.currencies;
         myManager.addCurrency("EEE", "Eventual Euro Eradicator", false);
        //List<Currency> temp2 =myManager.currencyDatabase.currencies;

       // Currency e = new Currency("DDD", "Dubious Denomination", false);

         //assertEquals(temp.size(), temp2.size());

        //assertTrue(myManager.currencyDatabase.currencyExists("EEE"));

        //assertTrue( myManager.currencyDatabase.currencies.contains(e) );


       long sizeOfListAfterAdding = myManager.currencyDatabase.currencies.size();
        assertEquals(sizeOfListBeforeAdding,  sizeOfListAfterAdding);


    }


    @Test
    public void testgetMjorCurrencyRates() throws Exception{

        List<ExchangeRate> result = myManager.getMajorCurrencyRates();

        List<ExchangeRate> expected = new ArrayList<ExchangeRate>();

        Currency a = new Currency("AAA", "Another Alternative Asset", true);
        Currency b = new Currency("BBB", "Belgian Big Bullion", true);

        ExchangeRate ab =new ExchangeRate(a,b,1.00);
        ExchangeRate ba =new ExchangeRate(b,a,1.00);

        expected.add(ab);
        expected.add(ba);


            assertEquals(expected.toString(),result.toString());
//this should  not include toString


    }

}

