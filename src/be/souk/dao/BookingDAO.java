package be.souk.dao;

import java.sql.*;
import java.time.LocalDate;
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
	public boolean delete(Booking booking) {
		String req = "DELETE FROM booking"
				+ "	  WHERE idBooking=?";
		
		try(PreparedStatement stmt = connect.prepareStatement(req)) {
			
			stmt.setInt(1, booking.getIdBooking());
			return stmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		String req = "select * from booking ";
		ArrayList<Booking> bookings =null;
		try (Statement stmt = connect.createStatement())
		{
			try (ResultSet res = stmt.executeQuery(req))
			{
				bookings = new ArrayList<>();
				while(res.next()) {
					int idBooking = res.getInt("idBooking");
					LocalDate bookingDate = res.getDate("bookingDate").toLocalDate();
					Player p = Player.getPlayer(res.getInt("borrower"));
					VideoGame vg = VideoGame.getVideoGame(res.getInt("idVideoGame"));
					long nbWeek = res.getLong("nbWeek");
					Booking bk = new Booking(idBooking, bookingDate, p, vg, nbWeek);
					bookings.add(bk);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookings;
	}
	
	

}
