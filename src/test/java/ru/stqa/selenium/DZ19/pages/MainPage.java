package ru.stqa.selenium.DZ19.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends Page {

    public MainPage (WebDriver driver) {
        super(driver);
    }

    public void clickByXpath(String s) {
        getElementBy(By.xpath(s)).click();
    }
}
