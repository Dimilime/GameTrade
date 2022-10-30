package be.souk.dao;

import be.souk.models.*;

public abstract class AbstractDAOFactory {
	
	public static final int DAO_FACTORY = 0;
	public static final int XML_DAO_FACTORY = 1;
	
	public abstract DAO<Admin> getAdminDAO();
	
	public abstract DAO<Booking> getBookingDAO();
	
	public abstract DAO<Player> getPlayerDAO();
		
	public abstract DAO<Copy> getCopyDAO();
	
	public abstract DAO<Loan> getLoanDAO();
	
	public abstract DAO<VideoGame> getVideoGameDAO();
	
	
	public static AbstractDAOFactory getFactory(int type){
		switch(type){
		case DAO_FACTORY:
			return new DAOFactory();
			default:
				return null;
		}
	}

}
