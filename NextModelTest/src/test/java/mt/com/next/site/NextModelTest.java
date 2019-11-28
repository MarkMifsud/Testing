package mt.com.next.site;

import junit.framework.Assert;
import enums.NextModelStates;

import nz.ac.waikato.modeljunit.*;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class NextModelTest implements FsmModel  {

    //Defining Interface's systemUnderTest
    private NextModel systemUnderTest = new NextModel();

    //State Variables
    private NextModelStates  modelState = NextModelStates.LOGGED_OUT;
    private boolean loggedOut=true, loggedIn = false, viewingResults = false, viewingProduct = false, cartPopulated= false, viewingCart= false, checkingOut= false ;

    //Interface Method Implementations
    public NextModelStates getState() {
        return modelState;
    }

    public void reset(final boolean yes) {
        if (yes) {
            systemUnderTest = new NextModel();
        }
        modelState = NextModelStates.LOGGED_OUT;
        loggedOut=true; loggedIn = false; viewingResults = false; viewingProduct = false; cartPopulated= false; viewingCart= false; checkingOut= false ;

    }

    //Other methods  (Transitions)

    public boolean logGuard() {  return getState().equals(NextModelStates.LOGGED_OUT);    }
    public @Action void log() {

        systemUnderTest.logIn();
        loggedOut=false;
        loggedIn = true;
        modelState = NextModelStates.LOGGED_IN;

        Assert.assertEquals("The model's loggedIN state does not match the SUT's LoggedIN state.", loggedIn, systemUnderTest.isLoggedIn() );
    }

    public boolean logOutGuard() {  return getState().equals(NextModelStates.LOGGED_IN);    }
    public @Action void logOut() {

        systemUnderTest.logOut();
        loggedIn = false;
        loggedOut = true;
        modelState = NextModelStates.LOGGED_OUT;

        Assert.assertEquals("The model's loggedOUT state does not match the SUT's LoggedOUT state..", loggedOut, systemUnderTest.isLoggedOut() );

    }


    public boolean searchFromHomeGuard() {  return getState().equals(NextModelStates.LOGGED_IN);    }
    public @Action void searchFromHome() {  //doing a search right after log in as opposed to search from product page

        systemUnderTest.search();
        viewingResults = true;
        loggedIn = false;

        modelState = NextModelStates.VIEW_RESULTS;

       Assert.assertEquals("The model's VIewResult state does not match the SUT's VIewResult state.[Search from home]", viewingResults, systemUnderTest.isViewingResults() );
    }


    public boolean searchFromProductGuard() {  return getState().equals(NextModelStates.VIEW_PRODUCT);    }
    public @Action void searchFromProduct() {  //doing a search right after log in as opposed to search from product page

        systemUnderTest.search();
        viewingResults = true;
         viewingProduct = false;

        modelState = NextModelStates.VIEW_RESULTS;

        Assert.assertEquals("The model's loggedOUT state does not match the SUT's LoggedOUT state.[Search from product]", viewingResults, systemUnderTest.isViewingResults() );
    }


    public boolean clickProductGuard() {  return getState().equals(NextModelStates.VIEW_RESULTS);    }
    public @Action void clickProduct() {

        systemUnderTest.clickSearchResult();
        viewingResults = false;
        viewingProduct = true;

        modelState = NextModelStates.VIEW_PRODUCT;

        Assert.assertEquals("The model's VIewProduct state does not match the SUT's VIewProduct state.", viewingProduct, systemUnderTest.isViewingProduct() );
    }


    public boolean goHomeGuard() {  return getState().equals(NextModelStates.VIEW_RESULTS);    }
    public @Action void goHome() {  //From Search Results page, going to the log in state which is equivalent to being logged in and on the home page

        systemUnderTest.logIn();

        viewingResults =   false;
        loggedIn =true;

        modelState = NextModelStates.LOGGED_IN;

        Assert.assertEquals("The model's VIewProduct state does not match the SUT's VIewProduct state.", loggedIn, systemUnderTest.isLoggedIn() );
    }

    public boolean addToCartGuard() {  return getState().equals(NextModelStates.VIEW_PRODUCT);    }
    public @Action void addToCart() {

        systemUnderTest.addToCart();

        viewingProduct =   false;
        cartPopulated =true;

        modelState = NextModelStates.CART_POPULATED;

        Assert.assertEquals("The model's CartPopulated state does not match the SUT's CartPopulated state.[AddToCart]", cartPopulated, systemUnderTest.isCartPopulated() );
    }

    public boolean addToCartAgainGuard() {  return getState().equals(NextModelStates.CART_POPULATED);    }
    public @Action void addToCartAgain() {

        systemUnderTest.addToCart();
        cartPopulated =true;
        modelState = NextModelStates.CART_POPULATED;

        Assert.assertEquals("The model's CartPopulated state does not match the SUT's CartPopulated state.[AddToCartAgain]]", cartPopulated, systemUnderTest.isCartPopulated() );
    }

    public boolean goToCartGuard() {  return getState().equals(NextModelStates.CART_POPULATED);    }
    public @Action void goToCart() {

        systemUnderTest.goToCart();
        cartPopulated =false;
        viewingCart =true;
        modelState = NextModelStates.VIEW_CART;

        Assert.assertEquals("The model's VIewCart state does not match the SUT's VIewCart state.", viewingCart, systemUnderTest.isViewingCart() );
    }



    public boolean checkOutGuard() {  return getState().equals(NextModelStates.VIEW_CART);    }
    public @Action void checkOut() {

        systemUnderTest.checkOut();
        viewingCart =false;
        checkingOut=true;
        modelState = NextModelStates.CHECKOUT;

        Assert.assertEquals("The model's Checkout state does not match the SUT's Checkout state.", checkingOut, systemUnderTest.isCheckingOut() );
    }


    //
    @Test
    public void NextModelTestRunner()   {
        final Tester tester = new GreedyTester(new NextModelTest());
        tester.setRandom(new Random());
        final GraphListener graphListener = tester.buildGraph();
        tester.addListener(new StopOnFailureListener());
        tester.addListener("verbose");
        tester.addCoverageMetric(new TransitionPairCoverage());
        tester.addCoverageMetric(new StateCoverage());
        tester.addCoverageMetric(new ActionCoverage());
        tester.generate(35);
        tester.printCoverage();
    }


}
