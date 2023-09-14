package helper;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

    private static String url="jdbc:postgresql://localhost/bibliotheque?user=postgres&password=root";
    private static String userName="postgres";
    private static String password="root";
    private static Connection cnx=null;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cnx= DriverManager.getConnection(url,userName, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConn(){
        return cnx;
    }

}
