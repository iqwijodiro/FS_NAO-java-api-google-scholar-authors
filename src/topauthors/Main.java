package topauthors;


import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {

        Scanner scanner = new Scanner(System.in);
        HttpClient client = HttpClient.newHttpClient();
        String apiKey = "86680062b634e3e6a00d0efd7a566ff00a11a9e28386609e50f832806c386206";
        String dbUrl = "jdbc:mysql://localhost:3306/top-autores";
        String userDB = "root";
        String passwordDB = "root";
        String baseUrl = "https://serpapi.com/search.json?engine=google_scholar_author";
        int results = 10;

        System.out.print("Enter the author´s ID: ");
        String id = scanner.nextLine();
        System.out.print("Select search language (en - english, es - spanish... etc): ");
        String language = scanner.nextLine();

        String apiURL = baseUrl + "&author_id=" + id + "&hl" + language + "&num=" + results + "&api_key=" + apiKey;

        HttpRequest req = HttpRequest.newBuilder().uri(new URI(apiURL)).build();
        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        String jsonResp = resp.body();
        JSONObject data = new JSONObject(jsonResp);
        String name = data.getJSONObject("author").getString("name");
        String affiliations = data.getJSONObject("author").getString("affiliations");
        String email = data.getJSONObject("author").getString("email");
        String profileUrl = data.getJSONObject("search_metadata").getString("google_scholar_author_url");

        System.out.println(name);
        System.out.println(affiliations);
        System.out.println(email);
        System.out.println(profileUrl);

        try (   Connection connection = DriverManager.getConnection(dbUrl, userDB, passwordDB);
                Statement stmt = connection.createStatement())
        {
            System.out.println("Saving authors into database");
            String sql = "INSERT INTO autor (name, affiliations, email, url) VALUES ('"
                    +name+"', '" +affiliations+ "', '" +email+ "', '" +profileUrl + "')";
            stmt.executeUpdate(sql);
            System.out.println("Author saved in DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}