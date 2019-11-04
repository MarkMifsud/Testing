package edu.uom.currencymanager.currencyserver;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;



public class DefaultCurrencyServerTest {

    DefaultCurrencyServer myserver;

    @Before
    public void setup() {
        myserver=new DefaultCurrencyServer();
    }

    @After
    public void teardown() {
        myserver=null;
    }

    @Test
    public void testgetExchangeRate() throws Exception {

        double rate = myserver.getExchangeRate("naystrring","anyotherstring");

        assertNotEquals(0,rate);
    }




}