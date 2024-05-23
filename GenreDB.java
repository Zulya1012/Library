package lbr;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GenreDB {
    LibraryDB ldb = new LibraryDB();

    public void saveGenre(Genre genre) {
        String sql = String.format("INSERT INTO Genre (name) VALUES ('%s')", genre.getName());
        System.out.println(sql);

        try (Connection conn = ldb.connectionDB(); Statement stmt = conn.createStatement()) {
            int rowsInserted = stmt.executeUpdate(sql);
            System.out.println(rowsInserted);

        
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    genre.setGenreId(generatedKeys.getInt(1));
                    System.out.println(genre.getGenreId());
                } else {
                    throw new SQLException("Creating genre failed, no ID obtained.");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Genre getGenreById(int genreId) {
        String sql = String.format("SELECT * FROM Genre WHERE genreId = %d", genreId);
        System.out.println(sql);

        try (Connection conn = ldb.connectionDB(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                Genre genre = new Genre();
                genre.setGenreId(rs.getInt("genreId"));
                genre.setName(rs.getString("name"));
                return genre;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
