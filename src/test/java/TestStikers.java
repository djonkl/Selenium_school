import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TestStikers {

    public static void startTestStikers(WebDriver driver) {

        driver.get("http://localhost/litecart/");

        List<WebElement> elements = driver.findElements(By.xpath("//li[contains(@class,'product')]"));
        for (WebElement element : elements) {
            if (element.findElements(By.cssSelector(".sticker")).size() != 1)
                System.out.println("element" + element + " haven't sticker or have more 1 sticker");
        }
    }

}
