package dto;

public class Book {
    private String isbn;
    private String title;
    private String auteur;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private static Book instance;

    public Book(){}

    public Book(String isbn, String title, String auteur){
        this.isbn=isbn;
        this.title=title;
        this.auteur=auteur;
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    @Override
    public String toString() {
        return this.isbn + " " + this.title + " " + this.auteur;
    }
}
