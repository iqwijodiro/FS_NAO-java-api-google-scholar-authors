package topauthors.views;

import topauthors.controlllers.AuthorsController;
import topauthors.models.AuthorModel;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class AuthorsView {
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del autor: ");
        String author_id = scanner.nextLine();
        System.out.print("Ingrese el Api Key: ");
        String apiKey = scanner.nextLine();
        System.out.print("Ingrese el lenguaje (en: inglés, es: español, fr: francés, pt: portugués): ");
        String lang = scanner.nextLine();
        System.out.print("¿Cuantos resultados desea mostrar?: ");
        int results = scanner.nextInt();

        String apiURL = "https://serpapi.com/search.json?engine=google_scholar_author";
        String urlSearch = apiURL + "&hl=" + lang + "&api_key=" + apiKey;

        AuthorModel author = new AuthorModel(author_id, apiKey, lang, results, urlSearch);
        AuthorsController authorController = new AuthorsController(author);

        String search = authorController.AuthorSearch();
        System.out.println(search);
    }
}
