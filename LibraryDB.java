package lbr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LibraryDB {
    public Connection connectionDB() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\DB\\database.db");
        System.out.println("Connected to SQLite database");
        return conn;
    }
}