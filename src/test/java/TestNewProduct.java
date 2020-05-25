import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class TestNewProduct {

    public static void startTestNewProduct(WebDriver driver) {
        new WebDriverWait(driver,2,500).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//li[2]//span[@class='name']"))));
        driver.findElement(By.xpath("//li[2]//span[@class='name']")).click();
        new WebDriverWait(driver,2,5000).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[contains(@href,'edit_product')]"))));
        //перешли на страницу добавления и подождали ее прогрузки
        driver.findElement(By.xpath("//a[contains(@href,'edit_product')]")).click();
        new WebDriverWait(driver,2,500).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1"))));

        String string = "productName";

        File file = new File("src/test/images/newDuck.png");


        //заполняем инфу о товаре
        driver.findElement(By.xpath("//td/label[1]")).click();
        driver.findElement(By.xpath("//input[contains(@name,'name[en]')]")).sendKeys(string);
        driver.findElement(By.xpath("//input[contains(@name,'code')]")).sendKeys("00110011");
        driver.findElement(By.cssSelector("input[type=checkbox][data-name='Rubber Ducks']")).click();
        driver.findElement(By.cssSelector("input[type=checkbox][data-name='Subcategory']")).click();
        driver.findElement(By.cssSelector("input[type=checkbox][value='1-3']")).click();
        driver.findElement(By.cssSelector("input[name=quantity]")).clear();
        driver.findElement(By.cssSelector("input[name=quantity]")).sendKeys(Keys.NUMPAD1);
        driver.findElement(By.cssSelector("input[type=file]")).sendKeys(file.getAbsolutePath());
        driver.findElement(By.cssSelector("input[name='date_valid_from']")).sendKeys("25052020");
        driver.findElement(By.cssSelector("input[name='date_valid_to']")).sendKeys("30062020");

        driver.findElement(By.cssSelector("a[href='#tab-information']")).click();
        new WebDriverWait(driver,2,500).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input[name='meta_description[en]'"))));
        new Select(driver.findElement(By.cssSelector("select[name='manufacturer_id'"))).selectByVisibleText("ACME Corp.");
        driver.findElement(By.cssSelector("input[name='keywords']")).sendKeys("duck red");
        driver.findElement(By.cssSelector("input[name='short_description[en]']")).sendKeys("red duck");
        driver.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys("new original red duck");
        driver.findElement(By.cssSelector("input[name='head_title[en]']")).sendKeys("head title red duck");
        driver.findElement(By.cssSelector("input[name='meta_description[en]']")).sendKeys("meta description red duck");

        driver.findElement(By.cssSelector("a[href='#tab-prices']")).click();
        new WebDriverWait(driver,2,500).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h2[2]"))));

        driver.findElement(By.cssSelector("input[name='purchase_price']")).clear();
        driver.findElement(By.cssSelector("input[name='purchase_price']")).sendKeys(Keys.NUMPAD1);
        new Select(driver.findElement(By.cssSelector("select[name='purchase_price_currency_code']"))).selectByVisibleText("Euros");

        driver.findElement(By.cssSelector("input[name='prices[USD]']")).clear();
        driver.findElement(By.cssSelector("input[name='prices[USD]']")).sendKeys(Keys.NUMPAD1);
        driver.findElement(By.cssSelector("input[name='prices[EUR]']")).clear();
        driver.findElement(By.cssSelector("input[name='prices[EUR]']")).sendKeys(Keys.NUMPAD1);

        driver.findElement(By.cssSelector("button[name='save']")).click();

        System.out.println("imena sovpadayut? " + driver.findElement(By.xpath("//tr[@class='row'][3]/td[3]/a")).getAttribute("textContent").equals(string));

    }
}
