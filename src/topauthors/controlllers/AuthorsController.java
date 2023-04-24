package topauthors.controlllers;

import topauthors.models.AuthorModel;
import java.io.IOException;
import java.net.URISyntaxException;

public class AuthorsController {
    private AuthorModel author;
    public AuthorsController (AuthorModel author) {
        this.author = author;
    }

    public String AuthorSearch () throws URISyntaxException, IOException, InterruptedException {
        return author.AuthorSearch();
    }
}
