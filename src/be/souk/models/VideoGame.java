package be.souk.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

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
	
	public VideoGame(int idVideoGame, String name, int crediCost, String console ) {
		this.idVideoGame = idVideoGame;
		this.name = name;
		this.crediCost = crediCost;
		this.console = console;
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
		//filter the collection, get videoGame copies
		return copies = Copy.getAll().stream().filter(copy -> copy.getVideoGame().equals(this)).collect(Collectors.toCollection(ArrayList::new));
	}
	
	@Override
	public String toString() {
		return "VideoGame [idVideoGame=" + idVideoGame + ", name=" + name + ", console=" + console + ", crediCost="
				+ crediCost + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(console, copies, crediCost, idVideoGame, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof VideoGame))
			return false;
		VideoGame other = (VideoGame) obj;
		return Objects.equals(console, other.console) && Objects.equals(copies, other.copies)
				&& crediCost == other.crediCost && idVideoGame == other.idVideoGame && Objects.equals(name, other.name);
	}

	public boolean update() {
		return videoGameDAO.update(this);
	}
	
	public static ArrayList<VideoGame> getAll(){
		return videoGameDAO.findAll();
	}
	
	public Copy copyAvailable(Player p) {
		if(copies !=null)
			return copies.stream().filter(copy -> !copy.getOwner().equals(p)).findFirst().orElse(null);//find first copy that not belong to the player (p)
		return null;//if no copy available return null
		
	}
	
	public static VideoGame getVideoGame(int id) {
		return videoGameDAO.find(id);
	}

	
	

}
