package com.biblioteca.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.biblioteca.model.Book;
import com.biblioteca.model.Loan;
import com.biblioteca.model.User;
import com.biblioteca.service.LibraryService;


public class Main {
	public static void main(String[] args){
	try {
		LibraryService ls= new LibraryService();
		
		User[] utenti= {
				new User("Mario Rossi","mario@example.com"), 
                new User("Luigi Bianchi", "luigi@example.com"),
                new User("Giulia Verdi", "giulia@example.com"),
                new User("Anna Neri", "anna@example.com"),
                new User("Paolo Gialli", "paolo@example.com")
		};
		for(User u: utenti) {
			ls.createUserService(u);
			System.out.println("Inserito nuovo utente "+u.getName());
		}
		User found= ls.getUserProfileService(1);
		if(found!=null) {
	     	System.out.println("Utente trovato: " + found.getName());
        }
		
		Book[] listaLibri= {
				new Book( "I Malavoglia", "Giovanni Verga ", 1881,true),
				new Book( "Il fu Mattia Pascal ", "Luigi Pirandello", 1904, true),
				new Book( "Operatte morali ", "Giacomo Leopardi", 1835,false),
				new Book( "Canne al vento ", "Grazia Deledda",1913 ,false),
				new Book( "Uno, nessuno e centomila", "Luigi Pirandello",1926 , true),
				new Book( "Decameron", "Giovanni Boccaccio",1349 , true)			
		};
		
		for(Book b: listaLibri) {
			ls.createBookService(b);
			System.out.println("Inserito nuovo libro "+b.getTitle());
		}
		Book foundBook=ls.getBookProfileService(1);
		if(foundBook!=null) {
			System.out.println("Libro trovato: " + foundBook.getTitle());
		}
		/*
		List<Loan> prestitiAttivi= new ArrayList<>();
		for(Loan l: listaPrestiti) {
			Book b= ls.getBookProfileService(l.getBook_id());
			
			if(l.getReturn_date()==null) {
			ls.faiUnPrestitoBookService(b.getId());
			Loan loan= new Loan( l.getBook_id(),l.getUser_id(), dataPrestito, null);
			prestitiAttivi.add(loan);
			System.out.println("prestiti attivi " + prestitiAttivi);
			ls.createLoanService(loan);
			
			}
		}
		*/
		
		//Loan[] listaPrestiti= {};
		List<Loan> listaPrestiti= Arrays.asList( 
				new Loan( 3,5 , "2022-09-01", null),
				new Loan( 1,1 , "2022-04-01", "2022-09-13"),
				new Loan( 2,5 , "2022-05-01", "2022-09-11"),
				new Loan( 4,2 , "2022-06-01", "2022-09-21"),
				new Loan( 6,5 , "2022-08-01", null)
				
				
		);
		for(Loan vecchio: listaPrestiti) {
			if(vecchio.getLoan_date()!=null && vecchio.getReturn_date()!=null)
			ls.createLoanService(vecchio);
		}
		
		String dataPrestito = LocalDate.now().toString();	
		List<Loan> prestitiAttivi=ls.gestisciPrestiti(listaPrestiti);
		System.out.println("prestito attivo: " + prestitiAttivi);
		
		Loan foundLoan=ls.getLoanProfileService(2);
		if(foundLoan==null) {
			System.out.println("prestito 2 non esiste: ");
			return;
		}
		
		if(foundLoan!=null) {
			System.out.println("prestito trovato: " + foundLoan.getLoan_id());
			System.out.println("Book ID: " + foundLoan.getBook_id());
		    System.out.println("Return date: " + foundLoan.getReturn_date());
		}
		
		String dataRestituzione= LocalDate.now().toString();
		System.out.println("restituisco il prestito ID=2 ");
		ls.gestisciRestituzione(foundLoan, dataRestituzione);
		
		Loan update= ls.getLoanProfileService(2);
		System.out.println("\ndopo la restituzione: ");
		System.out.println("Loan ID: " + update.getLoan_id());
	    System.out.println("Return date: " + update.getReturn_date());
		
		
    } catch (Exception e) {
        e.printStackTrace();
    }	
		
		
		
	}
}
