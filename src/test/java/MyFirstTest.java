import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class MyFirstTest {

    public WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
    }

    @Test
    public void myFirstTest() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.get("http://yandex.ru/");
        TestLogin.loginTest(driver);
        //TestAllAdminSections.startTestAllAdminSections(driver);
        //TestStikers.startTestStikers(driver);
        //TestSort.startTestSort(driver);
        //TestSort.startTestCountryZone(driver);
        //TestThisProduct.startTestThisProduct(driver);
        //TestCreateNewUser.startTestCreateNewUser(driver);
        TestNewProduct.startTestNewProduct(driver);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}