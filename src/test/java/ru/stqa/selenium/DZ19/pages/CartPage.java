package ru.stqa.selenium.DZ19.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;

public class CartPage extends Page {


    public CartPage (WebDriver driver) {
        super(driver);
    }

    public void deleteGoodsInCart() {
        clickByXpath("//a[contains(.,'Checkout')]");
        int end = 1;
        if (areElementsNotPresent(By.cssSelector("li.shortcut")) == true) {
            int countLi = driver.findElements(By.cssSelector("li.shortcut")).size();
            end = (end + countLi) - 1;
        }
        for (int i = 0; i < end; ++i) {
            if (areElementsNotPresent(By.xpath("//h2[contains(.,'Order Summary')]")) == false) {
                clickByXpath("//a[contains(.,'Checkout')]");
            }
            try{Thread.sleep(5000);}  catch (Exception e){}//пауза
            wait.until(visibilityOfAllElementsLocatedBy(By.xpath("(//button[contains(@name,'remove_cart_item')])[1]")));
            clickByXpath("(//button[contains(@name,'remove_cart_item')])[1]");
            WebElement tab = getElementBy(By.xpath("(//tr[@class='footer']//strong)[2]"));
            wait.until(ExpectedConditions.stalenessOf(tab));
        }
    }
}
