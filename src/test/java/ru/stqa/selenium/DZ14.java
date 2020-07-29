//package ru.stqa.selenium;
//
//import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedCondition;
//
//import java.util.List;
//import java.util.Set;
//
//public class DZ14 extends TestBase {
//
//
//    @Test
//    public void dz14Test() {
//        driver.get("http://localhost/litecart/admin/");
//        driver.findElement(By.name("username")).sendKeys("admin");
//        driver.findElement(By.name("password")).sendKeys("admin");
//        driver.findElement(By.name("login")).click();
//        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
//        driver.findElement(By.xpath("(//a[@title='Edit'])[1]")).click();
//        List<WebElement> blanks = driver.findElements(By.cssSelector("table table:not(.dataTable) a[target]"));
//        System.out.println(blanks.size());
//
//        String mainWindow = driver.getWindowHandle();
//        Set<String> oldWindows = driver.getWindowHandles();
//
//        for (WebElement blank : blanks) {
//            blank.click();
//            String newWindow = wait.until(thereIsWindowOtherThan(oldWindows));
//            driver.switchTo().window(newWindow);
//            //try{Thread.sleep(2000);}  catch (Exception e){}//пауза
//            driver.close();
//            driver.switchTo().window(mainWindow);
//        }
//        //try{Thread.sleep(1000);}  catch (Exception e){}//пауза
//    }
//
//
//    private ExpectedCondition<String> thereIsWindowOtherThan(Set<String> oldWindows) {
//        return driver -> {
//            Set<String> handles = driver.getWindowHandles();
//            handles.removeAll(oldWindows);
//            return handles.size() > 0 ? handles.iterator().next() : null;
//        };
//    }
//
////    private ExpectedCondition<String> thereIsWindowOtherThan2(Set<String> oldWindows) {
////        return new ExpectedCondition<String>() {
////            public String apply(WebDriver driver) {
////                Set<String> handles = driver.getWindowHandles();
////                handles.removeAll(oldWindows);
////                return handles.size() > 0 ? handles.iterator().next() : null;
////            }
////        };
////    }
//}
