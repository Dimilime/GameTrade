package be.souk.dao;

import java.sql.*;
import java.util.*;
import java.util.Date;

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
		String req2 = "Select idUser, username, password, pseudo,dateOfBirth, registrationDate, credit "
				+ "from user u inner join player p on u.idUser = p.idUser  where username=?;";
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
								res.getInt(1),res.getString(2), res.getString(3)
								);
					}else {
						try(PreparedStatement stmt2 = connect.prepareStatement(req2)){
							stmt2.setString(1, username);
							
							try(ResultSet res2 = stmt2.executeQuery()){
								if(res2.next()) {
									user = new Player(
											res2.getInt(1),res2.getString(2), res2.getString(3),res2.getString(4),(Date)res2.getDate(5),(Date)res2.getDate(6),res2.getInt(7)
											);
								}
							}
						}
					}
					
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	
	
	

}
