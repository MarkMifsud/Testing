
package edu.uom.currencymanager;
// testing the main program is not UNIT TESTING but this file is created in case any units in this class prove testable


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import edu.uom.currencymanager.currencies.Currency;
import edu.uom.currencymanager.currencies.CurrencyDatabase;
import edu.uom.currencymanager.currencies.ExchangeRate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class  CurrencyManagerTest {

    CurrencyManager myManager;

    @Before
    public void setup() {

    }

    @After
    public void teardown() {

    }


    @Test
    public void testAddCurrencyLongCode() throws Exception {
        // Throws exception if currency code larger than 3 characters
        myManager= new CurrencyManager();

        try {
            myManager.addCurrency("LONGCODE", "LONG Description", true);
        } catch (Exception e) {
            assertThat(e.getMessage(), is("A currency code should have 3 characters."));
        }
    }

    @Test
    public void testAddCurrencyShortName() throws Exception {
        // Throws exception if currency name smaller than 4 characters
        myManager= new CurrencyManager();

        try {
            myManager.addCurrency("AAA", "its", true);
        } catch (Exception e) {
            assertThat(e.getMessage(), is("A currency's name should be at least 4 characters long."));
        }
    }


}


