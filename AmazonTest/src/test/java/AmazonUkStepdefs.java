package test.stepdefs;  // should be deleted

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;


public class AmazonUkStepdefs {

    String text ="";  //any text being read

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (Exception e) {}
    }
    
    public boolean loggedIn(){

        boolean loggedOut =false;
        try {
    loggedOut = browser.findElement(By.linkText("My Account")).isDisplayed();
    } catch (Exception e){}

        return !loggedOut;
    }

    public void logOut(){
        browser.findElement(By.className("myAccountlinkactive")).click();
        browser.findElement(By.className("myAccountsignout")).click();
        browser.findElement(By.id("header-logo")).click();
        sleep(2);

    }

    public void logIn(){
        browser.findElement(By.linkText("My Account")).click(); //click account link
        sleep(3);  //give chance for page to load

        //enter email

        browser.findElement(By.id("EmailOrAccountNumber")).sendKeys("procompleter@gmail.com");
        browser.findElement(By.id("Password")).sendKeys("WeakPassw0rd");
        browser.findElement(By.id("SignInNow")).click();
        sleep(4);  //give chance for login to be attempted
        browser.findElement(By.id("header-logo")).click(); // go to home page
    }

        WebDriver browser;

        @Before
        public void setup(){
            System.setProperty("webdriver.chrome.driver", "C:/users/mark/software/chromedriver.exe");
            browser = new ChromeDriver();
            browser.get("https://www.next.com.mt/en"); // go to the site
        }

        @After
        public void teardown() {
            //text="";
          browser.quit();
        }


    // Scenario Valid Login requires the following 3 tests
    @Given("I am a user on the website")
    public void i_am_a_user_on_the_website() {


        // The user is not logged in, therefore this module should check logout the user if necessary
        // this test is required by Scanarios: Valid Login, Invalid Login

        // USer not logged in is characterised by: <span class="nav-line-1">Hello, Sign in</span>

        if (loggedIn()==true) logOut();


        assertEquals(loggedIn(), false) ;


    }

    @When("I log in using valid credentials")
    public void i_log_in_using_valid_credentials() {
        // Write code here that turns the phrase above into concrete actions


        logIn();

        String text= browser.findElement(By.className("myAccountlinkactive")).getText();
        assertEquals(text,"Mark");

    }

    @Then("I should be logged in")
    public void i_should_be_logged_in() {

        //String text= browser.findElement(By.className("myAccountlinkactive")).getText();
        //assertTrue(text,"Mark");
        assertTrue(loggedIn());
        //browser.quit();
    }



    //  Scenario Invalid Login needs the following 2 tests
    @When("I log in using invalid credentials")
    public void i_log_in_using_invalid_credentials() {

        browser.findElement(By.linkText("My Account")).click(); //click account link
        sleep(3);  //give chance for page to load

        //enter email

        browser.findElement(By.id("EmailOrAccountNumber")).sendKeys("wrong@email.not");
        browser.findElement(By.id("Password")).sendKeys("BadPassw0rd");
        browser.findElement(By.id("SignInNow")).click();
        sleep(4);  //give chance for login to be attempted

        browser.findElement(By.id("header-logo")).click();


        //String text= browser.findElement(By.linkText("My Account")).getText();
        //assertEquals(text,"My Account");
    }

    @Then("I should not be logged in")
    public void i_should_not_be_logged_in() {

        assertTrue(!loggedIn());

        //browser.quit();
    }



    @Given("I am a logged in user on the website")
    public void i_am_a_logged_in_user_on_the_website() {

            if(!loggedIn()) logIn();

        assertTrue(loggedIn());
    }

    @When("I search for a product")
    public void i_search_for_a_product() {

            //enter string in search box
        sleep(5);
        browser.findElement(By.className("SearchBox")).sendKeys("River Island Mid Auth Paperbag Kristy Jeas");
        //click search button
        browser.findElement(By.className("SearchButton")).click();

    }

    @When("I select the first product in the list")
    public void i_select_the_first_product_in_the_list() {

        browser.findElement(By.className("TitleText")).click();
        sleep(2);  //to load page



    }

    @Then("I should see the product details")
    public void i_should_see_the_product_details() {

        String text = browser.findElement(By.className("Title")).getText();
        assertEquals(text,"River Island Mid Auth Paperbag Kristy Jeas");
    }

    @Given("my shopping cart is empty")
    public void my_shopping_cart_is_empty() {





       // throw new cucumber.api.PendingException();
    }

    @When("I view the details of a product")
    public void i_view_the_details_of_a_product() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("I choose to buy the product")
    public void i_choose_to_buy_the_product() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("my shopping cart should contain {int} item")
    public void my_shopping_cart_should_contain_item(Integer int1) {
        // Write code here that turns the phrase above into concrete actions


        throw new cucumber.api.PendingException();
    }

    @When("I add <num-products> products to my shopping cart")
    public void i_add_num_products_products_to_my_shopping_cart() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("my shopping cart should contain <num-products> items")
    public void my_shopping_cart_should_contain_num_products_items() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Given("my shopping cart has {int} products")
    public void my_shopping_cart_has_products(Integer int1) {
       // browser = new ChromeDriver();

        throw new cucumber.api.PendingException();
    }

    @When("I remove the first product in my cart")
    public void i_remove_the_first_product_in_my_cart() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }





}
