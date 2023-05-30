package crawling.application;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class GoogleSearchCrawling {

    public void crawling(String search) {
        System.setProperty("webdriver.chrome.driver", "/Users/jeong-youhwan/Desktop/playGround/chromedriver_mac_arm64/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.google.com");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(search);
        searchBox.submit();

        System.out.println("start");
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("h3")));
        List<WebElement> searchResults = driver.findElements(By.cssSelector("h3"));
        for (int i = 0; i < searchResults.size(); i++) {
            WebElement result = searchResults.get(i);
            System.out.println((i+1) + ". " + result.getText());
        }
        System.out.println("end");

        driver.quit();
    }
}
