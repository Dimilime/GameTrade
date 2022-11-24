package be.souk.dao;

import java.sql.*;
import java.util.ArrayList;

import be.souk.models.VideoGame;

public class VideoGameDAO extends DAO<VideoGame> {

	public VideoGameDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(VideoGame videoGame) {
		String req =  "Insert into videoGame (name,creditCost, console) values (?,?,?); ";
		
		try (PreparedStatement stmt = connect.prepareStatement(req))
		{
			int cpt =1;
			stmt.setString(cpt++, videoGame.getName());
			stmt.setInt(cpt++, videoGame.getCrediCost());
			stmt.setString(cpt++, videoGame.getConsole());
			
			return stmt.executeUpdate()>0;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
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
		
		try(PreparedStatement stmt = connect.prepareStatement(req)) {
			stmt.setInt(1, videoGame.getCrediCost());
			stmt.setInt(2, videoGame.getIdVideoGame());
			
			if(stmt.executeUpdate() > 0)
				return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public VideoGame find(int id) {
		String req = "Select * from videoGame where idVideoGame=?;";
		VideoGame videoGame=null;
		
		try(PreparedStatement stmt = connect.prepareStatement(req)) {
			
			stmt.setInt(1, id);
			try(ResultSet res = stmt.executeQuery()) {
				if(res.next()) {
					int idVideoGame = res.getInt(1);
					String name= res.getString(2);
					int creditCost = res.getInt(3);
					String console = res.getString(4);
					videoGame = new VideoGame(idVideoGame, name, creditCost, console);
				}		
			}
			
		} catch (SQLException e) {
			System.out.println("Error videogame not found!");
		}
		
		return videoGame;
	}

	@Override
	public ArrayList<VideoGame> findAll() {
		String req = "select idVideoGame, name, creditCost, console from VideoGame Order By name ";
		ArrayList<VideoGame> videoGames =null;
		try (Statement stmt = connect.createStatement())
		{
			try (ResultSet res = stmt.executeQuery(req))
			{
				videoGames = new ArrayList<>();
				while(res.next()) {
					VideoGame vg = new VideoGame(res.getInt("idVideoGame"), res.getString("name"),  res.getInt("creditCost"),res.getString("console"));
					videoGames.add(vg);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return videoGames;
	}
	

}
