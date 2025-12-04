package com.biblioteca.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.biblioteca.DAO.BookDAO;
import com.biblioteca.DAO.LoanDAO;
import com.biblioteca.DAO.UserDAO;
import com.biblioteca.model.Book;
import com.biblioteca.model.Loan;
import com.biblioteca.model.User;

public class LibraryService {
	private UserDAO userDAO= new UserDAO();
	private BookDAO bookDAO= new BookDAO();
	private LoanDAO loanDAO= new LoanDAO();
	
// *  ---------------------------------------	user   ---------------------------------------
	public void createUserService(User u) { userDAO.insertUser(u); }
	
	public void createUserService (String name,String email) {
		User u= new User(name,email);
		userDAO.insertUser(u);
	}
	public User getUserProfileService(int id) { return userDAO.getUserById(id); }
	
	public List<User> getAllUsersService() throws SQLException{ return userDAO.getAll(); }
	
	public void removeUserService(int user_id) throws SQLException { userDAO.deleteUser(user_id); }
	
// * ---------------------------------------   book   ---------------------------------------
	
	public void createBookService(Book u) { bookDAO.insertBook(u); }
	
	public void createBookService (String title,String author,int year, boolean available) {
		Book u= new Book(title,author,year,available);
		bookDAO.insertBook(u);
	}
	public Book getBookProfileService(int id) {  return bookDAO.getBookById(id); }
	
	public List<Book> getAllBooksService() throws SQLException{
		return bookDAO.getAll();
	}
	public void removeBookService(int id) throws SQLException { bookDAO.deleteBook(id); }
	
	
	
// * ---------------------------------------   loan   --------------------------------------- 
	/*
	 *  deve 
	 *  trovare il prestito 
	 *  aggiornare il libro disponibile 
	 *  salvere nel prestito la dati di restituzione( o segnarlo come chiuso)
	 *  
	 *  prestito     -> available= false
	 *  restituzione -> available= true
	 */
	
	public void createLoanService(Loan u) {	loanDAO.insertLoan(u);	}
	
	public void createLoanService (int book_id, int user_id,String loan_date, String return_date) {
		Loan u= new Loan(book_id,user_id,loan_date,return_date);
		loanDAO.insertLoan(u);
	}
	public Loan getLoanProfileService(int id) { return loanDAO.getLoanById(id); }
	
	public List<Loan> getAllLoanService() throws SQLException{ return loanDAO.getAll(); }
	
	public void removeLoanService(int id) throws SQLException { loanDAO.deleteLoan(id); 	}
	
	
// ----------------------------- prestito e restituzione 	-------------------------------------------- //
	
	public void faiUnPrestitoBookService( int book_id) { bookDAO.aggiornaPrestito( book_id, false); }
		
	public void returnBookService( int book_id) { bookDAO.aggiornaPrestito( book_id, true); }
	
	public List<Loan> gestisciPrestiti(List<Loan> listaPrestiti){	//String dataPrestito ){
		
		List<Loan> prestitiAttivi= new ArrayList<>();
		for(Loan l: listaPrestiti) {
			Book b= getBookProfileService(l.getBook_id());
			if(b==null) continue;
			
			if(l.getReturn_date()==null) {				// prestito in corso 							
				faiUnPrestitoBookService(b.getId());
				Loan loan= new Loan( l.getBook_id(),l.getUser_id(), l.getLoan_date(), null);
				createLoanService(loan);				// inserisco prestito attivo
				prestitiAttivi.add(loan);
			}
			/*
			else {
				returnBookService(b.getId());
				createLoanService(l);
			}
			*/
		}
		return prestitiAttivi;
	}
	public void gestisciRestituzione(Loan l, String data){
	
		if(l.getReturn_date()==null && l.getLoan_id()!=0) {
			loanDAO.aggiornaDateLoan(l.getLoan_id(),data);
			returnBookService(l.getBook_id());
		}
	}
	// -------------------------------------------------------------------------------------------------	//
	
	
}
