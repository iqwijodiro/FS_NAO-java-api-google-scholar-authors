package topauthors.views;

import topauthors.controlllers.AuthorsController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class AuthorsView {
    public static void main (String[] args) throws IOException, InterruptedException, URISyntaxException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the author´s ID: ");
        String id = scanner.nextLine();
        System.out.print("Select search language (en - english, es - spanish... etc)");
        String language = scanner.nextLine();

        AuthorsController controller = new AuthorsController(id, language);
        controller.getAuthor();
    }
}
