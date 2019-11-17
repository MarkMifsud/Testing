package test.cucumber.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.*;



public class AmazonUk {

    WebDriver browser;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/users/mark/software/chromedriver");
        browser = new ChromeDriver();
    }

    @After
    public void teardown() {
        browser.quit();
    }



    @Given("I am a user on the website")
    public void i_am_a_user_on_the_website() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("I log in using valid credentials")
    public void i_log_in_using_valid_credentials() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("I should be logged in")
    public void i_should_be_logged_in() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }




}
