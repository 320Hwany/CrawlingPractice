package crawling.controller;

import crawling.application.GoogleSearchCrawling;
import crawling.application.MelonChartCrawling;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CrawlingController {

    private final GoogleSearchCrawling googleSearchCrawling;
    private final MelonChartCrawling melonChartCrawling;

    @GetMapping("/google")
    public void googleSearchCrawling(@RequestParam String search) {
        googleSearchCrawling.crawling(search);
    }

    @GetMapping("/melon/top100")
    public void top100Crawling() {
        melonChartCrawling.top100Crawling();
    }

    @GetMapping("/melon/artist")
    public void melonCrawling(@RequestParam String artistName) {
        melonChartCrawling.searchSongsByArtist(artistName);
    }
}
