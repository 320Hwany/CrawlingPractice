package crawling.application;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class MelonChartCrawling {

    public void top100Crawling() {
        System.setProperty("webdriver.chrome.driver", "/Users/jeong-youhwan/Desktop/playGround/chromedriver_mac_arm64/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.melon.com/chart/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        List<WebElement> songRows = driver.findElements(By.cssSelector(".service_list_song > table > tbody > tr"));
        for (int i = 0; i < songRows.size(); i++) {
            WebElement songRow = songRows.get(i);
            WebElement titleElement = songRow.findElement(By.cssSelector(".ellipsis.rank01 > span > a"));
            WebElement artistElement = songRow.findElement(By.cssSelector(".ellipsis.rank02 > a"));
            String title = titleElement.getText();
            String artist = artistElement.getText();
            System.out.println((i+1) + ". " + title + " - " + artist);
        }

        driver.quit();
    }

    public void searchSongsByArtist(String artistName) {
        System.setProperty("webdriver.chrome.driver", "/Users/jeong-youhwan/Desktop/playGround/chromedriver_mac_arm64/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.melon.com/chart/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));

        // 검색창에 가수 이름 입력
        WebElement searchInput = driver.findElement(By.cssSelector("#top_search"));
        searchInput.sendKeys(artistName);
        searchInput.sendKeys(Keys.ENTER);

        // 검색 결과 페이지로 이동 후 노래 제목 크롤링
        System.out.println("start");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".service_list_song > table > tbody > tr")));
        List<WebElement> songRows = driver.findElements(By.cssSelector(".service_list_song > table > tbody > tr"));
        for (int i = 0; i < Math.min(10, songRows.size()); i++) {
            WebElement songRow = songRows.get(i);
            WebElement titleElement = songRow.findElement(By.cssSelector(".ellipsis.rank01 > span > a"));
            String title = titleElement.getText();
            System.out.println((i + 1) + ". " + title);
        }
        System.out.println("end");

        driver.quit();
    }
}
