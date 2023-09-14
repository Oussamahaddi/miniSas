package implimentation;

import dto.Book;
import dto.Borrower;
import helper.DB;
import interfeces.Iborrower;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowerImp implements Iborrower {


    @Override
    public Borrower add(Borrower b) {
        try {
            String sql = "insert into Borrower(sin, first_name, last_name, email, number) values (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = DB.getConn().prepareStatement(sql);
            preparedStatement.setString(1, b.getSin());
            preparedStatement.setString(2, b.getFirst_name());
            preparedStatement.setString(3, b.getLast_name());
            preparedStatement.setString(4, b.getEmail());
            preparedStatement.setString(5, b.getNumber());
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
    public List<Borrower> select() {
        try {
            String sql = "select * from borrower";
            PreparedStatement preparedStatement = DB.getConn().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            List<Borrower> brs = new ArrayList<Borrower>();
            while (rs.next()) {
                brs.add(new Borrower(rs.getString("sin"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("number")));
            }
            return brs;
        } catch (SQLException e) {
        }
        return null;
    }

}
