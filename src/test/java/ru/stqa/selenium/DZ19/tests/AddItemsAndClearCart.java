package ru.stqa.selenium.DZ19.tests;

import org.junit.Test;


public class AddItemsAndClearCart extends TestBase {

    @Test
    public void dz19Test() {
        app.mainPage.goToPage("http://localhost/litecart/");
        for (int x = 1; x < 4; ++x) {
            app.itemPage.addGoodsToCart(x);
        }
        app.cartPage.deleteGoodsInCart();
    }
}
