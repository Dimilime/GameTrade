package be.souk.dao;

import java.sql.*;
import java.util.*;

import be.souk.models.*;

public class UserDAO extends DAO<User> {

	public UserDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(User obj) {
		return false;
	}

	@Override
	public boolean delete(User obj) {
		return false;
	}

	@Override
	public boolean update(User obj) {
		return false;
	}

	@Override
	public User find(int id) {
		return null;
	}

	@Override
	public ArrayList<User> findAll() {
		return null;
	}
	
	public boolean checkUserPassword(User u) {
		String req = "Select username, password from user where username=? and password=?";
		boolean valid = false;
		try(PreparedStatement stmt = connect.prepareStatement(req)){
			
			stmt.setString(1, u.getUserName());
			stmt.setString(2, u.getPassword());
			try(ResultSet res = stmt.executeQuery()){
				
				valid = res.next();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return valid;
	}
	
	public User getUser(String username) {
		
		String req = "Select * from user where username=?;";
		PlayerDAO playerDAO = new PlayerDAO(connect);
		boolean isAdmin=false;
		User user=null;
		
		try(PreparedStatement stmt = connect.prepareStatement(req)) {
			
			stmt.setString(1, username);
			
			try(ResultSet res = stmt.executeQuery()) {
				
				if(res.next())
				{
					isAdmin = res.getBoolean("admin");
					
					if(isAdmin)
					{
						user = new Admin(
								res.getInt(1),res.getString(2), null
								);
					}else {
						user = playerDAO.find(res.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();;
		}
		
		return user;
	}
	
	
	
	

}
