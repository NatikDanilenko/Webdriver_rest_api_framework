package cnn_api;

import cnn.core.cnn_www.NewsSearchResult;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class CheckNewsApi {

    private static final String NEWS_RESULT_PATH = "https://search.api.cnn.io/content?size=10;q=bitcoin";

    @Test
    public List<NewsSearchResult> getNewsFromApi() throws IOException {
        List<NewsSearchResult> newsExpectedResult = new ArrayList<NewsSearchResult>();

        String inputLine;
        StringBuffer response = new StringBuffer();

        URL url = new URL(NEWS_RESULT_PATH);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        if (connection.getResponseCode() == 200) {

        BufferedReader in = new BufferedReader(
        new InputStreamReader(connection.getInputStream()));
        while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
        }
        in.close();

        JsonParser jsonParser = new JsonParser();
        JsonElement jsonTree = jsonParser.parse(response.toString());
        JsonObject jsonObject = jsonTree.getAsJsonObject();
        JsonArray results = jsonObject.getAsJsonArray("result");

        for (JsonElement item : results) {
           JsonObject result = item.getAsJsonObject();
           NewsSearchResult newsItem = new NewsSearchResult();
            newsItem.setNewsTitle(result.get("headline").getAsString());
           newsItem.setNewsBody(result.get("body").getAsString().trim());
           newsExpectedResult.add(newsItem);
        }
        }
        return newsExpectedResult;
    }

}

