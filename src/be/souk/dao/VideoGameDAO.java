package be.souk.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import be.souk.models.VideoGame;

public class VideoGameDAO extends DAO<VideoGame> {

	public VideoGameDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(VideoGame videoGame) {
		String req =  "Insert into videoGame (name,creditCost, console) values (?,?,?); ";
		String reqId = "Select @@IDENTITY ";
		String req2 = "INSERT INTO CreditCostHistory (idVideoGame, creditCost, modificationDate) values(?,?,?) ";
		int idVideoGame = 0;
		try (PreparedStatement stmt = connect.prepareStatement(req))
		{
			int cpt =1;
			stmt.setString(cpt++, videoGame.getName());
			stmt.setInt(cpt++, videoGame.getCrediCost());
			stmt.setString(cpt++, videoGame.getConsole());
			
			if(stmt.executeUpdate() > 0)
			{
				
				try (ResultSet res = connect.createStatement().executeQuery(reqId)) {
					if(res.next())
						idVideoGame= res.getInt(1);
				}
				
				try(PreparedStatement stmt2 = connect.prepareStatement(req2)){
					cpt=1;
					stmt2.setInt(cpt++, idVideoGame);
					stmt2.setInt(cpt++, videoGame.getCrediCost());
					stmt2.setDate(cpt++, Date.valueOf(LocalDate.now()));
					
					return stmt2.executeUpdate()>0;
				}
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(VideoGame videoGame) {
		String req = "DELETE FROM videoGame"
				+ "	  WHERE idVideoGame=?";
		
		try(PreparedStatement stmt = connect.prepareStatement(req)) {
			
			stmt.setInt(1, videoGame.getIdVideoGame());
			return stmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(VideoGame videoGame) {
		String req = "UPDATE videoGame"
				+ "	  SET creditCost = ? WHERE idVideoGame=?";
		String req2 = "INSERT INTO CreditCostHistory (idVideoGame, creditCost, modificationDate) values(?,?,?) ";
		
		try(PreparedStatement stmt = connect.prepareStatement(req)) {
			stmt.setInt(1, videoGame.getCrediCost());
			stmt.setInt(2, videoGame.getIdVideoGame());
			
			if(stmt.executeUpdate() > 0)
			{
				try(PreparedStatement stmt2 = connect.prepareStatement(req2)){
					int cpt=1;
					stmt2.setInt(cpt++, videoGame.getIdVideoGame());
					stmt2.setInt(cpt++, videoGame.getCrediCost());
					stmt2.setDate(cpt++, Date.valueOf(LocalDate.now()));
					
					return stmt2.executeUpdate()>0;
				}
				
			}
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public VideoGame find(int id) {
		String req = "Select * from videoGame where idVideoGame=?;";
		String req2= "select idCreditCostHistory, modificationDate from creditcosthistory where idVideoGame=? order by modificationDate";
		String req3 = "Select idBooking from booking where idVideoGame = ?";
		VideoGame videoGame=null;
		CreditCostHistoryDAO creditCostHistoryDAO = new CreditCostHistoryDAO(connect);
		BookingDAO bookingDAO = new BookingDAO(connect);
		try(PreparedStatement stmt = connect.prepareStatement(req)) {
			
			stmt.setInt(1, id);
			try(ResultSet res = stmt.executeQuery()) {
				if(res.next()) {
					int idVideoGame = res.getInt(1);
					String name= res.getString(2);
					int creditCost = res.getInt(3);
					String console = res.getString(4);
					videoGame = new VideoGame(idVideoGame, name, creditCost, console);
					
					try(PreparedStatement stmt2 = connect.prepareStatement(req2)){
						
						stmt2.setInt(1, videoGame.getIdVideoGame());
						try(ResultSet res2 = stmt2.executeQuery()){
							while(res2.next())
								videoGame.addCreditCostHistory(creditCostHistoryDAO.find(res2.getInt(1)));
						}
					}
					try(PreparedStatement stmt3 = connect.prepareStatement(req3)){
						
						stmt3.setInt(1, videoGame.getIdVideoGame());
						try(ResultSet res3 = stmt3.executeQuery()){
							while(res3.next())
								videoGame.addBooking(bookingDAO.find(res3.getInt(1)));
						}
					}
				}		
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return videoGame;
	}

	@Override
	public ArrayList<VideoGame> findAll() {
		String req = "select idVideoGame, name, creditCost, console from VideoGame Order By name ";
		String req2 = "SELECT Copy.idCopy "
				+ "FROM VideoGame INNER JOIN Copy ON VideoGame.idVideoGame = Copy.idVideoGame "
				+ "WHERE copy.idVideoGame = ?;";
		String req3= "select idCreditCostHistory, modificationDate from creditcosthistory where idVideoGame=? order by modificationDate";
		String req4= "select idBooking from booking where idVideoGame=? ";
		
		ArrayList<VideoGame> videoGames =null;
		CopyDAO copyDAO = new CopyDAO(connect);
		CreditCostHistoryDAO creditCostHistoryDAO = new CreditCostHistoryDAO(connect);
		BookingDAO bookingDAO = new BookingDAO(connect);
		try (Statement stmt = connect.createStatement())
		{
			try (ResultSet res = stmt.executeQuery(req))
			{
				videoGames = new ArrayList<>();
				while(res.next()) {
					VideoGame vg = new VideoGame(res.getInt("idVideoGame"), res.getString("name"),  res.getInt("creditCost"),res.getString("console"));
					
					try(PreparedStatement stmt2 = connect.prepareStatement(req2)){
						stmt2.setInt(1, vg.getIdVideoGame());
						try(ResultSet res2 = stmt2.executeQuery()){
							while(res2.next()) {
								vg.addCopy(copyDAO.find(res2.getInt(1)) );
							}
						}
					}
					
					try(PreparedStatement stmt3 = connect.prepareStatement(req3)){
						
						stmt3.setInt(1, vg.getIdVideoGame());
						try(ResultSet res3 = stmt3.executeQuery()){
							while(res3.next())
								vg.addCreditCostHistory(creditCostHistoryDAO.find(res3.getInt(1)));
						}
					}
					
					try(PreparedStatement stmt4 = connect.prepareStatement(req4)){
						
						stmt4.setInt(1, vg.getIdVideoGame());
						try(ResultSet res4 = stmt4.executeQuery()){
							while(res4.next())
								vg.addBooking(bookingDAO.find(res4.getInt(1)));
						}
					}
					
					videoGames.add(vg);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return videoGames;
	}
	

}
