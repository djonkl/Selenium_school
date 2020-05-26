import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class TestNewWindow {


    public static void startTestNewWindow(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver,10);


        driver.findElement(By.cssSelector("li:nth-child(3) a span.name")).click();
        driver.findElement(By.cssSelector("a[href*='code=AM']:not([title='Edit'])")).click();

        //ищем все ссылки на новые окна
        List<WebElement> linksToNewWindow = driver.findElements(By.cssSelector("i[class*='external-link']"));
        for (int i = 0; i < linksToNewWindow.size() - 1; i++) {
            //текущее окно
            String originalWindow = driver.getWindowHandle();
            //список всех окон
            Set<String> oldWindows = driver.getWindowHandles();
            //кликаем и открываем новое окно
            linksToNewWindow.get(i).click();
            //жду новое окно и сохраняю его
            String newWindow = wait.until(anyWindowOtherThan(oldWindows));
            //перешел на новое окно
            driver.switchTo().window(newWindow);
            //закрыл его
            driver.close();
            //перешел на первоначальное окно
            driver.switchTo().window(originalWindow);
        }
    }

    public static ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver driver) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        }
        ;
    }
}
