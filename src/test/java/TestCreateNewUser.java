import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Scanner;

public class TestCreateNewUser {

    public static void startTestCreateNewUser(WebDriver driver) {
        driver.get("http://localhost/litecart/en/");
        String email = "newLogin" + (int) ((Math.random()*1000) + 1) + "@test.com" ;
        new WebDriverWait(driver,2,500).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='content']//a[contains(@href,'create')]"))));
        driver.findElement(By.xpath("//div[@class='content']//a[contains(@href,'create')]")).click();
        new WebDriverWait(driver, 2,500).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input[name=tax_id]"))));
        driver.findElement(By.cssSelector("input[name=tax_id]")).sendKeys("newTaxId" + (int) ((Math.random()*1000) + 1));
        driver.findElement(By.cssSelector("input[name=company]")).sendKeys("newCompany" + (int) ((Math.random()*1000) + 1));
        driver.findElement(By.cssSelector("input[name=firstname]")).sendKeys("newFirstName" + (int) ((Math.random()*1000) + 1));
        driver.findElement(By.cssSelector("input[name=lastname]")).sendKeys("newLastName" + (int) ((Math.random()*1000) + 1));
        driver.findElement(By.cssSelector("input[name=address1]")).sendKeys("newAddress1" + (int) ((Math.random()*1000) + 1));
        driver.findElement(By.cssSelector("input[name=address2]")).sendKeys("newAddress2" + (int) ((Math.random()*1000) + 1));
        driver.findElement(By.cssSelector("input[name=postcode]")).sendKeys("" + (int) ((Math.random()*89999) + 10000));
        driver.findElement(By.cssSelector("input[name=city]")).sendKeys("newCity");
        new Select(driver.findElement(By.cssSelector("[name=country_code]"))).selectByVisibleText("United States");
        driver.findElement(By.cssSelector("input[name=email]")).sendKeys(email);
        driver.findElement(By.cssSelector("input[name=phone]")).sendKeys("+1" + (int) ((Math.random()*89999999) + 10000000));
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys("password");
        driver.findElement(By.cssSelector("input[name=confirmed_password]")).sendKeys("password");
        // кликаем создать что бы активировалась область в стране
        driver.findElement(By.cssSelector("button[name=create_account]")).click();
        new WebDriverWait(driver, 2,500).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input[name=tax_id]"))));
        //заново вбиваем пароли
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys("password");
        driver.findElement(By.cssSelector("input[name=confirmed_password]")).sendKeys("password");

        //все. создаем пользователя
        driver.findElement(By.cssSelector("button[name=create_account]")).click();
        //logout
        new WebDriverWait(driver, 2,500).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[contains(@href,'logout')]"))));
        driver.findElement(By.xpath("//a[contains(@href,'logout')]")).click();
        //login
        new WebDriverWait(driver,2,500).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input[name=email]"))));
        driver.findElement(By.cssSelector("input[name=email]")).sendKeys(email);
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys("password");
        driver.findElement(By.cssSelector("button[name=login]")).click();



    }
}
