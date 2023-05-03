package topauthors.controlllers;

import org.json.JSONObject;
import topauthors.Main;
import topauthors.models.AuthorModel;

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
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthorsController {
   private final String id;
   private final String language;

   public AuthorsController (String id, String language) {
       this.id = id;
       this.language = language;
   }

   public void getAuthor() throws IOException, InterruptedException, URISyntaxException {
       HttpClient client = HttpClient.newHttpClient();
       String apiKey = "86680062b634e3e6a00d0efd7a566ff00a11a9e28386609e50f832806c386206";

       String baseUrl = "https://serpapi.com/search.json?engine=google_scholar_author";

       int results = 10;

       String apiURL = baseUrl + "&author_id=" + id + "&hl" + language + "&num=" + results + "&api_key=" + apiKey;

       HttpRequest req = HttpRequest.newBuilder().uri(new URI(apiURL)).build();
       HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());

       String jsonResp = resp.body();
       JSONObject data = new JSONObject(jsonResp);

       String name = data.getJSONObject("author").getString("name");
       String affiliations = data.getJSONObject("author").getString("affiliations");
       String email = data.getJSONObject("author").getString("email");
       String profileUrl = data.getJSONObject("search_metadata").getString("google_scholar_author_url");

       AuthorModel author = new AuthorModel(name, affiliations, email, profileUrl);

       saveAuthor(author);

   }

   private void saveAuthor(AuthorModel author) {
       String dbUrl = "jdbc:mysql://localhost:3306/top-autores";
       String userDB = "root";
       String passwordDB = "root";

       try (Connection connection = DriverManager.getConnection(dbUrl, userDB, passwordDB);
       Statement stmt = connection.createStatement())
       {
           System.out.println("Saving authors into database");
           String sql = "INSERT INTO autor (name, affiliations, email, url) VALUES ('" +
                   author.getName() + "', '" +
                   author.getAffiliations() + "', '" +
                   author.getEmail() + "', '" +
                   author.getUrl() + "')";
           stmt.executeUpdate(sql);
           System.out.println("Author saved in DB");
       } catch (SQLException ex) {
           ex.printStackTrace();
       }
   }
}
