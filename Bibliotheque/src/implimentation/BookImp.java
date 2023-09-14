package implimentation;


import dto.Book;
import dto.Borrower;
import dto.Status;
import helper.DB;
import interfeces.Ibook;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookImp implements Ibook {


    public BookImp() {
    }

    @Override
    public Book select(String isbn) {
        try {
            String sql = "Select * from book where isbn = ?";
            PreparedStatement preparedStatement = DB.getConn().prepareStatement(sql);
            preparedStatement.setString(1, isbn);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Book book = new Book(rs.getString("isbn"), rs.getString("title"), rs.getString("author"));
                return book;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Book search(Book b) {
        try {
            String sql = "Select * from book where isbn = ? OR title = ? OR author = ?";
            PreparedStatement preparedStatement = DB.getConn().prepareStatement(sql);
            preparedStatement.setString(1, b.getIsbn());
            preparedStatement.setString(2, b.getTitle());
            preparedStatement.setString(3, b.getAuteur());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new Book(rs.getString("isbn"), rs.getString("title"), rs.getString("author"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Book> allBook(Status status) {
        try {
            String sql = "select * from book where status = ?";
            PreparedStatement preparedStatement = DB.getConn().prepareStatement(sql);
            preparedStatement.setObject(1, status, Types.OTHER);
            ResultSet rs = preparedStatement.executeQuery();
            List<Book> brs = new ArrayList<Book>();
            while (rs.next()) {
                brs.add(new Book(rs.getString("isbn"), rs.getString("title"), rs.getString("author")));
            }
            return brs;
        } catch (SQLException e) {
        }
        return null;
    }

    @Override
    public ResultSet statistique() {
        try {
            String sql = """
                        SELECT
                            (SELECT count(*) FROM book WHERE status = 'Available') as available,
                            (SELECT count(*) FROM book WHERE status = 'borrowed') as borrowed,
                            (SELECT count(*) FROM book WHERE status = 'lost') as lost;
                    """;
            PreparedStatement preparedStatement = DB.getConn().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return rs;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Book add(Book b) {
        try {
            String sql = "insert into book(isbn, title, author) values (?, ?, ?)";

            PreparedStatement preparedStatement = DB.getConn().prepareStatement(sql);
            preparedStatement.setString(1, b.getIsbn());
            preparedStatement.setString(2, b.getTitle());
            preparedStatement.setString(3, b.getAuteur());
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                return b;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Book delete(Book b) {
        try {
            String sql = "delete from book where isbn = ?";
            PreparedStatement preparedStatement = DB.getConn().prepareStatement(sql);
            preparedStatement.setString(1, b.getIsbn());
            int row = preparedStatement.executeUpdate();
            if (row > 0) {
                return b;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Book update(Book b) {
        try {
            String sql = "update book set title = ?, author = ? where isbn = ?";
            PreparedStatement preparedStatement = DB.getConn().prepareStatement(sql);
            preparedStatement.setString(1, b.getTitle());
            preparedStatement.setString(2, b.getAuteur());
            preparedStatement.setString(3, b.getIsbn());
            int row = preparedStatement.executeUpdate();
            if (row > 0) {
                return b;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


}
