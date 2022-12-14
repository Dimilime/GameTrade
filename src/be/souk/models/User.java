package be.souk.models;

import java.io.Serializable;
import java.util.Objects;

import be.souk.dao.AbstractDAOFactory;
import be.souk.dao.*;

public abstract class User implements Serializable{

	private static final long serialVersionUID = -2478199171149635958L;
	
	private static AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private static DAO<User> userDAO = adf.getUserDAO();
	
	protected int idUser;
	protected String userName;
	protected String password;
	
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
	public  boolean login() {
		return ((UserDAO)userDAO).checkUserPassword(this);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idUser, password, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		return idUser == other.idUser && Objects.equals(password, other.password)
				&& Objects.equals(userName, other.userName);
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", userName=" + userName + ", password=" + password + "]";
	}
	
	
	
	
	

	
	
	

}
