package com.biblioteca.model;

public class Loan {
	private int loan_id;
	private int book_id;
	private int user_id;
	private String loan_date;
	private String return_date;
	
	public Loan(int loan_id,int book_id, int user_id,String loan_date, String return_date) {
		this.loan_id=loan_id;
		this.book_id=book_id;
		this.user_id=user_id;
		this.loan_date= loan_date;
		this.return_date=return_date;
	}
	public Loan(int book_id, int user_id,String loan_date, String return_date) {
		
		this.book_id=book_id;
		this.user_id=user_id;
		this.loan_date= loan_date;
		this.return_date=return_date;
	}
	public int getLoan_id() {
		return loan_id;
	}
	public void setLoan_id(int loan_id) {
		this.loan_id = loan_id;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getLoan_date() {
		return loan_date;
	}
	public void setLoan_date(String loan_date) {
		this.loan_date = loan_date;
	}
	public String getReturn_date() {
		return return_date;
	}
	public void setReturn_date(String return_date) {
		this.return_date = return_date;
	}
	@Override
	public String toString() {
		return "Loans [loan_id=" + loan_id + ", book_id=" + book_id + ", user_id=" + user_id + ", loan_date="
				+ loan_date + ", return_date=" + return_date + "]";
	}
	
}
