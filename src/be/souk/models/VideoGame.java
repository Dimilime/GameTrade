package be.souk.models;

import java.io.Serializable;
import java.util.ArrayList;

import be.souk.dao.AbstractDAOFactory;
import be.souk.dao.DAO;

public class VideoGame implements Serializable {
	
	private static final long serialVersionUID = 7655528300555278583L;
	
	private int idVideoGame;
	private String name;
	private String console;
	private int crediCost;
	private ArrayList<Copy> copies;
	
	private static AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private static DAO<VideoGame> videoGameDAO = adf.getVideoGameDAO();
	
	public VideoGame(int idVideoGame, String name, String console, int crediCost) {
		this.idVideoGame = idVideoGame;
		this.name = name;
		this.console = console;
		this.crediCost = crediCost;
		copies = new ArrayList<>();
	}
	
	public int getIdVideoGame() {
		return idVideoGame;
	}
	public void setIdVideoGame(int idVideoGame) {
		this.idVideoGame = idVideoGame;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getConsole() {
		return console;
	}
	public void setConsole(String console) {
		this.console = console;
	}
	public int getCrediCost() {
		return crediCost;
	}
	public void setCrediCost(int crediCost) {
		this.crediCost = crediCost;
	}
	
	public ArrayList<Copy> getCopies() {
		return copies = Copy.getVideoGameCopies(this) ;
	}
	
	@Override
	public String toString() {
		return "VideoGame [idVideoGame=" + idVideoGame + ", name=" + name + ", console=" + console + ", crediCost="
				+ crediCost + "]";
	}
	public boolean update() {
		return videoGameDAO.update(this);
	}
	
	public static ArrayList<VideoGame> getAll(){
		return videoGameDAO.findAll();
	}
	
	public Copy copyAvailable() {
		
		for (Copy copy : copies) {
			if(copy.isAvailable())
				return copy;
		}
		return null;
		
	}

	
	

}
