package ru.stqa.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.substringAfter;
import static org.junit.Assert.assertTrue;

public class DZ17_2 extends TestBase {

    public ArrayList<String> allCategoryIds = new ArrayList<String>();

    @Test
    public void dz17_2Test() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=0/");

        ArrayList<String> firstCategoryIds = new ArrayList<String>();

        //******************проверка есть ли вложенные подкатегории
        checkIncludeCategories();

        //*************прокликиваем подкатегории
        klickCategories(firstCategoryIds);

    }

    private void klickCategories(ArrayList<String> firstCategoryIds) {
        //*************прокликиваем подкатегории
        for (String categoryId : firstCategoryIds) {
            try { Thread.sleep(1000); } catch (Exception e) { }//пауза
            driver.findElement(By.xpath("//a[@href[contains(.,'catalog&category_id=" + categoryId + "')]]")).click();
            checkIncludeCategories();         //******************проверка есть ли вложенные подкатегории
        }
    }

    public void checkIncludeCategories() {
        //******************проверка есть ли вложенные подкатегории и если подкатегории есть то получаем их
        if (areElementsPresent(By.xpath("//table[@class='dataTable']//td[3]/a[@href[contains(.,'catalog&category_id')]]"))) {
            //****************получаем все подкатегории
            List<WebElement> countSubCategories = driver.findElements
                    (By.xpath("//table[@class='dataTable']//td[3]/a[@href[contains(.,'catalog&category_id')]]"));
            //************получаем у всех подкатегорий id
            ArrayList<String> categoryIds = new ArrayList<String>();/////
            for (WebElement subCategory : countSubCategories) {
                String hrefCategory = subCategory.getAttribute("href");
                //проверяем есть ли категория в массиве и если нет то добавляем ее в массив
                String getId = substringAfter(hrefCategory, "catalog&category_id=");
                if (checkIsContainsId(allCategoryIds, getId)) { //если их нет то добавляем
                    allCategoryIds.add(getId);
                    categoryIds.add(getId);
                }
            }

            //*************прокликиваем подкатегории
            klickCategories(categoryIds);

        }
    }

    //проверка есть ли id в массиве
    public boolean checkIsContainsId(ArrayList<String> allCategoryIds, String getId) {
        if (allCategoryIds.contains(getId)) {
            return false;
        } else {
            return true;
        }
    }
}
