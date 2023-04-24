package topauthors;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class MainUI {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        String apiURL = "https://serpapi.com/search.json?";
        String engine = "google_scholar";
        String apiKey = Constants.apiKey;
        String searchQuery = "machine";
        String lang = "es";
        int resultsLength = 10;
        String author = "";
        String source = "";

        String endpoint = apiURL + "engine=" + engine + "&q=" + searchQuery + "&hl=" + lang + "&num=" + "&api_key" + apiKey;

        HttpRequest getRequest = HttpRequest.newBuilder().uri(new URI(endpoint)).build();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(getResponse.body());
    }
}