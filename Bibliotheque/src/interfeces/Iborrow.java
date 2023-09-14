package interfeces;

import dto.Borrow;
import dto.Borrower;

public interface Iborrow  {
    Borrow reserve(Borrow b);
    boolean back(String isbn_book);
}
