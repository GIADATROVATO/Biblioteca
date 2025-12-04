package com.biblioteca.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biblioteca.db.DBConnection;
import com.biblioteca.model.Book;
import com.biblioteca.model.User;

public class BookDAO {

    private Connection conn;

    public BookDAO() {
        conn = DBConnection.getInstance().getConnection();
    }

    															  // ----------- CREATE -----------
    public void insertBook(Book book) {
        String sql = "INSERT INTO book(title, author, year, available) VALUES (?,?,?,?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getYear());
            stmt.setBoolean(4, book.isAvailable());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }															  // ----------- READ by ID -----------
    public Book getBookById(int book_id) {
        String sql = "SELECT * FROM book WHERE book_id=? ";
        Book book = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, book_id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                book = new Book(
                    rs.getInt("book_id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getInt("year"),
                    rs.getBoolean("available")
                );
            }
        } catch (SQLException e) { e.printStackTrace(); }

        return book;
    }															   // ----------- READ ALL -----------
    public List<Book> getAll() {
        String sql = "SELECT * FROM book";
        List<Book> books = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                books.add(new Book(
                    rs.getInt("book_id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getInt("year"),
                    rs.getBoolean("available")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return books;
        
    }															  // ----------- UPDATE -----------
    public boolean updateBook(Book book) {
        String sql = "UPDATE book SET title=?, author=?, year=?, available=? WHERE book_id=?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getYear());
            stmt.setBoolean(4, book.isAvailable());
            stmt.setInt(5, book.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) { e.printStackTrace(); }

        return false;
    }															   // ----------- DELETE -----------
    public void deleteBook(int book_id) {
        String sql = "DELETE FROM book WHERE book_id=?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, book_id);
            stmt.executeUpdate();
            System.out.println("Libro eliminato");
        } catch (SQLException e) { e.printStackTrace(); }
    }
    
    public void aggiornaPrestito(int book_id, boolean av) {
    	String sql="UPDATE book SET available=?  WHERE book_id=?";
    	 try (PreparedStatement stmt = conn.prepareStatement(sql)) {
    		 stmt.setBoolean(1,av);
    		 stmt.setInt(2, book_id);
    		 stmt.executeUpdate();
             System.out.println("Disponibilità libro aggiornata ");
    	 } catch (SQLException e) {
			e.printStackTrace();
    	 }
    }
}





