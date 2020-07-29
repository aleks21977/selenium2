package ru.stqa.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class DZ8 extends TestBase {
    //$x("//div[contains(@class, 'sticker new') or contains(@class, 'sticker sale')]")
    //li[contains(@class,'product')]
    //div[contains(@class,'sticker')]

    @Test
    public void dz8Test() {
        driver.get("http://localhost/litecart/");
        List<WebElement> Goods = driver.findElements(By.xpath("//li[contains(@class,'product')]"));
        System.out.println(Goods.size());
        //try{Thread.sleep(500);}  catch (Exception e){}//пауза

        for (WebElement OneGoods : Goods) {
            //int NumberElement = OneGoods.findElements(By.xpath(".//div[contains(@class,'sticker')]")).size();
            //System.out.println(NumberElement);
            assertTrue( OneGoods.findElements(By.xpath(".//div[contains(@class,'sticker')]")).size() == 1);
        }
    }
}
