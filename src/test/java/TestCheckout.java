import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCheckout {

    public static void startTestCheckout(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement element;
        driver.get("http://localhost/litecart/en/");
        int quantity = 0;

        while (quantity < 3) {
            driver.findElement(By.cssSelector("li[class*='product']")).click();
            element = driver.findElement(By.cssSelector("button[name='add_cart_product']"));
            element.click();
            element = driver.findElement(By.cssSelector("span.quantity"));
            //ждем обновления кол-ва товаров. возникнет ошибка. обработаем ее и пойдем дальше
            try {
                wait.until(ExpectedConditions.stalenessOf(element));
            } catch (Exception e) {
                e.printStackTrace();
            }
            driver.navigate().refresh();
            //вернемся на главную страницу
            driver.navigate().back();
            quantity = Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getAttribute("textContent"));
        }

        //пошли в корзину
        driver.findElement(By.cssSelector("span.quantity")).click();
        //запомним кол-во столбцов с заполненным именем товара
        int i = driver.findElements(By.cssSelector("td.item")).size();
        //запомним таблицу как элемент, что бы отслеживать ее исчезновение
        while (i > 0) {
            element = driver.findElement(By.cssSelector(".dataTable"));
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[name='remove_cart_item']")))).click();
            //ждем пока исчезнет таблица
            wait.until(ExpectedConditions.stalenessOf(element));
            i = driver.findElements(By.cssSelector("td.item")).size();
        }

        //больше в корзине ловить нечего. пошли на главную
        driver.findElement(By.cssSelector("img[alt='My Store']")).click();
    }
}