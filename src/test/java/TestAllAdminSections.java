import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestAllAdminSections {

    public static void startTestAllAdminSections(@NotNull WebDriver driver) {
        //вычиляем кол-во главных меню в админке
        for (int i = 1; i <= driver.findElements(By.xpath("//li[@id='app-']")).size() ; i++) {
            driver.findElement(By.xpath("//li[@id='app-'][" + i + "]")).click();
            //вычисляем кол-во подменю в выбранном пункте главного меню
            for (int j = 1; j <= driver.findElements(By.xpath("//li[@id='app-'][" + i + "]//li")).size(); j++ ) {
                driver.findElement(By.xpath("//li[@id='app-'][" + i + "]//li[" + j + "]")).click();
                try {
                    driver.findElement(By.xpath("//h1"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
