package topauthors.models;

public class AuthorModel {
    private final String name;
    private final String affiliations;
    private final String email;
    private final String url;

    public AuthorModel (String name, String affiliations, String email, String url) {
        this.name = name;
        this.affiliations = affiliations;
        this.email = email;
        this.url = url;
    }
    public String getName() {
        return name;
    }
    public String getAffiliations() {
        return affiliations;
    }
    public String getEmail() {
        return email;
    }
    public String getUrl() {
        return url;
    }
}





