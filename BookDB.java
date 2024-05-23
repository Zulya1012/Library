package lbr;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookDB {
    LibraryDB ldb = new LibraryDB();

    public void saveBook(Book book) {
        String sql = String.format("INSERT INTO Book (name, page, yearEdition, genreId) VALUES ('%s', %d, %d, %d)",
                book.getName(), book.getPage(), book.getYearEdition(), book.getGenreId());
        System.out.println("Executing SQL: " + sql);

        try (Connection conn = ldb.connectionDB(); Statement stmt = conn.createStatement()) {
            int rowsInserted = stmt.executeUpdate(sql);
            System.out.println(rowsInserted);

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    book.setBookId(generatedKeys.getInt(1));
                    System.out.println( book.getBookId());
                } else {
                    throw new SQLException("Creating book failed, no ID obtained.");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
