import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.util.List;

public class TestNoLineLogsInConsole {




    public static void startTestNoLineLogsInConsole(WebDriver driver) {

        driver.findElement(By.cssSelector("[href *= catalog]")).click();
        driver.findElement(By.cssSelector("[href $= 'catalog&category_id=1']")).click();

        List<WebElement> products = driver.findElements(By.cssSelector("[href *= 'product_id']:not([title='Edit'])"));

        for (int i = 0; i < products.size(); i++) {
            products = driver.findElements(By.cssSelector("[href *= 'product_id']:not([title='Edit'])"));
            products.get(i).click();
            String name = driver.findElement(By.cssSelector("[name = 'name[en]'")).getAttribute("value");
            System.out.println(name);
            List<LogEntry> logs = driver.manage().logs().get("browser").getAll();
            if (logs.size() > 0) {
                System.out.println("Список логов при открытии страницы товара " + name + " : " + logs);
            }
            Assert.assertEquals(0, logs.size());
            driver.navigate().back();
        }

    }


}
