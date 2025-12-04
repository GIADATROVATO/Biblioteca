package com.biblioteca.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biblioteca.db.DBConnection;
import com.biblioteca.model.Loan;

public class LoanDAO {
	private Connection conn;
	public LoanDAO() {
		conn=DBConnection.getInstance().getConnection();
	}
	  															  // ----------- CREATE --------------
	public void insertLoan(Loan loan) {
		String sql="INSERT INTO loan(book_id,user_id,loan_date,return_date) VALUES(?,?,?,?)";
		try (PreparedStatement stmt= conn.prepareStatement(sql)){
			stmt.setInt(1, loan.getBook_id());
			stmt.setInt(2, loan.getUser_id());
			stmt.setString(3, loan.getLoan_date());
			stmt.setString(4, loan.getReturn_date());
			stmt.executeUpdate();
			
		} catch (SQLException e) {e.printStackTrace();
			System.out.println("ERRORE INSERT LOAN");
		}
	}
	
	
	
	
	// ----------- READ by ID -----------
	public Loan getLoanById(int loan_id) {
		String sql="SELECT* FROM loan WHERE loan_id=?";
		Loan loan= null;
		try (PreparedStatement stmt= conn.prepareStatement(sql)){
			stmt.setInt(1, loan_id);
			ResultSet rs= stmt.executeQuery();
			if(rs.next()) {
				loan= new Loan( 
						rs.getInt("loan_id"),
						rs.getInt("book_id"),
						rs.getInt("user_id"),
						rs.getString("loan_date"),
						rs.getString("return_date")
						
						);
			}
		
		} catch (SQLException e) {e.printStackTrace();
		}
		return loan;
	}															  // ----------- READ ALL -----------
	public List<Loan> getAll(){
		String sql="SELECT * FROM loan";
		List<Loan> loans= new ArrayList<>();
		try (PreparedStatement stmt= conn.prepareStatement(sql)){
			ResultSet rs= stmt.executeQuery();
			while(rs.next()) {
				loans.add( new Loan( 
						rs.getInt("loan_id"),
						rs.getInt("book_id"),
						rs.getInt("user_id"),
						rs.getString("loan_date"),
						rs.getString("return_date")			
					));
			}
		} catch (SQLException e) {e.printStackTrace();
		}
		return loans;
	}																  // ----------- UPDATE -----------
	public boolean updateLoan(Loan loan) {
		String sql="UPDATE loan SET book_id=?,user_id=?,loan_date=?, return_date=? WHERE loan_id=?";
		try (PreparedStatement stmt= conn.prepareStatement(sql)){
				stmt.setInt(1, loan.getBook_id());
				stmt.setInt(2, loan.getUser_id());
				stmt.setString(3, loan.getLoan_date());
	            stmt.setString(4, loan.getReturn_date());
	            stmt.setInt(5, loan.getLoan_id());

	            return stmt.executeUpdate() > 0;	
		} catch (SQLException e) {e.printStackTrace();
		}
		return false;
	}																  // ----------- DELETE -----------
	public void deleteLoan(int loan_id) {
		String sql = "DELETE FROM loan WHERE loan_id=?";
	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, loan_id);
	            stmt.executeUpdate();
	            System.out.println("prestito eliminato");
	    } catch (SQLException e) { e.printStackTrace(); }
	}
	public void aggiornaDateLoan(int loan_id, String return_date ) {
		String sql="UPDATE loan SET return_date=? WHERE loan_id=?";
		 try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			 	stmt.setString(1, return_date );
			 	stmt.setInt(2, loan_id);
	            stmt.executeUpdate(); 
	            System.out.println("data restituzione modificata");
	    } catch (SQLException e) { e.printStackTrace(); }
	}
	
}
