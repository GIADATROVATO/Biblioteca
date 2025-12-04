package com.biblioteca.model;

public class Book {
	private int book_id;
	private String title;
	private String author;
	private int year;
	private boolean available;
	
	public Book(int book_id,String title,String author,int year, boolean available){
		this.book_id=book_id;
		this.title=title;
		this.author=author;
		this.year=year;
		this.available=available;
	}
	public Book(String title,String author,int year, boolean available){
		this.title=title;
		this.author=author;
		this.year=year;
		this.available=available;
	}
	public int getId() {
		return book_id;
	}
	public void setId(int id) {
		this.book_id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	@Override
	public String toString() {
		return "Book [id=" + book_id + ", title=" + title + ", author=" + author + ", year=" + year + ", available="
				+ available + "]";
	}
	
	
}
