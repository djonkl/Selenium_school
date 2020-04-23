import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestLogin extends MyFirstTest {


    public static void loginTest (WebDriver driver) {
        driver.get(" http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }
}
