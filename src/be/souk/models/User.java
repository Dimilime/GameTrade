package be.souk.models;

import java.io.Serializable;

public abstract class User implements Serializable{

	private static final long serialVersionUID = -2478199171149635958L;
	
	private int idUser;
	private String userName;
	private String password;
	
	public User() {}
	
	public User(int idUser, String userName, String password) {
		this.idUser = idUser;
		this.userName = userName;
		this.password = password;
	}

	protected int getIdUser() {
		return idUser;
	}

	protected void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	protected String getUserName() {
		return userName;
	}

	protected void setUserName(String userName) {
		this.userName = userName;
	}

	protected String getPassword() {
		return password;
	}

	protected void setPassword(String password) {
		this.password = password;
	}
	

	
	
	

}
