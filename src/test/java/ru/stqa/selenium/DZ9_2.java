package ru.stqa.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DZ9_2 extends TestBase {

    @Test
    public void dz9_2Test() {
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        List<WebElement> CountryItems = driver.findElements(By.xpath("//tr[contains(@class,'row')]//a[not(@title='Edit')]"));
        int CountCountry = CountryItems.size() + 1;
        List<String> CountryZones = new ArrayList<String>();

        for (int i = 1; i < CountCountry; i++) {
            String NamesCity = driver.findElement(By.xpath("//tr[contains(@class,'row')][" + i + "]//a[not(@title='Edit')]")).getText();
            System.out.println(NamesCity);
            try{Thread.sleep(500);}  catch (Exception e){}//пауза
            driver.findElement(By.xpath("//a[contains(.,'" + NamesCity + "')]")).click();

            List<WebElement> NamesZonesCountry = driver.findElements(By.xpath("//option[contains(.,'-- All Zones --')]/../option[@selected='selected']"));

            for (WebElement NameZonesCountry : NamesZonesCountry) {
                CountryZones.add(NameZonesCountry.getText());

                List<String> CountryZonesSort = new ArrayList<String>(CountryZones);
                Collections.sort(CountryZonesSort);
                assertEquals(CountryZones, CountryZonesSort);

            }
            System.out.println(CountryZones.size());
            System.out.println(CountryZones);
            CountryZones.clear();
            driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
            //try{Thread.sleep(2000);}  catch (Exception e){}//пауза
        }
    }
}
