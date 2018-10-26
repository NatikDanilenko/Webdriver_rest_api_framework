package cnn_site;

import cnn.core.cnn_www.CnnMainPage;
import cnn.core.cnn_www.NewsSearchResult;
import cnn.core.cnn_www.SearchResultPage;
import cnn_api.CheckNewsApi;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CnnEditionTestsSite {

    private WebDriver driver;

    @Before
    public void setUpDriver() {

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://edition.cnn.com");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void getNewsFromSite() {
        final CnnMainPage cnnMainPage = new CnnMainPage(driver);
        final SearchResultPage searchResultPage = cnnMainPage.searchByText("bitcoin");
        final List<NewsSearchResult> newsActualResult = searchResultPage.getNewsFromSite();
        Assert.assertTrue(newsActualResult != null);
    }

    @Test
    public void compareNewsFromSiteAndApi() {
        final CnnMainPage cnnMainPage = new CnnMainPage(driver);
        final SearchResultPage searchResultPage = cnnMainPage.searchByText("bitcoin");
        final List<NewsSearchResult> newsActualResult = searchResultPage.getNewsFromSite();

        final CheckNewsApi checkNewsApi = new CheckNewsApi();
        List<NewsSearchResult> newsExpectedResult = null;
        try {
            newsExpectedResult = checkNewsApi.getNewsFromApi();
        }
        catch (IOException e)
        {}

        Assert.assertEquals(newsActualResult.size(), newsExpectedResult.size());
        Assert.assertTrue(CollectionUtils.isEqualCollection(newsActualResult, newsExpectedResult));
    }
    @After
    public void driverTeamDown() {driver.close(); }

    }

