package ru.stqa.selenium;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


public class MyFirstTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        FirefoxOptions options = new FirefoxOptions().setLegacy(true);
        options.setBinary(new FirefoxBinary(new File("C:\\Program Files\\Mozilla Firefox ESR\\firefox.exe")));
        WebDriver driver = new FirefoxDriver(options);


//        DesiredCapabilities caps = new DesiredCapabilities();
//        //caps.setCapability(FirefoxDriver.MARIONETTE, false);
//        WebDriver driver = new FirefoxDriver(
//                new FirefoxBinary(new File("C:\\Program Files\\Mozilla Firefox ESR\\firefox.exe")),
//                new FirefoxProfile(), caps);
//        System.out.println(((HasCapabilities) driver).getCapabilities());
//        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 3);

    }

    @Test
    public void myFirstTest() {
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("btnK")).click();
        wait.until(titleIs("webdriver - Поиск в Google"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
