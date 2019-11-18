package test.system.google;

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
        browser.get("https://best.aliexpress.com");
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

        String text= browser.findElement(By.className("account-name")).getText();

        //String text= browser.findElement(By.className("account-unsigned")).getClass().toString();

            //assertEquals( "class org.openqa.selenium.remote.RemoteWebElement", text) ;
        assertEquals( "", text) ;

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


        browser.findElement(By.className("user-account-info")).click(); //find the Sign In button
        browser.findElement(By.className("sign-btn")).click(); //find the Sign In button
        sleep(8);  //give chance for page to load

        //enter email
        browser.findElement(By.className("notice-close")).click(); //find the Sign In button
        //sleep(6);
        browser.findElement(By.id("fm-login-id")).sendKeys("realitysight@gmail.com");
        browser.findElement(By.id("fm-login-password")).sendKeys("WeakPassword123");

        //browser.findElement(By.className("a-icon a-icon-checkbox")).click();
        //<i class="a-icon a-icon-checkbox"></i>

        browser.findElement(By.className("fm-button fm-submit password-login")).click();
        sleep(6);  //give chance for login to be attempted


        //go back home
      //  browser.findElement(By.className("a-link-nav-icon")).click();


        String text= browser.findElement(By.className("account-name")).getText();
        assertEquals(text,"Hello, Bogus");


    }

}
