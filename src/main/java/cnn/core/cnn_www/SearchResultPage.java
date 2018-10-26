package cnn.core.cnn_www;

import cnn.core.cnn_www.NewsSearchResult;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPage {

    private WebDriver driver;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    static final String NEWS_TITLE_LOCATOR = "h3[@class = 'cnn-search__result-headline']";
    static final String NEWS_BODY_LOCATOR = "div[@class = 'cnn-search__result-body']";

    @FindBys(
            @FindBy(xpath = "//div[@class = 'cnn-search__result-contents']")
    )
    private List<WebElement> newsBlock;

    @FindBy(xpath = "//li[@class = 'facet_item']//label[contains(text(), 'Videos')]")
    private WebElement videoLabel;



    public List<NewsSearchResult> getNewsFromSite() {
        final List<NewsSearchResult> news = new ArrayList<NewsSearchResult>();
        for (WebElement element : newsBlock) {
            NewsSearchResult newsItem = new NewsSearchResult();

            newsItem.setNewsTitle(element.findElement(By.xpath(NEWS_TITLE_LOCATOR)).getText());

            newsItem.setNewsBody(element.findElement(By.xpath(NEWS_BODY_LOCATOR)).getText());
            news.add(newsItem);

        }
        return news;
    }

    public void VideoSelect() {
        videoLabel.click();
    }
}

