package ru.stqa.selenium.DZ19.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Page {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private int timeout = 3;
    public int waitTime = 7;

    public Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }


    public boolean isElementPresent (By locator) {
        try {
            //wait.until((WebDriver d) -> d.findElement(locator)); //явное ожидание l3_m9 6:30
            driver.findElement(locator); //не явное ожидание l3_m9 6:30
            return true;
        } catch (NoSuchElementException ex) { //для явного необходимо исправить l3_m9 6:30
            return false;
        }
    }

    public boolean areElementsPresent (By locator) {
        try {
            return driver.findElements(locator).size() > 0;
        } catch (InvalidSelectorException ex) {
            return false;
        }
    }

    public boolean areElementsNotPresent (By locator) {
        try {
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            return driver.findElements(locator).size() > 0;
        } finally {
            driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
        }
    }

    public void clickByXpath(String s) {
        getElementBy(By.xpath(s)).click();
    }

    public WebElement getElementBy(By by) {
        return driver.findElement(by);
    }

    public void goToPage(String URL) {
        driver.get(URL);
    }
}
