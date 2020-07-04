package ru.stqa.selenium;

import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DZ10 extends TestBase {

    @Test
    public void testNameGoods() {
        goToLitecart();
        String NameGoodsFirst = driver.findElement(By.cssSelector("div#box-campaigns li:first-child div.name")).getText();
        goToGoodsPage();
        String NameGoodsSecond = driver.findElement(By.cssSelector("h1.title")).getText();
        assertEquals(NameGoodsFirst, NameGoodsSecond);
    }

    @Test
    public void testPriceGoods() {
        goToLitecart();
        String RegularPriceFirst = driver.findElement(By.cssSelector("div#box-campaigns li:first-child s.regular-price")).getText();
        String CampaignPriceFirst = driver.findElement(By.cssSelector("div#box-campaigns li:first-child strong.campaign-price")).getText();
        goToGoodsPage();
        String RegularPriceSecond = driver.findElement(By.cssSelector("s.regular-price")).getText();
        String CampaignPriceSecond = driver.findElement(By.cssSelector("strong.campaign-price")).getText();
        assertEquals(RegularPriceFirst, RegularPriceSecond);
        assertEquals(CampaignPriceFirst, CampaignPriceSecond);
    }

    @Test
    public void testCampaignPrice() {
        goToLitecart();

        //проверка что цена перечеркнута
        String OuterHTML = driver.findElement(By.cssSelector("div#box-campaigns div.price-wrapper")).getAttribute("outerHTML");
        assertTrue(OuterHTML.indexOf("</s>") != -1);

        //проверка что цена серого цвета
        String Color = driver.findElement(By.cssSelector("div#box-campaigns li:first-child s.regular-price")).getCssValue("color");
        String[] hexValue = Color.replace("rgba(", "").replace(")", "").split(",");
        int hexValue1=Integer.parseInt(hexValue[0]);
        hexValue[1] = hexValue[1].trim();
        int hexValue2=Integer.parseInt(hexValue[1]);
        hexValue[2] = hexValue[2].trim();
        int hexValue3=Integer.parseInt(hexValue[2]);
        String actualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
        assertEquals("#777777", actualColor);
    }

    @Test
    public void testRegularPrice() {
        goToLitecart();

        //проверка что цена жирная
        String OuterHTMLFirst = driver.findElement(By.cssSelector("div#box-campaigns div.price-wrapper")).getAttribute("outerHTML");
        assertTrue(OuterHTMLFirst.indexOf("</strong>") != -1);

        //проверка что цена красного цвета
        String ColorFirst = driver.findElement(By.cssSelector("div#box-campaigns li:first-child strong.campaign-price")).getCssValue("color");
        String[] hexValueFirst = ColorFirst.replace("rgba(", "").replace(")", "").split(",");
        int hexValue1First =Integer.parseInt(hexValueFirst[0]);
        hexValueFirst[1] = hexValueFirst[1].trim();
        int hexValue2First=Integer.parseInt(hexValueFirst[1]);
        hexValueFirst[2] = hexValueFirst[2].trim();
        int hexValue3First=Integer.parseInt(hexValueFirst[2]);
        String actualColorFirst = String.format("#%02x%02x%02x", hexValue1First, hexValue2First, hexValue3First);
        assertEquals("#cc0000", actualColorFirst);

        goToGoodsPage();

        //проверка что цена жирная
        String OuterHTML = driver.findElement(By.cssSelector("div.information div.price-wrapper")).getAttribute("outerHTML");
        assertTrue(OuterHTML.indexOf("</strong>") != -1);

        //проверка что цена красного цвета
        String Color = driver.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color");
        String[] hexValue = Color.replace("rgba(", "").replace(")", "").split(",");
        int hexValue1=Integer.parseInt(hexValue[0]);
        hexValue[1] = hexValue[1].trim();
        int hexValue2=Integer.parseInt(hexValue[1]);
        hexValue[2] = hexValue[2].trim();
        int hexValue3=Integer.parseInt(hexValue[2]);
        String actualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
        assertEquals("#cc0000", actualColor);

    }

    private void goToGoodsPage() {
        driver.findElement(By.cssSelector("div#box-campaigns li:first-child")).click();
    }

    private void goToLitecart() {
        driver.get("http://localhost/litecart/");
    }
}
