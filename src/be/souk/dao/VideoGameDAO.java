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
			stmt.executeUpdate();
			
			return true;
			
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
			
			if(stmt.executeUpdate() > 0)
				return true;
			
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
		return null;
	}

	@Override
	public ArrayList<VideoGame> findAll() {
		String req = "select * from VideoGame";
		ArrayList<VideoGame> videoGames = new ArrayList<>();
		try (Statement stmt = connect.createStatement())
		{
			try (ResultSet res = stmt.executeQuery(req))
			{
				while(res.next()) {
					VideoGame vg = new VideoGame(res.getInt("idVideoGame"), res.getString("name"), res.getString("console"), res.getInt("creditCost"));
					videoGames.add(vg);
				}
				return videoGames;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
