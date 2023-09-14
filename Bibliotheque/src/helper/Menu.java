package helper;
import dto.Book;
import dto.Borrow;
import dto.Borrower;
import dto.Status;
import implimentation.BookImp;
import implimentation.BorrowImp;
import implimentation.BorrowerImp;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);

    private static Menu instance;

    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    public boolean index() {
        System.out.println(
                "\n" +
                        "┌───────────────────────────────────────────────┐" + "\n" +
                        "│                   MAIN MENU                   │" + "\n" +
                        "├───────────────────────────────────────────────┤" + "\n" +
                        "│ 1 - Show all Books                            │" + "\n" +
                        "│ 2 - Manage a Book                             │" + "\n" +
                        "│ 3 - Add a Borrower                            │" + "\n" +
                        "│ 4 - Borrow a Book                             │" + "\n" +
                        "│ 5 - Search a Book                             │" + "\n" +
                        "│ 6 - Statistics                                │" + "\n" +
                        "│ 7 - Return back a book                        │" + "\n" +
                        "│ 8 - Exit                                      │" + "\n" +
                        "└───────────────────────────────────────────────┘" + "\n"
        );
        System.out.println("Enter you choice : ");
        int s = Integer.parseInt(scanner.nextLine());
        switch (s) {
            case 1 -> showAllBook();
            case 2 -> managerBook();
            case 3 -> addBorrow();
            case 4 -> borrowBook();
            case 5 -> searchBook();
            case 6 -> statistic();
            case 7 -> returnBook();
            default -> {
                System.out.println("********** GooD By ^^ ************");
                return false;
            }
        }
        return true;

    }

    public void managerBook()  {
        System.out.println(
                "\n" +
                        "┌───────────────────────────────────────────────┐" + "\n" +
                        "│               MANAGE A BOOK MENU              │" + "\n" +
                        "├───────────────────────────────────────────────┤" + "\n" +
                        "│ 1 - Add a book                                │" + "\n" +
                        "│ 2 - Update a Book                             │" + "\n" +
                        "│ 3 - Delete a Book                             │" + "\n" +
                        "│ 4 - Back to menu                              │" + "\n" +
                        "└───────────────────────────────────────────────┘" + "\n"
        );
        System.out.println("Enter you choice : ");
        int s = Integer.parseInt(scanner.nextLine());
        switch (s) {
            case 1 -> addBook();
            case 2 -> updateBook();
            case 3 -> deleteBook();
            case 4 -> index();
        }
    }

    Book book = new Book();
    BookImp bookImp = new BookImp();

    public void showAllBook() {
        System.out.println(
                "\n" +
                        "┌─────────────────────────────────────────────┐" + "\n" +
                        "│               All Available Books           │" + "\n" +
                        "└─────────────────────────────────────────────┘"
        );
        for (Book b : bookImp.allBook(Status.Available) ) {
            System.out.println("│ ISBN   : " + b.getIsbn());
            System.out.println("│ Title  : " + b.getTitle());
            System.out.println("│ Author : " + b.getAuteur());
            System.out.println("├─────────────────────────────────────────────┤");
        }

        // Header for Borrowed Books
        System.out.println(
                "\n" +
                        "┌─────────────────────────────────────────────┐" + "\n" +
                        "│               All Borrowed Books            │" + "\n" +
                        "└─────────────────────────────────────────────┘"
        );
        for (Book b : bookImp.allBook(Status.borrowed) ) {
            System.out.println("│ ISBN   : " + b.getIsbn());
            System.out.println("│ Title  : " + b.getTitle());
            System.out.println("│ Author : " + b.getAuteur());
            System.out.println("├─────────────────────────────────────────────┤");
        }
        // Footer
        System.out.println("└─────────────────────────────────────────────┘");

        // Header for lost Books
        System.out.println(
                "\n" +
                        "┌─────────────────────────────────────────────┐" + "\n" +
                        "│               All Lost Books                │" + "\n" +
                        "└─────────────────────────────────────────────┘"
        );
        bookImp.allBook(Status.lost).forEach(b -> {
            System.out.println("│ ISBN   : " + b.getIsbn());
            System.out.println("│ Title  : " + b.getTitle());
            System.out.println("│ Author : " + b.getAuteur());
            System.out.println("├─────────────────────────────────────────────┤");
        });
        // Footer
        System.out.println("└─────────────────────────────────────────────┘");
    }

    public void addBook() {

        System.out.println(
                "\n" +
                        "┌─────────────────────────────────────────────┐" + "\n" +
                        "│              Add a New Book                 │" + "\n" +
                        "└─────────────────────────────────────────────┘"
        );

        System.out.print("│ Enter Book ISBN   : ");
        scanner.nextLine();
        book.setIsbn(scanner.nextLine());
        System.out.print("│ Enter Book Title  : ");
        scanner.nextLine();
        book.setTitle(scanner.nextLine());
        System.out.print("│ Enter Book Author : ");
        scanner.nextLine();
        book.setAuteur(scanner.nextLine());

        System.out.println("└─────────────────────────────────────────────┘");

        if (bookImp.add(book) != null) {
            System.out.println("✓ Book added successfully!");
        } else {
            System.out.println("✗ Failed to add the book. Please try again.");
        }

    }

    public void updateBook(){
        System.out.println(
                "\n" +
                        "┌─────────────────────────────────────────────┐" + "\n" +
                        "│              Update Existing Book           │" + "\n" +
                        "└─────────────────────────────────────────────┘"
        );

        System.out.print("│ Enter Book ISBN to Update : ");

        Book selectedBook = bookImp.select(scanner.nextLine());

        if (selectedBook != null) {
            System.out.print("│ Enter the new title  : ");

            selectedBook.setTitle(scanner.nextLine());

            System.out.print("│ Enter the new author : ");
            selectedBook.setAuteur(scanner.nextLine());

            System.out.println("└─────────────────────────────────────────────┘");

            if (bookImp.update(selectedBook) != null) {
                System.out.println("✓ Book updated successfully!");
            } else {
                System.out.println("✗ Something went wrong. Please try again.");
            }
        } else {
            System.out.println("✗ Book with provided ISBN not found.");
        }
    }

    public void deleteBook(){
        System.out.println(
                "\n" +
                        "┌─────────────────────────────────────────────┐" + "\n" +
                        "│               Delete Existing Book          │" + "\n" +
                        "└─────────────────────────────────────────────┘"
        );

        System.out.print("│ Enter Book ISBN to Delete : ");
        book.setIsbn(scanner.nextLine());

        System.out.println("└─────────────────────────────────────────────┘");
        System.out.println("Are you sure to delete this book ? Y / N");
        String s = scanner.nextLine();
        if (s == "Y") {
            if (bookImp.delete(book) != null) {
                System.out.println("✓ Book deleted successfully!");
            } else {
                System.out.println("problem");
            }
        } else {
            System.out.println("something wrong");
        }


    }

    public void searchBook() {
        System.out.println("************************************");
        System.out.println("*****      Search a Book      ******");
        System.out.println("************************************");
        System.out.println("- Enter Book ISBN Or Title or Author : ");
        String s = scanner.nextLine();
        book.setIsbn(s);
        book.setTitle(s);
        book.setAuteur(s);
        if (bookImp.search(book) != null) {
            System.out.println(bookImp.search(book));
        } else {
            System.out.println("No book with those information found try again ^^");
        }
    }

    Borrower borrower = new Borrower();
    BorrowerImp borrowerImp = new BorrowerImp();
    public void addBorrow() {
        System.out.println("*************************************");
        System.out.println("*****      Add a Borrower     *******");
        System.out.println("*************************************");
        System.out.println("- Enter Borrower SIN : ");
        borrower.setSin(scanner.nextLine());
        System.out.println("- Enter Book First Name : ");
        borrower.setFirst_name(scanner.nextLine());
        System.out.println("- Enter Book Last Name : ");
        borrower.setLast_name(scanner.nextLine());
        System.out.println("- Enter Book Email : ");
        borrower.setEmail(scanner.nextLine());
        System.out.println("- Enter Book Number : ");
        borrower.setNumber(scanner.nextLine());

        if (borrowerImp.add(borrower) != null) {
            System.out.println("Borrower add successfully");
        }
    }

    Borrow borrow = new Borrow();
    BorrowImp borrowImp = new BorrowImp();
    public void borrowBook() {

        System.out.println("*******************************************************");
        System.out.println("************       Borrow a Book       ****************");
        System.out.println("*******************************************************");
        System.out.println("--------- List of borrowers --------");
        for (Borrower b : borrowerImp.select()) {
            System.out.println("----------------------------------------------");
            System.out.println("CIN : " + b.getSin());
            System.out.println("First Name : " +b.getFirst_name());
            System.out.println("Last Name : " + b.getLast_name());
            System.out.println("Email : " +b.getEmail());
            System.out.println("Phone Number : " + b.getNumber());
        }
        System.out.println("----------------------------------------------");
        System.out.println("Enter the Borrower SIN : ");
        borrow.setSin_borrower(scanner.nextLine());
        System.out.println("-------- List of Books Available -----------");
        if (!bookImp.allBook(Status.Available).isEmpty()) {
            for (Book b : bookImp.allBook(Status.Available)) {
                System.out.println("----------------------------------------------");
                System.out.println(b.getIsbn());
                System.out.println(b.getTitle());
                System.out.println(b.getAuteur());
                System.out.println(b.getStatus());
            }
        } else {
            System.out.println("No Book available");
        }
        System.out.println("----------------------------------------------");
        System.out.println("Enter the Book isbn : ");
        borrow.setIsbn_book(scanner.nextLine());
        System.out.println("Enter the Date of return : ");
        String dateString = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Date dateR = Date.valueOf(LocalDate.parse(dateString, formatter));
        borrow.setDate_return(dateR);
        if (borrowImp.reserve(borrow) != null)  {
            System.out.println("The book Borrowed successfully");
        } else {
            System.out.println("something wrong");
        }
    }

    public void statistic() {
        try {
            System.out.println("----------------------------------------------");
            System.out.println("Books Available : " + bookImp.statistique().getInt("Available"));
            System.out.println("----------------------------------------------");
            System.out.println("Books Borrowed : " + bookImp.statistique().getInt("borrowed"));
            System.out.println("----------------------------------------------");
            System.out.println("Books Lost : " + bookImp.statistique().getInt("lost"));
            System.out.println("----------------------------------------------");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void returnBook() {
        System.out.println("*******************************************************");
        System.out.println("************      Return back a book      *************");
        System.out.println("*******************************************************");
        System.out.println("Enter isbn of book : ");
        if (borrowImp.back(scanner.nextLine())) {
            System.out.println("Book has be returned");
        } else {
            System.out.println("Something wrong");
        }
    }

}
