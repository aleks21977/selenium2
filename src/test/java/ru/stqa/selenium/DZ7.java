//package ru.stqa.selenium;
//
//import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Dimension;
//import org.openqa.selenium.WebElement;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.assertTrue;
//
//public class DZ7 extends TestBase {
//
//    @Test
//    public void dz7Test() {
//        driver.get("http://localhost/litecart/admin/");
//        driver.findElement(By.name("username")).sendKeys("admin");
//        driver.findElement(By.name("password")).sendKeys("admin");
//        driver.findElement(By.name("login")).click();
//        try{Thread.sleep(500);}  catch (Exception e){}//пауза
//        List<WebElement> MenuItems = driver.findElements(By.cssSelector("span.name"));
//        List<String> NamesItems = new ArrayList<String>();
//
//        for (WebElement MenuItem : MenuItems) {
//            NamesItems.add(MenuItem.getText());
//        }
//        try{Thread.sleep(500);}  catch (Exception e){}//пауза
//
//        for (String NameItem : NamesItems) {
//            driver.findElement(By.xpath("//span[text()[contains(.,'" + NameItem + "')]]")).click();
//            try{Thread.sleep(500);}  catch (Exception e){}//пауза
//            boolean ElementsPresent = areElementsPresent(By.cssSelector("li#app- li"));
//
//            if (ElementsPresent) {
//                List<WebElement> MenuItemsIncludes = driver.findElements(By.cssSelector("li#app- li"));
//                int size = MenuItemsIncludes.size() + 1;
//
//                for (int i = 1; i < size; ++i) {
//                    driver.findElement(By.xpath("(//li[@id='app-']/ul//span)[" + i + "]")).click();
//                    //try{Thread.sleep(500);}  catch (Exception e){}//пауза
//                    assertTrue(isElementPresent(By.cssSelector("h1")));
//                }
//
//            }
//            //try{Thread.sleep(1000);}  catch (Exception e){}//пауза
//            assertTrue(isElementPresent(By.cssSelector("h1")));
//        }
//        //try{Thread.sleep(2000);}  catch (Exception e){}//пауза
//    }
//
//
//
//}
