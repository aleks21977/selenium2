//package ru.stqa.selenium;
//
//import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.support.ui.Select;
//
//import java.io.File;
//
//import static org.junit.Assert.assertEquals;
//
//public class DZ12 extends TestBase {
//
//    @Test
//    public void dz12Test() {
//        long now = System.currentTimeMillis();
//        String goodsName = String.format("GoodsName%s", now);
//        File photo = new File("src/test/resources/macbook.jpg");
//
//        login();
//        try{Thread.sleep(1000);}  catch (Exception e){}//пауза
//
//        createNewProduct();
//        try{Thread.sleep(1000);}  catch (Exception e){}//пауза
//
//        fillTabGeneral(now, goodsName, photo);
//        fillTabInformation();
//        fillTabPrices();
//        //try{Thread.sleep(3000);}  catch (Exception e){}//пауза
//
//        driver.findElement(By.name("save")).click();
//
//        String goodsNameOnCatalogPage = driver.findElement(By.xpath("//a[contains(.,'" + goodsName + "')]")).getText();
//        assertEquals(goodsNameOnCatalogPage, goodsName);
//        try{Thread.sleep(4000);}  catch (Exception e){}//пауза
//    }
//
//
//    private void fillTabPrices() {
//        driver.findElement(By.xpath("//a[contains(.,'Prices')]")).click();
//        driver.findElement(By.name("purchase_price")).clear();
//        driver.findElement(By.name("purchase_price")).sendKeys("4");
//        Select purchaseCurrency = new Select(driver.findElement(By.name("purchase_price_currency_code"))); //про Select http://internetka.in.ua/selenium-driver-select/
//        purchaseCurrency.selectByVisibleText("US Dollars");
//        driver.findElement(By.name("prices[USD]")).sendKeys("3");
//        driver.findElement(By.name("prices[EUR]")).sendKeys("2.8");
//        driver.findElement(By.cssSelector("a#add-campaign")).click();
//        driver.findElement(By.name("campaigns[new_1][start_date]")).sendKeys("01.06.2020" + Keys.TAB + "09:00");
//        driver.findElement(By.name("campaigns[new_1][end_date]")).sendKeys("01.11.2020" + Keys.TAB + "09:00" + Keys.TAB + "10");
//    }
//
//    private void fillTabInformation() {
//        driver.findElement(By.xpath("//a[contains(.,'Information')]")).click();
//        Select manufacturerName = new Select(driver.findElement(By.name("manufacturer_id"))); //про Select http://internetka.in.ua/selenium-driver-select/
//        manufacturerName.selectByVisibleText("ACME Corp.");
//        driver.findElement(By.name("keywords")).sendKeys("GoodsKeywords");
//        driver.findElement(By.name("short_description[en]")).sendKeys("Goods Short Description");
//        driver.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys("Goods Text Description1.\nGoods Text Description2.\nGoods Text Description3.");
//        driver.findElement(By.name("head_title[en]")).sendKeys("Goods Head Title");
//        driver.findElement(By.name("meta_description[en]")).sendKeys("Goods Meta Description");
//    }
//
//    private void createNewProduct() {
//        driver.findElement(By.xpath("//span[contains(.,'Catalog')]")).click();
//        driver.findElement(By.cssSelector("a.button[href*=edit_product]")).click();
//    }
//
//    private void fillTabGeneral(long now, String goodsName, File photo) {
//        driver.findElement(By.xpath("//label[contains(.,'Enabled')]")).click();
//        driver.findElement(By.name("name[en]")).sendKeys(goodsName);
//        driver.findElement(By.name("code")).sendKeys(String.format("%s", now));
//        driver.findElement(By.name("product_groups[]")).click();
//        driver.findElement(By.name("quantity")).clear();
//        driver.findElement(By.name("quantity")).sendKeys("1");
//        driver.findElement(By.name("new_images[]")).sendKeys(photo.getAbsolutePath());
//        driver.findElement(By.name("date_valid_from")).sendKeys("11122019");
//        driver.findElement(By.name("date_valid_to")).sendKeys("11122022");
//    }
//
//    private void login() {
//        driver.get("http://localhost/litecart/admin/");
//        driver.findElement(By.name("username")).sendKeys("admin");
//        driver.findElement(By.name("password")).sendKeys("admin");
//        driver.findElement(By.name("login")).click();
//    }
//
//
//
//    @Test
//    public void testCurrentDir() {
//        File currentDir = new File(".");
//        System.out.println(currentDir.getAbsolutePath());
//        File photo = new File("src/test/resources/macbook.jpg");
//        System.out.println(photo.getAbsolutePath());
//        System.out.println(photo.exists());
//    }
//}
