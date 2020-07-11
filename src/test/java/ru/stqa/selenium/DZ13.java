package ru.stqa.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class DZ13 extends TestBase {

    @Test
    public void dz13Test() {
        goToMainPage();


        for (int x = 1; x < 4; ++x) {

            getElementBy(By.xpath("(//div[@id='slider-wrapper']/..//a[contains(@class,'link')])[1]")).click();
            //try{Thread.sleep(3000);}  catch (Exception e){}//пауза
            //Integer countGoodsInCart = Integer.parseInt(getElementBy(By.cssSelector("span.quantity")).getText()) + 1;
            //System.out.println(countGoodsInCart);
            if (driver.findElements(By.name("options[Size]")).size() > 0) {
                Select purchaseCurrency = new Select(driver.findElement(By.name("options[Size]"))); //про Select http://internetka.in.ua/selenium-driver-select/
                purchaseCurrency.selectByVisibleText("Small");
            }
            getElementBy(By.name("add_cart_product")).click();
            //try{Thread.sleep(3000);}  catch (Exception e){}//пауза
            wait.until(textToBePresentInElement(getElementBy(By.cssSelector("span.quantity")), Integer.toString(x)));
            WebElement countGoodsInCart2 = getElementBy(By.cssSelector("span.quantity"));
            System.out.println(countGoodsInCart2.getText());
            goToMainPage();
        }









        try{Thread.sleep(2000);}  catch (Exception e){}//пауза
    }

    private WebElement getElementBy(By by) {
        return driver.findElement(by);
    }

    private void goToMainPage() {
        driver.get("http://localhost/litecart/");
    }
}
