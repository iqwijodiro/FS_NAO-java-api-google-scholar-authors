package topauthors.models;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AuthorModel {
    private String id;
    private String apiKey;
    private String lang;
    private int results;
    private String url;

    public AuthorModel (String id, String apiKey, String lang, int results, String url) {
        this.id = id;
        this.apiKey = apiKey;
        this.lang = lang;
        this.results = results;
        this.url = url;
    }

    public String getId() {
        return id;
    }
    public String getApiKey() {
        return apiKey;
    }
    public String getLang() {
        return lang;
    }
    public int getResults() {
        return results;
    }
    public String getUrl() {
        return url;
    }

    public String AuthorSearch () throws URISyntaxException, IOException, InterruptedException {
            String apiURL = url + "&author_id=" + id + "&hl" + lang + "&num=" + results + "&api_key=" + apiKey;
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest getRequest = HttpRequest.newBuilder()
                            .uri(new URI(apiURL))
                            .build();
            HttpResponse<String> response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }
    }
