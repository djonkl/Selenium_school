import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class MyFirstTest {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,5);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void myFirstTest() {
        //driver.get("http://yandex.ru/");
        TestLogin.loginTest(driver);
        //TestAllAdminSections.startTestAllAdminSections(driver);
        //TestStikers.startTestStikers(driver);
        //TestSort.startTestSort(driver);
        //TestSort.startTestCountryZone(driver);
        //TestThisProduct.startTestThisProduct(driver);
        //TestCreateNewUser.startTestCreateNewUser(driver);
        //TestNewProduct.startTestNewProduct(driver);
        //TestCheckout.startTestCheckout(driver);
        TestNewWindow.startTestNewWindow(driver);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}