package ru.stqa.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestBase {

    public WebDriver driver;
    public WebDriverWait wait;
    public int waitTime = 3;

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

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, waitTime);
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
