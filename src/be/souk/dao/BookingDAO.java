package be.souk.dao;

import java.sql.*;
import java.util.ArrayList;

import be.souk.models.Booking;
import be.souk.models.Player;
import be.souk.models.VideoGame;

public class BookingDAO extends DAO<Booking> {

	public BookingDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Booking booking) {
		String req =  "Insert into booking (borrower, bookingDate, nbWeek, idVideoGame) values (?,?,?,?); ";
		
		try (PreparedStatement stmt = connect.prepareStatement(req))
		{
			int cpt =1;
			stmt.setInt(cpt++, booking.getBorrower().getIdUser());
			stmt.setDate(cpt++, Date.valueOf(booking.getBookingDate()));
			stmt.setLong(cpt++, booking.getNbWeek());
			stmt.setInt(cpt++, booking.getVideoGame().getIdVideoGame());
			
			return stmt.executeUpdate()>0;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Booking obj) {
		return false;
	}

	@Override
	public boolean update(Booking obj) {
		return false;
	}

	@Override
	public Booking find(int id) {
		return null;
	}

	@Override
	public ArrayList<Booking> findAll() {
		return null;
	}
	
	public ArrayList<Booking> getBorrowerBookings(Player borrower){
		
		ArrayList<Booking> bookings = null;
		
		String req = "SELECT * FROM Booking WHERE borrower=?";
		
		try (PreparedStatement stmt = connect.prepareStatement(req))
		{
			stmt.setInt(1, borrower.getIdUser());
			try (ResultSet res = stmt.executeQuery())
			{
				bookings = new ArrayList<>();
				while(res.next()) {
					VideoGame videoGame = VideoGame.getVideoGame(res.getInt("idVideoGame"));
					Booking booking = new Booking(res.getInt("idBooking"), res.getDate("bookingDate").toLocalDate(), borrower, videoGame, res.getLong("nbWeek"));
					bookings.add(booking);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bookings;
	}

}
