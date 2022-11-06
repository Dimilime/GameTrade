package be.souk.models;

import java.io.Serializable;

import be.souk.dao.AbstractDAOFactory;
import be.souk.dao.*;

public abstract class User implements Serializable{

	private static final long serialVersionUID = -2478199171149635958L;
	
	private static AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private static DAO<User> userDAO = adf.getUserDAO();
	
	private int idUser;
	private String userName;
	private String password;
	
	public User() {}
	
	public User(int idUser, String userName, String password) {
		this.idUser = idUser;
		this.userName = userName;
		this.password = password;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public  static User getUser(String username) {
		return ((UserDAO)userDAO).getUser(username);
	}
	public  boolean checkUserPassword() {
		return ((UserDAO)userDAO).checkUserPassword(this);
	}
	

	
	
	

}
