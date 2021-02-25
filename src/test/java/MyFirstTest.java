import com.google.common.io.Files;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MyFirstTest {

    public static class MyListerner extends AbstractWebDriverEventListener {


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
            File screen = new File("screen" + System.currentTimeMillis() + ".png");
            try {
                Files.copy(tmp, screen);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(screen);
        }
    }


    public static ThreadLocal<EventFiringWebDriver> tlDriver = new ThreadLocal<>();
    public EventFiringWebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new EventFiringWebDriver( new ChromeDriver());
        driver.register(new MyListerner());
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void myFirstTest() {
        //driver.get("http://yandex.ru/");
        //System.out.println(driver.manage().logs().getAvailableLogTypes());
        //driver.manage().logs().get("broswer").forEach(1 -> System.out.println(1));
        TestLogin.loginTest(driver);
        //TestAllAdminSections.startTestAllAdminSections(driver);
        //TestStikers.startTestStikers(driver);
        //TestSort.startTestSort(driver);
        //TestSort.startTestCountryZone(driver);
        //TestThisProduct.startTestThisProduct(driver);
        //TestCreateNewUser.startTestCreateNewUser(driver);
        //TestNewProduct.startTestNewProduct(driver);
        //TestCheckout.startTestCheckout(driver);
        //TestNewWindow.startTestNewWindow(driver);
        TestNoLineLogsInConsole.startTestNoLineLogsInConsole(driver);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}