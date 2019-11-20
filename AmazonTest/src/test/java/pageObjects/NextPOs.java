package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NextPOs {
    static WebDriver browser;


    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (Exception e) {}
    }

    public  static boolean loggedIn(){  // returns if the user is logged in or not

        boolean loggedOut =false;
        try {
            loggedOut = browser.findElement(By.linkText("My Account")).isDisplayed();
        } catch (Exception e){}

        return !loggedOut;
    }

    public static void logOut(){  // logs out the user
        browser.findElement(By.className("myAccountlinkactive")).click();
        browser.findElement(By.className("myAccountsignout")).click();
        browser.findElement(By.id("header-logo")).click();
        sleep(2);

    }

    public  static void logIn(){   //logs the user in
        browser.findElement(By.linkText("My Account")).click(); //click account link
        sleep(3);  //give chance for page to load

        //enter email

        browser.findElement(By.id("EmailOrAccountNumber")).sendKeys("procompleter@gmail.com");
        browser.findElement(By.id("Password")).sendKeys("WeakPassw0rd");
        browser.findElement(By.id("SignInNow")).click();
        sleep(4);  //give chance for login to be attempted
        browser.findElement(By.id("header-logo")).click(); // go to home page
    }


    public static  String cartCount(){ //returns the number of items in the cart in string format
        return browser.findElement(By.className("ItemCount")).getText();
    }

    public static void emptyCart(){  // empties a cart from all items
        // go to cart page
        browser.get("https://www.next.com.mt/en/shoppingbag");
        while(cartCount()!="0"){
            try{
                browser.findElement(By.linkText("Remove Item")).click();
            }catch(Exception e){
                browser.get("https://www.next.com.mt/en"); // go back home
                return; }
            sleep(10);  // till it is removed

        }
        browser.get("https://www.next.com.mt/en"); // go back home
    }

    public static  void addItem(){  //adds the item from the product page
        //Add to bag button
        browser.findElement(By.cssSelector(".addToBagCTA")).click();
        browser.findElement(By.id("sli_search_1")).click(); // just to move the focus away
        sleep(5);  //give time to process request


    }


}
