
import cucumber.api.java.en.Then;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.Assert.*;

public class SeparateTests {

    WebDriver browser;

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (Exception e) {}
    }

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/users/mark/software/chromedriver.exe");
        browser = new ChromeDriver();
        browser.get("https://www.next.com.mt/en");
    }

    @After
    public void teardown() {
      //  browser.quit();
    }

    @Test
    public void testSimpleSearch() {

        browser.findElement(By.id("twotabsearchtextbox")).sendKeys("the matrix");

        //browser.findElements()

        browser.findElement(By.className("nav-input")).click();
        //browser.findElement(By.name("b_searchboxSubmit")).click();
        String text= browser.findElement( By.id("glow-ingress-line2")).getText();
        //String text= browser.findElement(By.name("sb_count")).getText();
        assertEquals("Malta", text) ; //browser.getTitle())
        text = browser.findElement(By.linkText("The Matrix")).getClass().toString() ; //  By.className("a-color-state a-text-bold"));

        //WebElement firstSpan =  spans.get(0);
        //text = firstSpan.getText();
        assertEquals("class org.openqa.selenium.remote.RemoteWebElement", text) ; //browser.getTitle())
    }

    @Test
    public void notLoggedIn(){

        String text= browser.findElement(By.linkText("My Account")).getText();

        assertEquals( "My Account", text);

    }

    @Test
    public void badLogIn(){


        browser.findElement(By.id("nav-link-accountList")).click(); //find the Sign In button
        sleep(2);  //give chance for page to laod
        browser.findElement(By.className("nav-action-button")).click(); //find the Sign In button
        //sleep(6);  //give chance for page to laod
        browser.findElement(By.id("ap_email")).sendKeys("an invalid email");
        //browser.findElement(By.className("a-input-text a-span12 auth-autofocus auth-required-field")).sendKeys("an invalid email");


        browser.findElement(By.id("continue")).click();
        sleep(2);  //give chance for login to be attempted
        String text = browser.findElement(By.className("a-alert-heading")).getText();
        assertEquals(text,"There was a problem");


    }

    @Test
    public void LoggingIn(){

        browser.findElement(By.linkText("My Account")).click(); //click account link
        sleep(3);  //give chance for page to load

        //enter email

        browser.findElement(By.id("EmailOrAccountNumber")).sendKeys("procompleter@gmail.com");
        browser.findElement(By.id("Password")).sendKeys("WeakPassw0rd");
        browser.findElement(By.id("SignInNow")).click();
        sleep(4);  //give chance for login to be attempted

        //go back home
      //  browser.findElement(By.className("a-link-nav-icon")).click();


        String text= browser.findElement(By.className("myAccountlinkactive")).getText();
        assertEquals(text,"Mark");


    }


    @Test
            public void search() {
        browser.findElement(By.className("SearchBox")).sendKeys("River Island Mid Auth Paperbag Kristy Jeas");
        //click search button
        browser.findElement(By.className("SearchButton")).click();

        browser.findElement(By.className("TitleText")).click();
        sleep(2);  //to load page

        String text = browser.findElement(By.className("Title")).getText();
        assertEquals(text,"River Island Mid Auth Paperbag Kristy Jeas");
    }


    @Test
    public void ShopCart(){
        String text = browser.findElement(By.className("ItemCount")).getText();
        // assertEquals(text,"0"); // passed

        browser.get("https://www.next.com.mt/en/g83212s14#588790");
    }

    @Test
    public void AssessCart(){

        String text = browser.findElement(By.className("ItemCount")).getText();
      //  if(text!="0") {
            //browser.findElement(By.className("ItemCount")).click();
           // browser.findElement(By.className("view_edit_bag")).submit();
            browser.get("https://www.next.com.mt/en/shoppingbag");

            //verify you ar ein cart page

            browser.findElement(By.linkText("Remove Item")).click();


        //}
        //assertEquals(text,"0"); // passed

       // browser.get("https://www.next.com.mt/en/g83212s14#588790");
    }



    @Test
    public void buyItem(){

        // an item
        browser.get("https://www.next.com.mt/en/style/st445621#984757");


        //Add to bag button
        browser.findElement(By.cssSelector(".addToBagCTA")).click();



    }




}
