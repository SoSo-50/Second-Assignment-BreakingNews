package AP;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Infrastructure {
    private final String apiKey;
    private final String baseUrl = "https://newsapi.org/v2/top-headlines?country=us";

    public Infrastructure(String apiKey) {
        this.apiKey = apiKey;
    }

    // Fetches news data from NewsAPI and returns a list of News objects
    public List<News> fetchNews() throws IOException, InterruptedException {
        List<News> newsList = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "&apiKey=" + apiKey))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Check if the response is successful
        if (response.statusCode() != 200) {
            throw new IOException("API request failed with status code: " + response.statusCode());
        }

        // Parse JSON response
        JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
        if (!jsonResponse.get("status").getAsString().equals("ok")) {
            throw new IOException("API returned error: " + jsonResponse.get("message").getAsString());
        }

        JsonArray articles = jsonResponse.getAsJsonArray("articles");
        for (JsonElement element : articles) {
            JsonObject article = element.getAsJsonObject();
            String title = article.get("title").isJsonNull() ? "No Title" : article.get("title").getAsString();
            String description = article.get("description").isJsonNull() ? "No Description" : article.get("description").getAsString();
            String sourceName = article.getAsJsonObject("source").get("name").isJsonNull() ? "Unknown Source" : article.getAsJsonObject("source").get("name").getAsString();
            String author = article.get("author").isJsonNull() ? "Unknown Author" : article.get("author").getAsString();
            String url = article.get("url").isJsonNull() ? "No URL" : article.get("url").getAsString();
            String publishedAt = article.get("publishedAt").isJsonNull() ? "Unknown Date" : article.get("publishedAt").getAsString();

            newsList.add(new News(title, description, sourceName, author, url, publishedAt));
        }

        return newsList;
    }
}
