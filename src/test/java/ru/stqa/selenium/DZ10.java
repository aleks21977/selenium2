package ru.stqa.selenium;

import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DZ10 extends TestBase {

    @Test
    public void testNameGoods() {
        goToLitecart();
        String NameGoodsFirst = getText("div#box-campaigns li:first-child div.name");
        goToGoodsPage();
        String NameGoodsSecond = getText("h1.title");
        assertEquals(NameGoodsFirst, NameGoodsSecond);
    }

    @Test
    public void testPriceGoods() {
        goToLitecart();
        String RegularPriceFirst = getText("div#box-campaigns li:first-child s.regular-price");
        String CampaignPriceFirst = getText("div#box-campaigns li:first-child strong.campaign-price");
        goToGoodsPage();
        String RegularPriceSecond = getText("s.regular-price");
        String CampaignPriceSecond = getText("strong.campaign-price");
        assertEquals(RegularPriceFirst, RegularPriceSecond);
        assertEquals(CampaignPriceFirst, CampaignPriceSecond);
    }

    @Test
    public void testRegularPrice() {
        goToLitecart();
        //проверка что цена перечеркнута
        String OuterHTML = getOuterHTML("div#box-campaigns div.price-wrapper");
        assertTrue(OuterHTML.indexOf("</s>") != -1);

        //проверка что цена серого цвета
        String Color = getColor("div#box-campaigns li:first-child s.regular-price");
        String[] hexValue = colorReplace(Color);
        String actualColor = hexValue(hexValue);
        assertEquals("#777777", actualColor);
    }

    @Test
    public void testCampaignPrice() {
        goToLitecart();
        //проверка что цена жирная
        String OuterHTMLFirst = getOuterHTML("div#box-campaigns div.price-wrapper");
        assertTrue(OuterHTMLFirst.indexOf("</strong>") != -1);
        //проверка что цена красного цвета
        String ColorFirst = getColor("div#box-campaigns li:first-child strong.campaign-price");
        String[] hexValueFirst = colorReplace(ColorFirst);
        String actualColorFirst = hexValue(hexValueFirst);
        assertEquals("#cc0000", actualColorFirst);

        goToGoodsPage();
        //проверка что цена жирная
        try{Thread.sleep(2000);}  catch (Exception e){}//пауза
        String OuterHTML = getOuterHTML("div.information div.price-wrapper");
        assertTrue(OuterHTML.indexOf("</strong>") != -1);
        //проверка что цена красного цвета
        String Color = getColor("strong.campaign-price");
        String[] hexValue = colorReplace(Color);
        String actualColor = hexValue(hexValue);
        assertEquals("#cc0000", actualColor);
    }

    @Test
    public void testLargePrice() {
        goToLitecart();
        String getTextSizeRegular = driver.findElement(By.cssSelector("div#box-campaigns li:first-child s.regular-price")).getCssValue("font-size");
        Float TextSizeRegular = Float.parseFloat(getTextSizeRegular.replace("px", ""));
        String getTextSizeCampaign = driver.findElement(By.cssSelector("div#box-campaigns li:first-child strong.campaign-price")).getCssValue("font-size");
        Float TextSizeCampaign = Float.parseFloat(getTextSizeCampaign.replace("px", ""));
        assertTrue(TextSizeRegular < TextSizeCampaign);

        goToGoodsPage();
        String getTextSizeRegularSecond = driver.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size");
        Float TextSizeRegularSecond = Float.parseFloat(getTextSizeRegularSecond.replace("px", ""));
        String getTextSizeCampaignSecond = driver.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size");
        Float TextSizeCampaignSecond = Float.parseFloat(getTextSizeCampaignSecond.replace("px", ""));
        assertTrue(TextSizeRegularSecond < TextSizeCampaignSecond);


    }


    private String hexValue(String[] hexValue) {
        int hexValue1 = parseInt(0, hexValue);
        hexValue[1] = hexValue[1].trim();
        int hexValue2 = parseInt(1, hexValue);
        hexValue[2] = hexValue[2].trim();
        int hexValue3 = parseInt(2, hexValue);
        String actualColorSecond = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
        return actualColorSecond;
    }

    private String getText(String s) {
        return driver.findElement(By.cssSelector(s)).getText();
    }

    private String[] colorReplace(String color) {
        return color.replace("rgb(", "").replace("rgba(", "").replace(")", "").split(",");
    }

    private String getColor(String s) {
        return driver.findElement(By.cssSelector(s)).getCssValue("color");
    }

    private int parseInt(int i, String[] hexValue) {
        return Integer.parseInt(hexValue[i]);
    }

    private String getOuterHTML(String Selector) {
        return driver.findElement(By.cssSelector(Selector)).getAttribute("outerHTML");
    }

    private void goToGoodsPage() {
        driver.findElement(By.cssSelector("div#box-campaigns li:first-child")).click();
    }

    private void goToLitecart() {
        driver.get("http://localhost/litecart/");
    }
}
