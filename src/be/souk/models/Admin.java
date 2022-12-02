package be.souk.models;

import java.io.Serializable;

import be.souk.dao.AbstractDAOFactory;
import be.souk.dao.DAO;

public class Admin extends User implements Serializable{

	private static final long serialVersionUID = 7640924502146446485L;

	private static AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private static DAO<VideoGame> videoGameDAO = adf.getVideoGameDAO();
	public Admin(int idUser, String userName, String password) {
		super(idUser, userName, password);
	}
	
	
	public boolean addVideoGame(VideoGame vg) {
		
		return videoGameDAO.create(vg);
	}
	
	public boolean deleteVideoGame(VideoGame vg) {
		return videoGameDAO.delete(vg);
	}
	
	public boolean editCreditVideoGame(VideoGame vg) {
		return videoGameDAO.update(vg);
	}

	
	
	
	
	

}
