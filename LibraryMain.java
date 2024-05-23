package lbr;

import java.util.Scanner;

public class LibraryMain {
    public static void main(String[] args) {
        Book book = new Book();
        Genre genre = new Genre();
        Author author = new Author();
        BookDB db = new BookDB();
        GenreDB gdb = new GenreDB();
        AuthorDB adb = new AuthorDB();

        try (Scanner s = new Scanner(System.in)) {
            System.out.println("1. Add books");
            System.out.println("2. Add genre");
            System.out.println("3. Add author");
            int choice = s.nextInt();
            s.nextLine(); 
            if (choice == 1) {
                System.out.println("Enter book name:");
                String name = s.nextLine();
                System.out.println("Enter book page count:");
                int page = s.nextInt();
                System.out.println("Enter book year edition:");
                int yearEdition = s.nextInt();
                System.out.println("Enter genre id:");
                int genreId = s.nextInt();
                s.nextLine();

          
       
                Genre existingGenre = gdb.getGenreById(genreId);
                if (existingGenre == null) {
                    System.out.println(genreId);
                } else {
                   
                    book.setName(name);
                    book.setPage(page);
                    book.setYearEdition(yearEdition);
                    book.setGenreId(genreId);
                    db.saveBook(book);

                    System.out.println(book.getBookId() + " " + book.getName() + " " + book.getPage() + " " + book.getYearEdition() + " " + book.getGenreId());
                }
            } else if (choice == 2) {
                System.out.println("Enter genre name:");
                String name = s.nextLine();

                genre.setName(name);
                gdb.saveGenre(genre);

                System.out.println(genre.getGenreId() + " " + genre.getName());
            } else if (choice == 3) {
                System.out.println("Enter author name:");
                String name = s.nextLine();
                System.out.println("Enter author last name:");
                String lastName = s.nextLine();

                author.setName(name);
                author.setLastName(lastName);
                adb.saveAuthor(author);

                System.out.println(author.getAuthorId() + " " + author.getName() + " " + author.getLastName());
            } else {
                System.out.println("Invalid choice!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
