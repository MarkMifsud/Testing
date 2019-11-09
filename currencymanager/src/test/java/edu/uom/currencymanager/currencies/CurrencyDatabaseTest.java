
 package edu.uom.currencymanager.currencies;
// Unit tests do not talk to a database but some units are still testable
 //some will likely need to use doubles

 import edu.uom.currencymanager.currencyserver.CurrencyServer;
 import org.junit.After;
 import org.junit.Before;
 import org.junit.Test;
 import static org.junit.Assert.*;

 import java.util.ArrayList;
 import java.util.List;


 import javax.swing.text.NumberFormatter;

 public class CurrencyDatabaseTest {


     List<Currency> currencies = new ArrayList<Currency>();
     Currency a = new Currency("AAA", "Another Alternative Alley", true);
     Currency b = new Currency("BBB", "Belgian Bing Bun", true);
     Currency c = new Currency("CCC", "Cool Crypto Coin", false);



     CurrencyDatabase myDB;

     @Before
     public void setup() throws Exception {
         myDB= new CurrencyDatabase();
        myDB.currencies=currencies;

         myDB.currencies.add(a);
         myDB.currencies.add(b);
         myDB.currencies.add(c);

     }

     @After
     public void teardown() {
         myDB = null;
     }

     @Test
   public void testGetMajorCurrencies() throws Exception
     {
         List<Currency> majorCurrencies = new ArrayList<Currency>();
         majorCurrencies.add(a);
         majorCurrencies.add(b);

         for (int i = 0; i < myDB.currencies.size() ; i++) {
              assertEquals(myDB.currencies.indexOf(i),majorCurrencies.indexOf(i));
         }

     }


     @Test
     public void testGetCurrencyByCode() throws Exception{

         assertEquals(a, myDB.getCurrencyByCode("AAA"));
     }

     @Test
     public void testCurrencyExists() throws Exception{

         assertTrue(myDB.currencyExists("AAA"));
     }

     @Test
     public void testGetCurrencies() throws Exception{
         List<Currency> allCurrencies = new ArrayList<Currency>();
         allCurrencies.add(a);
         allCurrencies.add(b);
         allCurrencies.add(c);

         for (int i = 0; i < myDB.currencies.size() ; i++) {
             assertEquals(myDB.currencies.indexOf(i),allCurrencies.indexOf(i));
         }
     }

     @Test
     public void testAddCurrency() throws Exception{
         Currency d= new Currency("DDD", "Dubious Denomination", false);
         List<Currency> allCurrencies = new ArrayList<Currency>();
         allCurrencies.add(a);
         allCurrencies.add(b);
         allCurrencies.add(c);
         allCurrencies.add(d);

         myDB.addCurrency(d);

         for (int i = 0; i < myDB.currencies.size() ; i++) {
             assertEquals(myDB.currencies.indexOf(i),allCurrencies.indexOf(i));
         }
     }


     @Test
     public void testDeleteCurrency() throws Exception{

         List<Currency> allCurrencies = new ArrayList<Currency>();
         allCurrencies.add(a);
         allCurrencies.add(c);


         myDB.deleteCurrency("BBB");

         for (int i = 0; i < myDB.currencies.size() ; i++) {
             assertEquals(myDB.currencies.indexOf(i),allCurrencies.indexOf(i));
         }
     }

     @Test
     public void testGetExchangeRate() throws Exception{

         myDB.currencyServer = new ServerDouble();
         ExchangeRate result = myDB.getExchangeRate ("AAA","BBB");

         ExchangeRate myRate = new ExchangeRate(a,b, 1.00 );
         //myRate.timeLastChecked= result.timeLastChecked; //to make them equal, I'm not testing for time

          assertEquals (myRate.rate, result.rate, 0);
         assertEquals (myRate.sourceCurrency, result.sourceCurrency);
         assertEquals (myRate.destinationCurrency, result.destinationCurrency);



     }




 }