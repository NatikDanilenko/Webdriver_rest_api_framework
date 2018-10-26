package cnn.core.cnn_www;

import org.openqa.selenium.WebElement;

public class NewsSearchResult {

    private String newsTitle;
    private String newsBody;


    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsBody() {
        return newsBody;
    }

    public void setNewsBody(String newsBody) {
        this.newsBody = newsBody;
    }
}
