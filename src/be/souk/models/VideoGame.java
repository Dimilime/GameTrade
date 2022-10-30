package be.souk.models;

import java.io.Serializable;

public class VideoGame implements Serializable {
	
	private static final long serialVersionUID = 7655528300555278583L;
	
	private int idVideoGame;
	private String name;
	private String console;
	private int crediCost;
	private Booking booking;
	
	
	public VideoGame(int idVideoGame, String name, String console, int crediCost, Booking booking) {
		this.idVideoGame = idVideoGame;
		this.name = name;
		this.console = console;
		this.crediCost = crediCost;
		this.booking = booking;
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
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	@Override
	public String toString() {
		return "VideoGame [idVideoGame=" + idVideoGame + ", name=" + name + ", console=" + console + ", crediCost="
				+ crediCost + ", booking=" + booking + "]";
	}
	
	
	

}
