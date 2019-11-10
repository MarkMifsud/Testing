package edu.uom.currencymanager.currencies;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;



public class UtilTest {

    Util myUtil;

    @Before
    public void setup() {
        myUtil= new Util();
    }

    @After
    public void teardown() {
         myUtil=null;
    }

    @Test
    public void testFormatAmount() throws Exception {

        //Exercise
        String Result = myUtil.formatAmount(9916777216.05);


        //Verify
        assertEquals ("9,916,777,216.05", Result);
    }

}