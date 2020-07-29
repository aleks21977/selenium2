//package ru.stqa.selenium;
//
//import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.apache.commons.lang3.StringUtils.substringAfter;
//import static org.junit.Assert.assertTrue;
//
//public class DZ17_1 extends TestBase {
//
//    @Test
//    public void dz17_1Test() {
//        driver.get("http://localhost/litecart/admin/");
//        driver.findElement(By.name("username")).sendKeys("admin");
//        driver.findElement(By.name("password")).sendKeys("admin");
//        driver.findElement(By.name("login")).click();
//        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=0/");
//
//        //******************проверка есть ли вложенные подкатегории
//        if (areElementsPresent(By.xpath("//table[@class='dataTable']//td[3]/a[@href[contains(.,'catalog&category_id')]]"))) {
//
//            //****************получаем все подкатегории
//            List<WebElement> countSubCategories = driver.findElements
//                    (By.xpath("//table[@class='dataTable']//td[3]/a[@href[contains(.,'catalog&category_id')]]"));
//            //************получаем у всех подкатегорий id
//            ArrayList<String> categoryIds = new ArrayList<String>();
//            for (WebElement subCategory : countSubCategories) {
//                String hrefCategory = subCategory.getAttribute("href");
//                categoryIds.add(substringAfter(hrefCategory, "catalog&category_id="));
//            }
//
//            //*************прокликиваем подкатегории
//            for (String categoryId : categoryIds) {
//                try { Thread.sleep(1500); } catch (Exception e) { }//пауза
//                driver.findElement(By.xpath("//a[@href[contains(.,'catalog&category_id=" + categoryId + "')]]")).click();
//
//                //*****************получаем список всех товаров подкатегории
//                List<WebElement> listGoodsCategories = driver.findElements
//                        (By.xpath("//table[@class='dataTable']//td[3]/a[@href[contains(.,'category_id=" + categoryId + "&product_id=')]]"));
//
//                //******получаем у всех товаров в подкатегории Id
//                ArrayList<String> goodsIds = new ArrayList<String>();
//                for (WebElement goodsCategory : listGoodsCategories) {
//                    String hrefGoods = goodsCategory.getAttribute("href");
//                    goodsIds.add(substringAfter(hrefGoods, "product_id="));
//                }
//                //System.out.println(goodsIds);
//
//                //***********прокликиваем все товары подкатегории
//                for (String goodsId : goodsIds) {
//                    try { Thread.sleep(1000); } catch (Exception e) { }//пауза
//                    driver.findElement(By.xpath("//a[@href[contains(.,'category_id=" + categoryId + "&product_id=" + goodsId + "')]]")).click();
//                    try { Thread.sleep(1000); } catch (Exception e) { }//пауза
//
//                    //***********проверяем что на консоль ничего не выодится
//                    driver.manage().logs().get("browser").forEach(l -> System.out.println(l));
//                    List haveLogsString = driver.manage().logs().get("browser").getAll();
//                    System.out.println("haveLogsString=" + haveLogsString);
//                    assertTrue(haveLogsString.size() == 0);
//                    driver.findElement(By.name("cancel")).click();
//                }
//            }
//        }
//        try { Thread.sleep(3000); } catch (Exception e) { }//пауза
//    }
//}
