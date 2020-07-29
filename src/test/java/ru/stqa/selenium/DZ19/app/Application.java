package ru.stqa.selenium.DZ19.app;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium.DZ19.pages.CartPage;
import ru.stqa.selenium.DZ19.pages.ItemPage;
import ru.stqa.selenium.DZ19.pages.MainPage;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;

public class Application {

    private WebDriverWait wait;
    private WebDriver driver;
    public MainPage mainPage;
    public CartPage cartPage;
    public ItemPage itemPage;



    public Application() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        mainPage = new MainPage(driver);
        cartPage = new CartPage(driver);
        itemPage = new ItemPage(driver);
    }

    public void quit() {
        driver.quit();
    }

}
