package mt.com.next.site;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class NextModel {

    public static WebDriver browser;

    public void setBroswer(){
        browser = new ChromeDriver();

        System.setProperty("webdriver.chrome.driver", "C:/users/mark/software/chromedriver.exe");

        browser.get("https://www.next.com.mt/en"); // go to the site
        browser.manage().window().maximize();
    }


    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (Exception e) {}
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






    public boolean loggedOut=true, loggedIn = false, viewingResults = false, viewingProduct = false, cartPopulated= false, viewingCart= false, checkingOut= false ;


    boolean logIn() {
        if (viewingResults) {
            // Click home page logo
            browser.findElement(By.id("header-logo")).click(); // go to home page
            viewingResults = false;
            loggedIn = true;
        } else if (loggedOut) {
            // log in
            if(browser==null) setBroswer();

            // uncomment the follwong line
            browser.findElement(By.linkText("My Account")).click(); //click account link



            sleep(3);  //give chance for page to load

            //enter email
            browser.findElement(By.id("EmailOrAccountNumber")).clear(); //clear anything it contains
            browser.findElement(By.id("EmailOrAccountNumber")).sendKeys("procompleter@gmail.com");
            browser.findElement(By.id("Password")).sendKeys("WeakPassw0rd");
            browser.findElement(By.id("SignInNow")).click();
            sleep(4);  //give chance for login to be attempted
            browser.findElement(By.id("header-logo")).click(); // go to home page
            loggedOut=false;
            loggedIn = true;

        } else {
            throw new IllegalStateException();
        }
        return true;
    }


    boolean logOut() {
        if (cartCount()!="0") emptyCart();

        if (loggedIn) {
            // log out
            browser.findElement(By.className("myAccountlinkactive")).click();
            browser.findElement(By.className("myAccountsignout")).click();
            browser.findElement(By.id("header-logo")).click();
            sleep(2);

            loggedOut = true;
            loggedIn = false;
        } else {
            throw new IllegalStateException();
        }
        return true;
    }

    boolean search(){
        if (loggedIn) {
            // do a search
            browser.findElement(By.className("SearchBox")).sendKeys("Silver Tone Sparkle Short Necklace");
            //click search button
            browser.findElement(By.className("SearchButton")).click();

            viewingResults = true;
            loggedIn = false;
        }else if (viewingProduct) {
            // do a search
            browser.findElement(By.className("SearchBox")).sendKeys("Silver Tone Sparkle Short Necklace");
            //click search button
            browser.findElement(By.className("SearchButton")).click();

            viewingResults = true;
            viewingProduct = false;

        } else {
            throw new IllegalStateException();
        }
        sleep(2); // give time for the result to return
        return true;
    }


    boolean clickSearchResult() {  // the first item in the search results

        if(viewingResults){
            // go to product page
            browser.findElement(By.className("TitleText")).click();
            sleep(2);  //to load page

            viewingResults =false;
            viewingProduct =true;

        } else {
            throw new IllegalStateException();
        }

        return true;
    }


    boolean addToCart(){

        if(viewingProduct){
            // add item to cart
            //Add to bag button
                browser.findElement(By.cssSelector(".addToBagCTA")).click();
                browser.findElement(By.id("sli_search_1")).click(); // just to move the focus away
                sleep(5);  //give time to process request

            viewingProduct =false;
            cartPopulated=true;

        } else if(cartPopulated) {
            //do nothing
            if (cartCount()!="9") {  //because one can't add more thnan 9 of the same item
                browser.findElement(By.cssSelector(".addToBagCTA")).click();
                browser.findElement(By.id("sli_search_1")).click(); // just to move the focus away
                sleep(5);  //give time to process request
            }
            return true;
        }else {
            throw new IllegalStateException();
        }

        return true;
    }


    boolean goToCart(){

        if(cartPopulated){

            // navigate to the cart
            browser.findElement(By.cssSelector(".nxbtn,.primary" )).click();
            sleep(2);

            cartPopulated=false;
            viewingCart =true;

        } else {
            throw new IllegalStateException();
        }

        return true;
    }


    boolean checkOut(){

        if(viewingCart){
           // check out
            browser.findElement(By.cssSelector(".nxbtn,.primary.GoToCheckout" )).click();
            viewingCart =false;
            checkingOut=true;

        } else {
            throw new IllegalStateException();
        }

        return true;
    }



    public boolean isLoggedIn() { return loggedIn;  }
    public boolean isLoggedOut() { return loggedOut;  }
    public boolean isViewingResults() { return viewingResults;  }
    public boolean isViewingProduct() { return viewingProduct;  }
    public boolean isCartPopulated() { return cartPopulated;  }
    public boolean isViewingCart() { return viewingCart;  }
    public boolean isCheckingOut() { return checkingOut;  }

}
