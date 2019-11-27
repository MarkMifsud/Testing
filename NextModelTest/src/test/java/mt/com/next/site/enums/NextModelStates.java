package mt.com.next.site.enums;

public enum NextModelStates {
    LOGGED_OUT,  //on home page and not logged in
    LOGGED_IN,   // on home page and logged in as user
    VIEW_RESULTS,  //after a search is done the results are being shown
    VIEW_PRODUCT,  // viewing a product's page,  cart is empty
    CART_POPULATED,  // viewing the product page, cart is populated and the same item can be added again (self pointing transition)
    VIEW_CART,  // On the Cart's page
    CHECKOUT  // out of the cart's page and on the checkout page


}
