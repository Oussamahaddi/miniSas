package dto;

import java.util.Date;

public class Borrow {

    private String sin_borrower;
    private String isbn_book;
    private Date date_take;
    private Date date_return;

    public Borrow(){};

    public String getSin_borrower() {
        return sin_borrower;
    }

    public void setSin_borrower(String sin_borrower) {
        this.sin_borrower = sin_borrower;
    }

    public String getIsbn_book() {
        return isbn_book;
    }

    public void setIsbn_book(String isbn_book) {
        this.isbn_book = isbn_book;
    }

    public Date getDate_take() {
        return date_take;
    }

    public void setDate_take(Date date_take) {
        this.date_take = date_take;
    }

    public Date getDate_return() {
        return date_return;
    }

    public void setDate_return(Date date_return) {
        this.date_return = date_return;
    }

}
