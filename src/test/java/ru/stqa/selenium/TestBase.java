package ru.stqa.selenium;

import com.google.common.io.Files;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class TestBase {

    public EventFiringWebDriver driver;
    public WebDriverWait wait;
    public int waitTime = 3;
    public BrowserMobProxy proxy;

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

//        //это что то связанное с многопоточностью взял из видео selenium3_l10_m7_proxy
//        if (tlDriver.get() != null) {
//            driver = tlDriver.get();
//            wait = new WebDriverWait(driver, 10);
//            return;
//        }


        proxy = new BrowserMobProxyServer();
        proxy.start();
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);// get the Selenium proxy object
        DesiredCapabilities capabilities = new DesiredCapabilities();// configure it as a desired capability






        //Proxy proxy = new Proxy();
        //proxy.setHttpProxy("localhost:8888");
//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability("proxy", proxy);
//        WebDriver driver = new ChromeDriver(caps);
        FirefoxOptions options = new FirefoxOptions()
                .addPreference("network.proxy.allow_hijacking_localhost", true)
                .setProxy(seleniumProxy);

        //capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

        //ChromeOptions options = new ChromeOptions();
        //options.setExperimentalOption("w3c", false);

        //DesiredCapabilities cap = DesiredCapabilities.chrome();

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);

        //cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        //options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        driver = new EventFiringWebDriver(new FirefoxDriver(options));
        driver.register(new MyListener());
        driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, waitTime);

//        //это что то связанное с многопоточностью взял из видео selenium3_l10_m7_proxy
//        Runtime.getRuntime().addShutdownHook(
//                new Thread(() -> { driver.quit(); driver = null; }));
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