package interfeces;

import dto.Book;
import dto.Status;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface Ibook {
    Book add(Book b);
    Book delete(Book b);
    Book update(Book b);

    Book select(String isbn);

    Book search(Book b);

    List<Book> allBook(Status status);

    ResultSet statistique();

}
