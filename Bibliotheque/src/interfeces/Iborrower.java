package interfeces;


import dto.Borrower;

import java.util.List;

public interface Iborrower {
    Borrower add(Borrower b);
    List<Borrower> select();
}
