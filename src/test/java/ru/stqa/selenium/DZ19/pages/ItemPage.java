package ru.stqa.selenium.DZ19.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class ItemPage extends Page {

    public ItemPage (WebDriver driver) {
        super(driver);
    }

    public void addGoodsToCart(int x) {
        clickByXpath("(//div[@id='slider-wrapper']/..//a[contains(@class,'link')])[1]");
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
}
