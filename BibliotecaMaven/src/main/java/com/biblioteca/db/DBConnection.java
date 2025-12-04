package com.biblioteca.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/BibliotecaMaven";		
	private static final String USER = "root";
	private static final String PASSWORD =  "giadasql";
	private static DBConnection instance;
	private static Connection connection;
	
	private DBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection=DriverManager.getConnection(URL,USER,PASSWORD);
			System.out.println("connessione stabilita");
		}catch(Exception e) {e.printStackTrace();}
	}
	public static DBConnection getInstance() {
		if( instance==null) {
			instance= new DBConnection();
		}
		return instance;
	}
	public Connection getConnection() {
		return connection;
	}
}
