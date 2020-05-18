import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSort {
    public static void startTestSort(WebDriver driver) {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        //подождем, пока прогрузится табличка:)
        new WebDriverWait(driver,5,500).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//tr[@class='row']/td[5]"))));
        //надо 2 списка. 1й оригинальный, 2й такой же, и потом сортируем по алфавиту. в конце сравниваем 2 списка. если одинаковые - ок
        //готовим первый = "оригинальный" список стран
        List<String> originList = new ArrayList<>();
        for (int i = 1; i <= driver.findElements(By.xpath("//tr[@class='row']/td[5]")).size(); i++) {
            originList.add(driver.findElement(By.xpath("//tr[@class='row'][" + i +"]/td[5]")).getAttribute("textContent"));
        }


        //готовим сортированный список
        List<String> sortedList = new ArrayList<>(originList);
        Collections.sort(sortedList);

        //сравниваем списки
        System.out.println("country list is sorted: " + originList.equals(sortedList));

        //ищем страны с ненулевыми зонами
        for (int i = 1; i <= driver.findElements(By.xpath("//tr[@class='row']/td[5]")).size(); i++) {
        int countZones = Integer.parseInt(driver.findElement(By.xpath("//tr[@class='row'][" + i +"]/td[6]")).getAttribute("textContent"));
        if (countZones > 0) {
            driver.findElement(By.xpath("//tr[@class='row'][" + i +"]/td[5]/a")).click();
            new WebDriverWait(driver,2,500).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table[@id='table-zones']"))));
            List<String> origZoneList = new ArrayList<>();
            for (int j = 2; j < driver.findElements(By.xpath("//table[@id='table-zones']//td[3]")).size(); j++) {
                origZoneList.add(driver.findElement(By.xpath("//table[@id='table-zones']/tbody/tr["+ j +"]/td[3]")).getAttribute("textContent"));
            }
            List<String> sortedZoneList = new ArrayList<>(origZoneList);
            Collections.sort(sortedZoneList);
            //Collections.sort(sortedZoneList, Collections.reverseOrder()); для проверки что не всегда true выскакивает
            System.out.println("timezones are sorted: " + origZoneList.equals(sortedZoneList));
        }
        }
    }


    public static void startTestCountryZone(WebDriver driver) {
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        for (int i = 1; i <= driver.findElements(By.xpath("//tr[@class='row']//td[3]/a")).size(); i++ ) {
            new WebDriverWait(driver,5,500).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//form[@name='geo_zones_form']"))));
            driver.findElement(By.xpath("//tr[@class='row']["+ i +"]//td[3]/a")).click();
            new WebDriverWait(driver,5,500).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table[@id='table-zones']//tr"))));
            //создаем оригинальный список
            List<String> originCountryZoneList = new ArrayList<>();
            for (int j = 2; j < driver.findElements(By.xpath("//table[@id='table-zones']//tr")).size(); j++) {
                WebElement element =driver.findElement(By.xpath("//table[@id='table-zones']//tr[" + j +"]/td[3]/select/option[@selected='selected']"));
                originCountryZoneList.add(element.getAttribute("textContent"));
            }

            //дублируем список для сортировочного
            List<String> sortedCountryZoneList = new ArrayList<>(originCountryZoneList);
            //Collections.sort(sortedCountryZoneList, Collections.reverseOrder()); //проверим обраточку
            Collections.sort(sortedCountryZoneList);

            System.out.println("country timezone are sorted: " + originCountryZoneList.equals(sortedCountryZoneList));
            driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        }




    }
}
