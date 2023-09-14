package implimentation;

import dto.Borrow;
import helper.DB;
import interfeces.Iborrow;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BorrowImp implements Iborrow {

    @Override
    public Borrow reserve(Borrow b) {
        Date d = new Date(System.currentTimeMillis());
        try {
            String sql = "insert into borrow (sin_borrower, isbn_book, date_take, date_return) values(?, ?, ?, ?)";
            PreparedStatement preparedStatement = DB.getConn().prepareStatement(sql);
            preparedStatement.setString(1,b.getSin_borrower());
            preparedStatement.setString(2,b.getIsbn_book());
            preparedStatement.setDate(3, (Date) d);
            preparedStatement.setDate(4, (Date) b.getDate_return());
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
    public boolean back(String isbn_book) {
        try {
            String sql = "update book set status = 'Available' where isbn = ?";
            PreparedStatement preparedStatement = DB.getConn().prepareStatement(sql);
            preparedStatement.setString(1, isbn_book);
            int row = preparedStatement.executeUpdate();
            if (row > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
