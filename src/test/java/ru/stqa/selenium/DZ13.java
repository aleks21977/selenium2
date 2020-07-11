package ru.stqa.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;

public class DZ13 extends TestBase {


    @Test
    public void dz13Test() {
        goToPage("http://localhost/litecart/");
        for (int x = 1; x < 4; ++x) {
            addGoodsToCart(x);
        }
        deleteGoodsInCart();
        //try{Thread.sleep(1000);}  catch (Exception e){}//пауза
    }



    private void deleteGoodsInCart() {
        getElementBy(By.xpath("//a[contains(.,'Checkout')]")).click();
        int end = 1;
        if (areElementsNotPresent(By.cssSelector("li.shortcut")) == true) {
            int countLi = driver.findElements(By.cssSelector("li.shortcut")).size();
            end = (end + countLi) - 1;
        }
        for (int i = 0; i < end; ++i) {
            if (areElementsNotPresent(By.xpath("//h2[contains(.,'Order Summary')]")) == false) {
                getElementBy(By.xpath("//a[contains(.,'Checkout')]")).click();
            }
            try{Thread.sleep(5000);}  catch (Exception e){}//пауза
            wait.until(visibilityOfAllElementsLocatedBy(By.xpath("(//button[contains(@name,'remove_cart_item')])[1]")));
            getElementBy(By.xpath("(//button[contains(@name,'remove_cart_item')])[1]")).click();
            WebElement tab = getElementBy(By.xpath("(//tr[@class='footer']//strong)[2]"));
            wait.until(ExpectedConditions.stalenessOf(tab));
        }
    }

    private void addGoodsToCart(int x) {
        getElementBy(By.xpath("(//div[@id='slider-wrapper']/..//a[contains(@class,'link')])[1]")).click();
        //getElementBy(By.xpath("(//div[@id='slider-wrapper']/..//a[contains(@href,'yellow-duck-p-1')])[1]")).click();
        if (areElementsNotPresent(By.name("options[Size]"))) {
            Select purchaseCurrency = new Select(driver.findElement(By.name("options[Size]"))); //про Select http://internetka.in.ua/selenium-driver-select/
            int a = (int) ( Math.random() * 3 ) + 1; //про random https://vertex-academy.com/tutorials/ru/generaciya-sluchajnyx-chisel-v-java/
            purchaseCurrency.selectByIndex(a);
        }
        getElementBy(By.name("add_cart_product")).click();
        wait.until(textToBePresentInElement(getElementBy(By.cssSelector("span.quantity")), Integer.toString(x)));
        WebElement countGoodsInCart2 = getElementBy(By.cssSelector("span.quantity"));
        System.out.println(countGoodsInCart2.getText());
        goToPage("http://localhost/litecart/");
    }

    private WebElement getElementBy(By by) {
        return driver.findElement(by);
    }

    private void goToPage(String URL) {
        driver.get(URL);
    }
}
