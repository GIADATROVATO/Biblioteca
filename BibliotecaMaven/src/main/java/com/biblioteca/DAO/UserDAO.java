package com.biblioteca.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biblioteca.db.DBConnection;
import com.biblioteca.model.User;

public class UserDAO {
	private Connection conn;
	public UserDAO() {
		conn=DBConnection.getInstance().getConnection();
	}
	public void insertUser(User user) {
		String sql= "INSERT INTO user(name,email) VALUES (?,?)";
		try(PreparedStatement stmt= conn.prepareStatement(sql)){
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.executeUpdate();
		}catch(Exception e) { e.printStackTrace();}
		
	}
	public User getUserById(int user_id) {
		String sql="SELECT * FROM user WHERE id=? ";
		User user=null;
		try (PreparedStatement stmt= conn.prepareStatement(sql)){
			stmt.setInt(1,user_id);
			ResultSet rs= stmt.executeQuery();
			if(rs.next()) {
				user= new User(
					rs.getInt("id"),
					rs.getString("name"),
					rs.getString("email")
			);}
		}catch(Exception e) { e.printStackTrace();}
		return user;
	}
	public List<User> getAll() throws SQLException{
		String sql= "UPDATE * FROM user";
		List<User> users= new ArrayList<>();
		try (PreparedStatement stmt= conn.prepareStatement(sql)){
			ResultSet rs= stmt.executeQuery();
			while(rs.next()) {
				users.add(new User(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("email")
				));
			}
		}
		return users;
	}
	public boolean updateUser(User user) {
		String sql ="UPDATE user SET name=?, email=?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setInt(3, user.getId());
            return stmt.executeUpdate() > 0;
	} catch (SQLException e) { e.printStackTrace(); }
		return false;
	}
	public void deleteUser(int user_id) throws SQLException {
		String sql ="DELETE FROM user WHERE id=?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1,user_id);
			stmt.executeUpdate();
		    System.out.println("user eliminato");
		} catch (SQLException e) { e.printStackTrace(); }
	}
	
}
