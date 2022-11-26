package be.souk.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import be.souk.dao.AbstractDAOFactory;
import be.souk.dao.DAO;

public class VideoGame implements Serializable {
	
	private static final long serialVersionUID = 7655528300555278583L;
	private static AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private static DAO<VideoGame> videoGameDAO = adf.getVideoGameDAO();
	
	private int idVideoGame;
	private String name;
	private String console;
	private int crediCost;
	private ArrayList<Copy> copies;
	private ArrayList<CreditCostHistory> creditCostHistories;
	private ArrayList<Booking> bookings;
	
	
	public VideoGame(int idVideoGame, String name, int crediCost, String console ) {
		this.idVideoGame = idVideoGame;
		this.name = name;
		this.crediCost = crediCost;
		this.console = console;
		copies = new ArrayList<>();
		creditCostHistories = new ArrayList<>();
		bookings = new ArrayList<>();
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
	
	public ArrayList<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(ArrayList<Booking> bookings) {
		this.bookings = bookings;
	}

	public ArrayList<Copy> getCopies() {
		return copies ;
	}
	
	public void addCopy(Copy copy) {
		copies.add(copy);
	}
	public void removeCope(Copy copy) {
		copies.remove(copy);
	}
	
	public void addCreditCostHistory(CreditCostHistory ccH) {
		creditCostHistories.add(ccH);
	}
	
	public void addBooking(Booking b) {
		bookings.add(b);
	}
	
	public void removeBooking(Booking b) {
		bookings.remove(b);
	}

	public boolean update() {
		return videoGameDAO.update(this);
	}
	
	public static ArrayList<VideoGame> getAll(){
		return videoGameDAO.findAll();
	}
	
	public Copy copyAvailable(Player p) {
		if(copies !=null)
			return copies.stream().filter(copy -> !copy.getOwner().equals(p) && copy.isAvailable()).findFirst().orElse(null);//find first copy that not belong to the player (p)
		return null;//if no copy available return null
		
	}
	
	public static VideoGame getVideoGame(int id) {
		return videoGameDAO.find(id);
	}

	public ArrayList<CreditCostHistory> getCreditCostHistories() {
		return creditCostHistories;
	}

	public void setCreditCostHistories(ArrayList<CreditCostHistory> creditCostHistories) {
		this.creditCostHistories = creditCostHistories;
	}
	
	public void selectBooking() {
		
		if(bookings.size()>0) {
			
		}
		
	}

	@Override
	public String toString() {
		return "VideoGame [idVideoGame=" + idVideoGame + ", name=" + name + ", console=" + console + ", crediCost="
				+ crediCost + ", copies=" + copies + ", creditCostHistories=" + creditCostHistories + "]";
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
	

	
	

}
