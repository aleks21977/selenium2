package ru.stqa.selenium;


import net.lightbody.bmp.core.har.Har;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


public class MyFirstTest extends TestBase {

//    private WebDriver driver;
//    private WebDriverWait wait;

//    @Before
//    public void start() {
//        FirefoxOptions options = new FirefoxOptions().setLegacy(true);
//        options.setBinary(new FirefoxBinary(new File("C:\\Program Files\\Mozilla Firefox ESR\\firefox.exe")));
//        WebDriver driver = new FirefoxDriver(options);
//        wait = new WebDriverWait(driver, 3);
//    }


    @Test
    public void getBrowserLogsWithProxy() {
        proxy.newHar();
        driver.get("http://selenium2.ru");
        Har har = proxy.endHar();
        har.getLog().getEntries().forEach
                (l -> System.out.println(l.getResponse().getStatus() + ":" + l.getRequest().getUrl()));
    }


    @Test
    public void getBrowserLogs() {
        driver.get("https://www.google.ru/");
        //System.out.println(driver.manage().logs().getAvailableLogTypes());
        driver.manage().logs().get("browser").forEach(l -> System.out.println(l));
        List haveLogsString = driver.manage().logs().get("browser").getAll();
        //LogEntries haveLogs = driver.manage().logs().get("performance");
        //System.out.println("haveLogs");
        System.out.println(haveLogsString);
        //Assert.assertTrue(haveLogs != null);
    }

    @Test
    public void myFirstTest() {
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("_btnK")).click();
        wait.until(titleIs("webdriver - Поиск в Google"));

    }

//    @After
//    public void stop() {
//        driver.quit();
//        driver = null;
//    }
}
