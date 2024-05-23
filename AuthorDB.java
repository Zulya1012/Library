package lbr;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthorDB {
    LibraryDB ldb = new LibraryDB();

    public void saveAuthor(Author author) {
        String sql = String.format("INSERT INTO Author (name, lastName) VALUES ('%s', '%s')",
                author.getName(), author.getLastName());
        System.out.println(sql);

        try (Connection conn = ldb.connectionDB(); Statement stmt = conn.createStatement()) {
            int rowsInserted = stmt.executeUpdate(sql);
            System.out.println(rowsInserted);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
