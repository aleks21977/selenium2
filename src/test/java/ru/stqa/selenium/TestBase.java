package ru.stqa.selenium;

import com.google.common.io.Files;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public EventFiringWebDriver driver;
    public WebDriverWait wait;
    public int waitTime = 3;

    public static class MyListener extends AbstractWebDriverEventListener {
        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by + " found");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            System.out.println(throwable);
            File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screen = new File("screen-" + System.currentTimeMillis() + ".png");
            try {
                Files.copy(tmp, screen);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(screen);
        }
    }

    @Before
    public void start() {
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.register(new MyListener());
        driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, waitTime);
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
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
}
