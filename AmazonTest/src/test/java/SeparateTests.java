package test.system.google;

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

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/users/mark/software/chromedriver.exe");
        browser = new ChromeDriver();
        browser.get("https://www.amazon.co.uk/");
    }

    @After
    public void teardown() {
        browser.quit();
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


        String text= browser.findElement(By.className("nav-line-1")).getText();

        assertEquals("Hello, Sign in", text) ; //browser.getTitle())
    }

}
