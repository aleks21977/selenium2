package ru.stqa.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class DZ11 extends TestBase {


    @Test
    public void dz11Test() {
        long now = System.currentTimeMillis();
        driver.get("http://localhost/litecart/");
        registration(now);
        try{Thread.sleep(1000);}  catch (Exception e){}//пауза

        logout();
        try{Thread.sleep(1000);}  catch (Exception e){}//пауза

        login(now);
        try{Thread.sleep(5000);}  catch (Exception e){}//пауза

        logout();
        try{Thread.sleep(2000);}  catch (Exception e){}//пауза
    }


    private void registration(long now) {
        driver.findElement(By.xpath("//a[contains(.,'New customers click here')]")).click();
        try{Thread.sleep(1000);}  catch (Exception e){}//пауза
        driver.findElement(By.name("firstname")).sendKeys("FirstName");
        driver.findElement(By.name("lastname")).sendKeys("LastName");
        driver.findElement(By.name("address1")).sendKeys("Address");
        String index = (String.format("%s", now)).substring(1, 6);
        driver.findElement(By.name("postcode")).sendKeys(index);
        driver.findElement(By.name("city")).sendKeys("City");
        Select country = new Select(driver.findElement(By.name("country_code"))); //про Select http://internetka.in.ua/selenium-driver-select/
        country.selectByVisibleText("United States");
        Select state = new Select(driver.findElement(By.cssSelector("select[name='zone_code']")));
        int options = state.getOptions().size();
        System.out.println(options);
        int  randomIndex = (int) ( Math.random() * 65 );
        state.selectByIndex(randomIndex);
        driver.findElement(By.name("email")).sendKeys(String.format("%s@mail.ru", now));
        driver.findElement(By.name("phone")).sendKeys("+79151234567");
        driver.findElement(By.name("password")).sendKeys("+79151234567");
        driver.findElement(By.name("confirmed_password")).sendKeys("+79151234567");
        driver.findElement(By.name("create_account")).click();
    }

    private void login(long now) {
        driver.findElement(By.name("email")).sendKeys(String.format("%s@mail.ru", now));
        driver.findElement(By.name("password")).sendKeys("+79151234567");
        driver.findElement(By.name("login")).click();
    }

    private void logout() {
        driver.findElement(By.xpath("//a[contains(.,'Logout')]")).click();
    }
}
