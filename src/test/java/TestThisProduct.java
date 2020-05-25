import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class TestThisProduct {
    public static void startTestThisProduct(WebDriver driver) {

        driver.get("http://localhost/litecart/en/");
        new WebDriverWait(driver,2,500).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='box-campaigns']//a"))));
        //делаем список для сравнения на странице всех товаров и конкретного товара
        WebElement element = driver.findElement(By.xpath("//div[@id='box-campaigns']//a"));
        List<String> originAttribute = new ArrayList<>(), pageProductAttribute = new ArrayList<>();
        originAttribute.add(element.findElement(By.cssSelector(".name")).getAttribute("textContent"));
        originAttribute.add(element.findElement(By.cssSelector(".regular-price")).getAttribute("textContent"));
        originAttribute.add(element.findElement(By.cssSelector(".campaign-price")).getAttribute("textContent"));
        //проверяем ОБЫЧНУЮ цену
        //проверка на серость
        thisIsGray(toIntegerString(element.findElement(By.cssSelector(".regular-price")).getCssValue("color")));
        //проверка на зачеркнутость
        element.findElement(By.cssSelector(".regular-price")).getCssValue("tagName").equals("S");
        //проверяем АКЦИОННУЮ цену
        //проверка на красный цвет
        thisIsRed(element.findElement(By.cssSelector(".campaign-price")).getCssValue("color"));
        //проверка на жирность
        element.findElement(By.cssSelector(".campaign-price")).getCssValue("tagName").equals("STRONG");
        //сверка размеров шрифта цен
        fontSizeCom(fontSizeArray(element.findElement(By.cssSelector(".campaign-price"))), fontSizeArray(element.findElement(By.cssSelector(".regular-price"))));

        //переходим на страницу товара
        element.click();

        //наполняем список с названием и ценами товара
        pageProductAttribute.add(driver.findElement(By.xpath("//h1")).getAttribute("textContent"));
        pageProductAttribute.add(driver.findElement(By.xpath("//s[@class='regular-price']")).getAttribute("textContent"));
        pageProductAttribute.add(driver.findElement(By.xpath("//strong[@class='campaign-price']")).getAttribute("textContent"));

        //сверяем списки
        System.out.println("eto on: " + originAttribute.equals(pageProductAttribute));

        //проверяем ОБЫЧНУЮ цену
        //проверка на серость
        thisIsGray(toIntegerString(driver.findElement(By.xpath("//s[@class='regular-price']")).getCssValue("color")));
        //проверка на зачеркнутость
        driver.findElement(By.xpath("//s[@class='regular-price']")).getCssValue("tagName").equals("S");
        //проверяем АКЦИОННУЮ цену
        //проверка на красный цвет
        thisIsRed(driver.findElement(By.xpath("//strong[@class='campaign-price']")).getCssValue("color"));
        //проверка на жирность
        driver.findElement(By.xpath("//strong[@class='campaign-price']")).getCssValue("tagName").equals("STRONG");
        //сверка размеров шрифта цен
        fontSizeCom(fontSizeArray(driver.findElement(By.xpath("//strong[@class='campaign-price']"))), fontSizeArray(driver.findElement(By.xpath("//s[@class='regular-price']"))));






    }


    public static String toIntegerString (String string) {
        return string.replaceAll("[a-z]", "").replaceAll("[()]", "").replaceAll("\\s", "");
    }
    public static Boolean thisIsGray (String string) {
        String[] rgb = string.split(",");
        boolean thisIsGrayResult;
        if (rgb[0].equals(rgb[1]) & rgb[1].equals(rgb[2]))
            thisIsGrayResult = true;
        else thisIsGrayResult = false;
        return thisIsGrayResult;
    }

    public static Boolean thisIsRed (String string) {
        String[] rgb = string.split(",");
        boolean thisIsRedResult;
        if (!rgb[0].equals("0") & rgb[1].equals("0") & rgb[2].equals("0"))
            thisIsRedResult = true;
        else thisIsRedResult = false;
        return thisIsRedResult;
    }

    public static int fontSizeArray (WebElement element) {
        return element.getSize().width * element.getSize().height;
    }

    public static boolean fontSizeCom(int i, int j) {
        return i > j;
    }
}
