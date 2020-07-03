package ru.stqa.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DZ9 extends TestBase {

    @Test
    public void dz9Test() {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        List<WebElement> CountryItems = driver.findElements(By.xpath("//tr[contains(@class,'row')]//a[not(@title='Edit')]"));
        List<String> NamesCites = new ArrayList<String>();
        List<String> NamesCitesWithZones = new ArrayList<String>();
        List<String> CountryZones = new ArrayList<String>();

        for (WebElement CountryItem : CountryItems) {
            NamesCites.add(CountryItem.getText());
        }

        List<String> NamesItemsSort = new ArrayList<String>(NamesCites);
        Collections.sort(NamesItemsSort);
        assertEquals(NamesCites, NamesItemsSort);

        for (WebElement CountryItem : CountryItems) {
            Integer CountCityZones = Integer.parseInt(CountryItem.findElement(By.xpath(".//../../td[6]")).getText());

            if (CountCityZones > 0) {
                NamesCitesWithZones.add(CountryItem.getText());
            }
        }

        for (String NameCityWithZones : NamesCitesWithZones) {
            driver.findElement(By.xpath("//a[contains(.,'" + NameCityWithZones + "')]")).click();
            List<WebElement> CountryWithZones = driver.findElements(By.xpath("//a[@id='remove-zone']/../../td[3]"));
            for (WebElement CountryZone : CountryWithZones) {
                CountryZones.add(CountryZone.getText());
            }

            List<String> CountryZonesSort = new ArrayList<String>(CountryZones);
            Collections.sort(CountryZonesSort);
            assertEquals(CountryZones, CountryZonesSort);

            CountryZones.clear();
            driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        }
    }
}
