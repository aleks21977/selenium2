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
//public class DZ17 extends TestBase {
//
//    public ArrayList<String> allCategoryIds = new ArrayList<String>();
//
//    @Test
//    public void dz17Test() {
//        driver.get("http://localhost/litecart/admin/");
//        driver.findElement(By.name("username")).sendKeys("admin");
//        driver.findElement(By.name("password")).sendKeys("admin");
//        driver.findElement(By.name("login")).click();
//        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=0/");
//
//        ArrayList<String> firstCategoryIds = new ArrayList<String>();
//
//        //**************прокликивам все товары Root категории
//        clickGoods("0");
//
//        //******************проверка есть ли вложенные подкатегории
//        checkIncludeCategories();
//
//        //*************прокликиваем подкатегории
//        klickCategories(firstCategoryIds);
//    }
//
//    private void clickGoods(String categoryId) {
//        //*****************получаем список всех товаров подкатегории
//        List<WebElement> listGoodsCategories = driver.findElements
//                (By.xpath("//table[@class='dataTable']//td[3]/a[@href[contains(.,'category_id=" + categoryId + "&product_id=')]]"));
//
//        //******получаем у всех товаров в подкатегории Id
//        ArrayList<String> goodsIds = new ArrayList<String>();
//        for (WebElement goodsCategory : listGoodsCategories) {
//            String hrefGoods = goodsCategory.getAttribute("href");
//            goodsIds.add(substringAfter(hrefGoods, "product_id="));
//        }
//
//        //***********прокликиваем все товары подкатегории
//        for (String goodsId : goodsIds) {
//            try { Thread.sleep(1000); } catch (Exception e) { }//пауза
//            driver.findElement(By.xpath("//a[@href[contains(.,'category_id=" + categoryId + "&product_id=" + goodsId + "')]]")).click();
//            try { Thread.sleep(1900); } catch (Exception e) { }//пауза
//
//            //***********проверяем что на консоль ничего не выводится
//            driver.manage().logs().get("browser").forEach(l -> System.out.println(l));
//            List haveLogsString = driver.manage().logs().get("browser").getAll();
//            System.out.println("haveLogsString=" + haveLogsString);
//            assertTrue(haveLogsString.size() == 0);
//            driver.findElement(By.name("cancel")).click();
//        }
//    }
//
//
//    private void klickCategories(ArrayList<String> firstCategoryIds) {
//
//        //*************прокликиваем подкатегории
//        for (String categoryId : firstCategoryIds) {
//            try { Thread.sleep(1000); } catch (Exception e) { }//пауза
//            driver.findElement(By.xpath("//a[@href[contains(.,'catalog&category_id=" + categoryId + "')]]")).click();
//
//            clickGoods(categoryId); //прокликивам все товары подкатегории
//
//            checkIncludeCategories(); //проверка есть ли вложенные подкатегории
//        }
//    }
//
//
//    public void checkIncludeCategories() {
//
//        //******************проверка есть ли вложенные подкатегории и если подкатегории есть то получаем их
//        if (areElementsPresent(By.xpath("//table[@class='dataTable']//td[3]/a[@href[contains(.,'catalog&category_id')]]"))) {
//
//            //****************получаем все подкатегории
//            List<WebElement> countSubCategories = driver.findElements
//                    (By.xpath("//table[@class='dataTable']//td[3]/a[@href[contains(.,'catalog&category_id')]]"));
//
//            //************получаем у всех подкатегорий id
//            ArrayList<String> categoryIds = new ArrayList<String>();//массив для прокликывания подкатегорий
//            for (WebElement subCategory : countSubCategories) {
//                String hrefCategory = subCategory.getAttribute("href");
//
//                //проверяем есть ли категория в массиве и если нет то добавляем ее в массив
//                String getId = substringAfter(hrefCategory, "catalog&category_id=");
//                if (checkIsContainsId(allCategoryIds, getId)) { //если их нет то добавляем
//                    allCategoryIds.add(getId);
//                    categoryIds.add(getId);
//                }
//            }
//
//            //*************прокликиваем подкатегории
//            klickCategories(categoryIds);
//        }
//    }
//
//
//    //проверка есть ли id в массиве allCategoryIds
//    public boolean checkIsContainsId(ArrayList<String> allCategoryIds, String Id) {
//        if (allCategoryIds.contains(Id)) {
//            return false;
//        } else {
//            return true;
//        }
//    }
//}
